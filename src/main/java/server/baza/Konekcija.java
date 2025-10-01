/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Marija
 */
public class Konekcija {
    private static Konekcija instance;
    private Connection connection;

    private Konekcija() {
        try {
            // Relativna putanja do baze (u istom folderu kao aplikacija)
            String url = "jdbc:sqlite:MaskMy.db";
            connection = DriverManager.getConnection(url);
            
            // Uključi proveru stranih ključeva
            Statement st = connection.createStatement();
            st.execute("PRAGMA foreign_keys = ON;");
            st.close();
            
            //String url = "jdbc:mysql://localhost:3306/baza";
            //connection = DriverManager.getConnection(url, "root", "");
            connection.setAutoCommit(false);
            System.out.println("Uspesno povezano sa SQLite bazom!");
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }


}
