
package KUIS1;

import java.util.Scanner;

abstract class Sapi {
    private String nama;
    private int berat;
    private String layanan;
    
    public Sapi(String nama, int berat, String layanan){
        this.nama = nama;
        this.berat = berat;
        this.layanan = layanan;
    }
    
    public String getNama(){
        return nama;
    }
    
    public int getBerat(){
        return berat;
    }
    
    public String getLayanan(){
        return layanan;
    }
    
    public double getHarga(){
        switch(layanan){
            case "spa":
                return 8000;
            case "potong kuku":
                return 6000;
            case "grooming":
                return 10000;
            default :
                return 0;
        }
    }
    
    public double getBiaya(){
        return berat*getHarga();
    }
    
    public double getDiskon(){
        if(berat > 30){
            return 0.10 * getBiaya();
        }
        return 0;
    }
    
    public abstract double getTambahan();
    
    public double getTotal(){
        return getBiaya() - getDiskon() + getTambahan();
    }
    
    public double getPajak(){
        return 0.08*getTotal();
    }
    
    public double getTotalKeseluruhan(){
        if(getGratis())return 0;
        return getTotal() + getPajak();
    }
    
    public boolean getGratis(){
        return getNama().equals("Moo")||
                getNama().equals("Mooo")||
                getNama().equals("Mooo");
    }
}

class SapiReguler extends Sapi{
    public SapiReguler(String nama, int berat, String layanan){
        super(nama, berat, layanan);
    }
    
    @Override
    public double getTambahan(){
        return 0;
    }
}

class SapiVIP extends Sapi{
    public SapiVIP(String nama, int berat, String layanan){
        super(nama, berat, layanan);
    }
    
    @Override
    public double getTambahan(){
        return 0.20*getBiaya();
    }
}

public class KlinikSapi {
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        
        String nama;
        int berat = 0 ;
        String layanan;
        String kelas;
        
        while(true){
            nama = input.nextLine();
            if (nama.matches("[a-zA-Z]+"))break;
            System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
        }
        
        while(true){
            try{
                berat = Integer.parseInt(input.nextLine());
                if (berat >= 1 && berat <= 80)break;
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            }catch(Exception e){
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            }
        }
        
        while (true){
            layanan = input.nextLine().toLowerCase();
            if (layanan.equals("spa")|| layanan.endsWith("potong kuku")|| layanan.equals("grooming"))break;
            System.out.println("Pilih spa, potong kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
        }
        
        while (true){
            kelas = input.nextLine().toLowerCase();
            if(kelas.equals("reguler")|| kelas.equals("vip"))break;
            System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
        }
        
        Sapi sapi;
        if (kelas.equals("vip")){
            sapi = new SapiVIP(nama, berat, layanan);
        }else{
            sapi = new SapiReguler(nama, berat, layanan);
        }
        
        System.out.println("============ NOTA KLINIK SAPI =============");
        System.out.println("Nama Sapi: " + sapi.getNama());
        System.out.println("Berat: " + sapi.getBerat() + " kg");
        System.out.println("Jenis Layanan: " + layanan);
        System.out.println("Kelas: " + kelas);
        System.out.println("Biaya Dasar: Rp " + sapi.getBiaya());
        System.out.println("Diskon: Rp " + sapi.getDiskon());
        System.out.println("Biaya Tambahan VIP: Rp " + sapi.getTambahan());
        System.out.println("Subtotal: Rp " + sapi.getTotal());
        System.out.println("Pajak: Rp " + sapi.getPajak());
        System.out.println("Total Biaya: Rp " + sapi.getTotalKeseluruhan());
        System.out.println("============================================");
        
        if (sapi.getGratis()){
            System.out.println("Terima kasih, " + nama + " ! Sapi spesial memang beda perlakuan~");
        }else{
            System.out.println("Terima kasih, " + nama + " ! Semoga sapinya makin glow up.");
        }
        
        input.close();
    }
}
