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
    
    public ArrayList<String> leggiStudenti(String stringaDallaCombo) {
        ArrayList<String> studenti = new ArrayList<>();
        String idClasseCercato = stringaDallaCombo.split(" - ")[0];

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
    
    
}
