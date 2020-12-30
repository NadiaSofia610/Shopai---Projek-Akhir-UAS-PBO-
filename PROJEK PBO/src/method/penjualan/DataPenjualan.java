/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.penjualan;

/**
 *
 * @author ASUS
 */
public class DataPenjualan {
    public String nomor_nota, kode_obat, nama_obat;
    
    public String getNonota(){
        return nomor_nota;
    }
    
    public void setNonota(String nomor_nota){
        this.nomor_nota = nomor_nota;
    }
    
    public String getKdobat(){
        return kode_obat;
    }
    
    public void setKdobat(String kode_obat){
        this.kode_obat = kode_obat;
    }
    
    public String getNmobat(){
        return nama_obat;
    }
    
    public void setNmobat(String nama_obat){
        this.nama_obat = nama_obat;
    }
    
    int harga_jual, jumlah_beli, total_bayar;
    
    public int getHrgjual(){
        return harga_jual;
    }
    
    public void setHrgjual(int harga_jual){
        this.harga_jual = harga_jual;
    }
    
    public int getJmlbeli(){
        return jumlah_beli;
    }
    
    public void setJmlbeli(int jumlah_beli){
        this.jumlah_beli = jumlah_beli;
    }
    
    public int getTbayar(){
        return total_bayar;
    }
    
    public void setTbayar(int total_bayar){
        this.total_bayar = total_bayar;
    }
    
    
}
