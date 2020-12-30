/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apotek;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Fajar
 */
public class koneksi  {
    static Connection con;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapotek","root","");
            
        }catch (Exception ex){
            System.out.println(""+ex);
        }
        return con;
    }

}