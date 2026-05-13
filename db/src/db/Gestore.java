/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ricci.davide
 */
public class Gestore {
    
    
    private String 
    String url = "jdbc:sqlite:scuola.db";
       
        try ( Connection conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM classi")){
            while(rs.next()){
                System.out.println(
                        rs.getString("id_classe") + " - " +
                        rs.getString("indirizzo")
                );
            }
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    

