/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.login;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class penggunacontrol {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    
    public penggunacontrol(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapotek","root","");
            st=con.createStatement();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal, Terjadi Kesalahan Pada : \n" + e);
        }
        
    }
    
    public List cariLogin(String username, String password){
        List logLogin = new ArrayList();
        int result;
        sql = "select username, password, hakakses from pengguna where username = '"+username+ "' and password = '"+password+ "' ";
        try {
            rs = st.executeQuery(sql);
            while(rs.next()){
                datapengguna ep = new datapengguna();
                ep.setusername(rs.getString("username"));
                ep.setpassword(rs.getString("password"));
                ep.sethakakses(rs.getString("hakakses"));
                logLogin.add(ep);
        }       
            }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Login, Pada:n"+e);
            }
        return logLogin;   
    }
    
    public List cariid(int idpengguna){
        List logLogin = new ArrayList();
        int result;
        sql = "select idpengguna from pengguna where idpengguna = '"+idpengguna+"'";
        try{
            rs = st.executeQuery(sql);
            while (rs.next()){
                datapengguna ep = new datapengguna();
                ep.setidpengguna(rs.getInt("idpengguna"));
                logLogin.add(ep);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pencarian id, pada "+e);
    }
        return logLogin;
    }
    
    public int tambah(datapengguna e){
        sql="insert into pengguna values('"+e.getidpengguna()+"','"+e.getusername()+"','"+e.getpassword()+"','"+e.gethakakses()+"')";
        int hasil = 0;
        try{
            hasil = st.executeUpdate(sql);
        }catch(SQLException a){
            Logger.getLogger(penggunacontrol.class.getName()).log(Level.SEVERE, null, a);
        }
        return hasil;
    }
    
    public int deletepengguna(datapengguna e){
        sql = "delete from pengguna where idpengguna='"+e.getidpengguna()+"'";
        int hasil = 0;
        try{
            hasil = st.executeUpdate(sql);
        }catch (Exception a){
            Logger.getLogger(penggunacontrol.class.getName()).log(Level.SEVERE, null, a);
        }
        return hasil;
    }
    
    public List tampil(){
        List logObat = new ArrayList();
        sql = "select idpengguna, username, password, hakakses from pengguna order by idpengguna";
        try{
            rs = st.executeQuery(sql);
            while(rs.next()){
                datapengguna dp = new datapengguna();
                dp.setidpengguna(rs.getInt("idpengguna"));
                dp.setusername(rs.getString("username"));
                dp.setpassword(rs.getString("password"));
                dp.sethakakses(rs.getString("hakakses"));
                logObat.add(dp);
            }
        }catch (Exception e){
            JOptionPane.showConfirmDialog(null, "Terjadi kesalahn tampil, pada : "+e);
        }
        return logObat;
    }
}
