/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.dataobat;

/**
 *
 * @author ASUS
 */
public class DataObat {
    public String kode_obat, nama_obat, satuan;
    public int jumlah_stok, harga_kontrak, harga_beli;
    
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
    
    public String getSatuan(){
        return satuan;
    }
    
    public void setSatuan(String satuan){
        this.satuan = satuan;
    }
    
    public int getJmlstok(){
        return jumlah_stok;
    }
    
    public void setJmlstok(int jumlah_stok){
        this.jumlah_stok = jumlah_stok;
    }
    
    public int getHrgkontrak(){
        return harga_kontrak;
    }
    
    public void setHrgkontrak(int harga_kontrak){
        this.harga_kontrak = harga_kontrak;
    }
    
    public int getHrgbeli(){
        return harga_beli;
    }
    
    public void setHrgbeli(int harga_beli){
        this.harga_beli = harga_beli;
    }
}
