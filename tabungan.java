
package KUIS1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

abstract class Student{
    private String nama;
    private double saldo;
    
    Student(String nama){
        this.nama = nama;
        this.saldo = 0;
    }
    
    void setnama(String nama){
        this.nama = nama;
    }
    
    String getNama(){
        return nama;
    }
    
    double getSaldo(){
        return saldo;
    }
    public void tambahSaldo (double jumlah){
        this.saldo += jumlah;
    }
    
    public void kurangiSaldo (double jumlah){
        this.saldo -= jumlah;
    }
    
    abstract void take(double jumlah);
    abstract String getTipe();
}

class Beasiswa extends Student{
    Beasiswa(String nama){
        super (nama);
    }
    
    @Override
    void take(double jumlah){
        double potongan = Math.max(0, jumlah-1000);
        kurangiSaldo(potongan);
    }
    
    @Override
    String getTipe(){
        return "BEASISWA";
    }
}

class Reguler extends Student{
    public Reguler (String nama){
        super(nama);
    }
    
    @Override
    void take(double jumlah){
        kurangiSaldo(jumlah);
    }
    
    @Override
    String getTipe(){
        return "REGULER";
    }
}
public class tabungan {
    public static void main(String []args){
        Scanner input = new Scanner(System.in);
        
        ArrayList<Student>listStudent = new ArrayList<>();
        
        int n = input.nextInt();
        for (int i = 0; i<n ; i++){
            String pilihan = input.next();
            
            
            if (pilihan.equals("CREATE")){
                String tipe = input.next();
                String name = input.next();
                
                Student s = cariSiswa(listStudent, name);
                if (s != null){
                    System.out.println("Akun Sudah Terdaftar");
                }else{
                    if(tipe.equals("REGULER")){
                        listStudent.add(new Reguler(name));
                    }else{
                        listStudent.add(new Beasiswa(name));
                    }
                    System.out.println(tipe + " " + name + " berhasil dibuat");
                }
           
                }else if (pilihan.equals("SAVE")){
                    String name = input.next();
                    double jumlah = input.nextDouble();
                    Student s = cariSiswa(listStudent, name);
                    
                    if(s == null){
                        System.out.println("Akun tidak ditemukan");
                    }else{
                        s.tambahSaldo(jumlah);
                        System.out.println("Saldo "+ name + " : " + s.getSaldo());
                    }
                }else if (pilihan.equals("TAKE")){
                    String name = input.next();
                    double jumlah = input.nextDouble();
                    Student s = cariSiswa(listStudent, name);
                    
                    if (s== null){
                        System.out.println("Akun tidak ditemukan");
                    }else{
                        double biaya = (s instanceof Beasiswa)? Math.max(0, jumlah-1000): jumlah;
                    
                    
                        if (s.getSaldo()< biaya){
                             System.out.println("Saldo "+ name + " tidak cukup");
                        }else {
                            s.take(jumlah);
                            System.out.println("Saldo " + name + " : " + s.getSaldo());
                        }
                    }
                }else if(pilihan.equals("CHECK")) {
                    String name = input.next();
                    Student s = cariSiswa(listStudent, name);
                    
                    if(s != null){
                        System.out.println(s.getNama() + " | " + s.getTipe()+ " | saldo: " + s.getSaldo());
                    }
                }
            }
        }
    public static Student cariSiswa(ArrayList<Student>list, String name){
        for(Student s : list){
            if(s.getNama().equalsIgnoreCase(name)){
                return s;
            }
        }
        return null;
    }
}
