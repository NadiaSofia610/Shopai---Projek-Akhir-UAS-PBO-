/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.beli;

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
public class belicontrol {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    
    public belicontrol(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapotek", "root", "");
            st = con.createStatement();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Koneksi database gagal, Terjadi Kesalahan pada : \n"+e);
        }
    }
    
    public List tampil() {
        List logBeli = new ArrayList();
        sql = "select no_nota, tanggal_beli, nama_pembeli, total, tunai, kembali from tbbeli order by idbeli asc";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                databeli db = new databeli();
                db.setno_nota(rs.getString("no_nota"));
                db.settanggal(rs.getString("tanggal_beli"));
                db.setnamapembeli(rs.getString("nama_pembeli"));
                db.settotal(rs.getInt("total"));
                db.settunai(rs.getInt("tunai"));
                db.setkembali(rs.getInt("kembali"));
                logBeli.add(db);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan tampil, pada: \n" + e);
        }
        return logBeli;
    }
}
