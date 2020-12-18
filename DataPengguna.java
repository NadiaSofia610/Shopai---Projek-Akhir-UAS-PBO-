/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Siti Nur Aisha
 */
public class DataPengguna {

    private String username;
    private String nama;
    private String ttl;
    private String alamat;
    private String jenisKelamin;
    private String email;
    private String password;
    private String noHP;
    
    public DataPengguna(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public DataPengguna(String nama, String ttl, String alamat, String jenisKelamin, String email, String noHP)
    {
        this.nama = nama;
        this.ttl = ttl;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.email = email;
        this.noHP = noHP;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
    public String getNama()
    {
        return this.nama;
    }
    
    public String getTtl()
    {
        return this.ttl;
    }
    
    public String getAlamat()
    {
        return this.alamat;
    }
    
    public String getJenisKelamin()
    {
        return this.jenisKelamin;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public String getNoHP()
    {
        return this.noHP;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public void Akun()
    {
        System.out.println("Username             : ");
        System.out.println("Nama Lengkap         : ");
        System.out.println("Tempat Tanggal Lahir : ");
        System.out.println("Alamat               : ");
        System.out.println("Jenis Kelamin        : ");
        System.out.println("Email                : ");
        System.out.println("No. HP               : ");
        System.out.println("Password             : ");
    }
}
