/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.beli;

/**
 *
 * @author HP
 */
public class databeli {
    private String no_nota, tanggal_beli, nama_pembeli;
    
    public String getnonota() {
        return no_nota;
    }
    
    public void setno_nota (String no_nota) {
        this.no_nota = no_nota;
    }
    
    public String gettanggal() {
        return tanggal_beli;
    }
    
    public void settanggal(String tanggal) {
        this.tanggal_beli = tanggal_beli;
    }
    
    public String getnamapembeli() {
        return nama_pembeli;
    }
    
    public void setnamapembeli(String nama_pembeli) {
        this.nama_pembeli = nama_pembeli;
    }
    
    int total, tunai, kembali;
    
    public int gettotal() {
        return total;
    }
    
    public void settotal(int total) {
        this.total = total;
    }
    
    public int gettunai() {
        return tunai;
    }
    
    public void settunai(int tunai) {
        this.tunai = tunai;
    }
    
    public int getkembali() {
        return kembali;
    }
    
    public void setkembali(int kembali) {
        this.kembali = kembali;
    }
}
