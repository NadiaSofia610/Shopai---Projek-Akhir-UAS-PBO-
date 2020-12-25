/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.penjualan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class penjualancontrol {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    
    public penjualancontrol(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapotek", "root", "");
            st = con.createStatement();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Koneksi database gagal, Terjadi Kesalahan pada : \n"+e);
        }
    }
    
    public List CekObat(String Nonota, String Kdobat) {
        List logDetailBeli = new ArrayList();
        sql = "select no_nota, kode_obat, nama_obat, harga_jual, jumlah_beli, total_bayar from tbpenjualan where no_nota='"+Nonota+"' and kode_obat='"+Kdobat+"'";
        try{
            rs = st.executeQuery(sql);
            while(rs.next()) {
                DataPenjualan dp = new DataPenjualan();
                dp.setKdobat(rs.getString("kode_obat"));
                dp.setNmobat(rs.getString("nama_obat"));
                dp.setHrgjual(rs.getInt("harga_jual"));
                dp.setJmlbeli(rs.getInt("jumlah_beli"));
                dp.setTbayar(rs.getInt("total_bayar"));
                logDetailBeli.add(dp);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Cek Barang : \n"+e);
        }
        return logDetailBeli;
    }
    
    public int tambahpenjualan(DataPenjualan a) {
        sql = "insert into tbpenjualan values('"+a.getNonota()+"', '"+a.getKdobat()+"', '"+a.getNmobat()+"', '"+a.getHrgjual()+"', '"+a.getJmlbeli()+"', '"+a.getTbayar()+"')";
        int hasil = 0;
        try{
            hasil = st.executeUpdate(sql);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada : \n"+e);
        }
        return hasil;
    }
    
}
