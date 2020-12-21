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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class penggunacontrol {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;

    public penggunacontrol(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapotek","root","");
            st=con.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada : \n" + e);
        }
    }
    
    public List cariLogin(String username, String password) {
        List logLogin = new ArrayList();
        int result;
        sql = "select username, password, hakakses from pengguna where username='"+username+"' and password='"+password+"'";
        try{
            rs=st.executeQuery(sql);
            while(rs.next()) {
            datapengguna ep=new datapengguna();
            ep.setusername(rs.getString("username"));
            ep.setpassword(rs.getString("password"));
            ep.sethakakses(rs.getString("hakakses"));
            logLogin.add(ep);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Login, Pada:n" + e);
        }
        return logLogin;
    }
    
}
