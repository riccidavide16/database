/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ricci.davide
 */
public class Gestore {
    
    private String url = "jdbc:sqlite:scuola.db";
    
    public ArrayList<String> mostraClassi(){
        ArrayList<String> classi = new ArrayList<>();
        
          try(Connection conn = DriverManager.getConnection(url);  
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery("SELECT * FROM classi")) {
              
              while(rs.next()){
                 classi.add(rs.getString("id_classe"));
              }
          } 
          catch (Exception e) {
            e.printStackTrace();
        }
        
        return classi;
    }
    
    public ArrayList<String> leggiStudenti(String cb_value) {
        ArrayList<String> studenti = new ArrayList<>();
       String idClasseCercato = cb_value;

        String query = "SELECT id_alunno, nome, cognome FROM alunni WHERE id_classe = ?";

        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, idClasseCercato);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    studenti.add(rs.getString("nome") + " " + rs.getString("cognome"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studenti;
    }
     public String leggiPartecipazioneGita() {
         String risultato = " ";
        
        try (Connection conn = DriverManager.getConnection(url); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT alunni.nome, alunni.cognome, gite.destinazione, gite.prezzo, partecipanti.pagato "  + "FROM alunni JOIN partecipanti ON alunni.id_alunno = partecipanti.id_alunno JOIN gite ON partecipanti.id_gita = gite.id_gita")) {
               
                while (rs.next()) {
                    
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String destinazione = rs.getString("destinazione");
                double prezzo = rs.getDouble("prezzo");
                int pagato = rs.getInt("pagato");
                String pagatoTesto = " ";
                if (pagato == 1) {
                    pagatoTesto = "SI";
                } else {
                    pagatoTesto = "NO";
                }
                risultato += rs.getString("nome") + " "
                    + rs.getString("cognome") + " - "
                    + rs.getString("destinazione") + " - "
                    + rs.getDouble("prezzo") + "€\n" 
                    + "\n" ;
                
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return risultato;
    
     }
     public ResultSet leggiGite() {

    ResultSet rs = null;

    String query =
        "SELECT alunni.nome, " +
        "alunni.cognome, " +
        "alunni.id_classe, " +
        "gite.destinazione, " +
        "gite.prezzo, " +
        "partecipanti.pagato " +
        "FROM partecipanti " +
        "INNER JOIN alunni " +
        "ON partecipanti.id_alunno = alunni.id_alunno " +
        "INNER JOIN gite " +
        "ON partecipanti.id_gita = gite.id_gita";

    try {

        Connection conn = DriverManager.getConnection(url);

        PreparedStatement ps = conn.prepareStatement(query);

        rs = ps.executeQuery();

    } catch (Exception e) {

        e.printStackTrace();
    }

    return rs;
}
     public void eliminaPartecipanteGita(int idAlunno) {

    String query =
        "DELETE FROM partecipanti WHERE id_alunno = ?";

    try (

        Connection conn = DriverManager.getConnection(url);

        PreparedStatement ps =
            conn.prepareStatement(query)

    ) {

        ps.setInt(1, idAlunno);

        ps.executeUpdate();

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}
