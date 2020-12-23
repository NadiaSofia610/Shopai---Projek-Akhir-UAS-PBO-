 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class reportcontrol {
Connection con=null; 
Statement st=null;
ResultSet rs=null;
String sql=null;

public reportcontrol(){
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapotek?","root","");
        st=con.createStatement();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Koneksi Database Gagal, terjadi Kesalahan Pada :\n" +e);
    }
}

public void cetakSeluruhObat(){
    try {
        String sumber;
        String tujuan;
        sumber= "src/Report/DataObat.jrxml";
        Map parms = new HashMap();
        JasperReport jr = JasperCompileManager.compileReport(sumber);
        
    } catch (Exception e) {
    
}

}
