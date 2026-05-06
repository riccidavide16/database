/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package db;



/**
 *
 * @author ricci.davide
 */
public class Db {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
    
}
