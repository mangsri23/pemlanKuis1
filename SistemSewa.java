
package KUIS1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

abstract class Vehicle{
    private String kode;
    private String nama;
    private int harga;
    private boolean ketersediaan;
    
    public Vehicle(String kode, String nama, int harga){
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.ketersediaan = true;
    }
    
    public String getKode(){
        return kode;
    }
    
    public String getNama(){
        return nama;
    }
    
    public int getHarga(){
        return harga;
    }
    
    public boolean getKetersediaan(){
        return ketersediaan;
    }
    
    public void setKetersediaan(boolean ketersediaan){
        this.ketersediaan = ketersediaan;
    }
     public abstract String getType();
     public abstract int hitungSewa(int hari, boolean promo);
     
     public String getStatus(){
         return ketersediaan ? "TERSEDIA" : "DISEWA";
     }
     
     public String display(){
         return getKode() + " | " + getType() + " | " + getNama() + " | harga: " + getHarga() + " | ststus: " + getStatus();
     }
}

class Car extends Vehicle{
    public Car (String kode, String nama, int harga){
        super(kode, nama, harga);
    }
    
    @Override 
    public String getType(){
        return "CAR";
    }
    @Override 
    public int hitungSewa(int hari,boolean promo){
        int total = getHarga()*hari;
        if (promo){
            total -= 20000;
        }
        return Math.max(total ,0);
    }
}

class Bike extends Vehicle{
    public Bike (String kode, String nama, int harga){
        super(kode, nama, harga);
    }
    
    @Override 
    public String getType (){
        return "BIKE";
    }
    @Override 
    public int hitungSewa(int hari, boolean promo){
        int total = getHarga()* hari;
        if (promo){
            total -= 10000;
        }
        return Math.max(total, 0);
    }
}

public class SistemSewa {
    
    static ArrayList<Vehicle> list = new ArrayList<>();
    
    public static Vehicle cari(String kode){
        for (Vehicle a : list){
            if (a.getKode().equals(kode)){
                return a;
            }
        }
        return null;
    }
    
    
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        int pilih = Integer.parseInt(input.nextLine());
        
        for (int i =0; i<pilih; i++){
            String pilihan = input.nextLine();
            String [] menu = pilihan.split(" ");
            
            switch(menu[0]){
                case "ADD":
                    String tipe = menu[1];
                    String kode = menu[2];
                    String nama = menu[3];
                    int harga = Integer.parseInt(menu[4]);
                    
                    if (cari(kode) != null){
                        System.out.println("Kendaraan sudah terdaftar");
                        break;
                    }
                    
                    if (tipe.equals("CAR")){
                        list.add(new Car(kode, nama, harga));
                    }else{
                        list.add(new Bike(kode, nama, harga));
                    }
                    
                    System.out.println(tipe + " " + kode + " berhasil ditambahkan" );
                    break;
                    
                case "RENT":
                    kode = menu[1];
                    int hari = Integer.parseInt(menu[2]);
                    boolean promo = (menu.length == 4 && menu[3].equalsIgnoreCase("PROMO"));
                    
                    Vehicle b = cari(kode);
                    
                    if(b == null){
                        System.out.println("Kendaraan tidak ditemukan");
                    }else if (!b.getKetersediaan()){
                        System.out.println("Kendaraan sedang disewa");
                    }else{
                        int total = b.hitungSewa(hari, promo);
                        b.setKetersediaan(false);
                        System.out.println("Total sewa " + kode + " : " + total);
                    }
                    break;
                    
                case "RETURN":
                    kode = menu[1];
                    b = cari(kode);
                    
                    
                    if (b == null){
                        System.out.println("Kendaraan tidak ditemukan");
                    }else if (b.getKetersediaan()){
                        System.out.println("Kendaraan belum disewa");
                    }else{
                        b.setKetersediaan(true);
                        System.out.println(kode + " berhasil dikembalikan");
                    }
                    break;
                    
                case "DETAIL":
                    kode = menu[1];
                    b = cari(kode);
                    
                    if (b == null){
                        System.out.println("Kendaraan tidak ditemukan");
                    }else{
                        System.out.println(b.display());
                    }
                    break;
                    
                case "COUNT":
                    System.out.println("Total kendaraan: " + list.size());
                    break;
            }
        }
        input.close();
    }
}
