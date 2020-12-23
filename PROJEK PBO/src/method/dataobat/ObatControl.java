/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.dataobat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ObatControl {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    
    public ObatControl(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapotek", "root", "");
            st = con.createStatement();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Koneksi database gagal, Terjadi Kesalahan pada : \n"+e);
        }
    }
    
    public List tampil(){
        List LogObat = new ArrayList();
        sql = "select kode_obat, nama_obat, satuan, jumlah_stok, harga_kontrak, harga_beli from tbdataobat order by idobat asc";
            try {
                rs = st.executeQuery(sql);
                    while (rs.next()){
                        DataObat Do = new DataObat();
                        Do.setKdobat(rs.getString("kode_obat"));
                        Do.setNmobat(rs.getString("nama_obat"));
                        Do.setSatuan(rs.getString("satuan"));
                        Do.setJmlstok(rs.getInt("jumlah_stok"));
                        Do.setHrgkontrak(rs.getInt("harga_kontrak"));
                        Do.setHrgbeli(rs.getInt("harga_beli"));
                        LogObat.add(Do);
                        
                    }
                
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada : \n"+e);
            }
            return LogObat;
    }
    
    public int tambahobat(DataObat e){
        sql = "insert into tbdataobat (kode_obat, nama_obat, satuan, jumlah_stok, harga_kontrak, harga_beli) values('"+e.getKdobat()+"','"+e.getNmobat()
                +"','"+e.getSatuan()+"','"+e.getJmlstok()+"','"+e.getHrgkontrak()+"','"+e.getHrgbeli()+"')";
        int hasil = 0;
        try{
            hasil = st.executeUpdate(sql);
        }catch(Exception a){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada : \n"+a);
        }
        return hasil;
    }
    
    public int editObat(DataObat e){
        sql = "update tbdataobat set nama_obat='"+e.getNmobat()+"',satuan='"+e.getSatuan()+"',jumlah_stok='"+e.getJmlstok()
                +"',harga_kontrak='"+e.getHrgkontrak()+"',harga_beli='"+e.getHrgbeli()+"'where kode_obat='"+e.getKdobat()+"'";
        int hasil = 0;
        try{
            hasil = st.executeUpdate(sql);
        } catch (Exception a){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada : \n"+a);
        }
        return hasil;
    }
    
    public List cariObat(String cari){
        List logObat = new ArrayList();
        sql = "select kode_obat, nama_obat, satuan, jumlah_stok, harga_kontrak, harga_beli from tbdataobat where satuan "
                +"like '%"+cari+"' or nama_obat like '%"+cari+"%'";
        try{
            rs=st.executeQuery(sql);
            while(rs.next()){
                DataObat Do = new DataObat();
                Do.setKdobat(rs.getString("kode_obat"));
                Do.setNmobat(rs.getString("nama_obat"));
                Do.setSatuan(rs.getString("satuan"));
                Do.setJmlstok(rs.getInt("jumlah_stok"));
                Do.setHrgkontrak(rs.getInt("harga_kontrak"));
                Do.setHrgbeli(rs.getInt("harga_beli"));
                logObat.add(Do);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada : \n"+e);
        }
        return logObat;
    }
    
    public int deleteObat(DataObat e){
        sql="delete from tbdataobat where kode_obat = '"+e.getKdobat()+"'";
        int hasil=0;
        try{
            hasil = st.executeUpdate(sql);
        } catch(Exception a){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada : \n"+a);
        }
        return hasil;
    }
    
    public int getJumlahObat() throws SQLException{
        sql = "select count(*) as jumlah_obat "+ "from tbdataobat";
        rs = st.executeQuery(sql);
        int jumlah;
        while(rs.next()){
            jumlah = rs.getInt("jumlah_obat");
            return jumlah;
        }
        return 0;
    }
}
