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
    
    public ArrayList<String> leggiStudenti(String cb_value) {
        ArrayList<String> studenti = new ArrayList<>();
        String idClasseCercato = cb_value.split(" - ")[0];

        String query = "SELECT id_alunno, nome, cognome FROM alunni WHERE id_classe = ?";

        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, idClasseCercato);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    studenti.add(rs.getInt("id_alunno") + " - " + rs.getString("nome") + " " + rs.getString("cognome"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studenti;
    }
     public void leggiPartecipazioneGita() {
        System.out.println("elenco degli alunni che partecipano ad  una gita:");
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
                System.out.println(nome + " - " + cognome + " - " + destinazione + " - " + prezzo + " - " + pagatoTesto);
            }
        } catch (Exception e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
            e.printStackTrace();
        }
    
     }
}
