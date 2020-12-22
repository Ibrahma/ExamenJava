package com.java;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    public Connection connexion;

    public void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/utilisateur", "root", null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String hashFunction(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText 
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean Connexion(user u) {
        boolean bool = false;
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();
        try {
            String req1 = "SELECT `email`, `password` FROM `user` WHERE `email`='" + u.getEmail() + "'";
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req1);
            String log = null, pwd = null;
            while (resultat.next()) {
                log = resultat.getString("email");
                pwd = resultat.getString("password");
            }
            if (pwd.equals(hashFunction(u.getPassword()))) {
                bool = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }

    public boolean ajouterUtilisateur(user u) {
        loadDatabase();
        boolean bool = false;
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO `user`( `nom`, `prenom`, `email`, `password`, `Telephone`, `Adresse`) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, u.getNom());
            preparedStatement.setString(2, u.getPrenom());
            preparedStatement.setString(3, u.getEmail());
            preparedStatement.setString(4, hashFunction(u.getPassword()));
            preparedStatement.setString(5, u.getTelephone());
            preparedStatement.setString(6, u.getAdresse());
            int i = preparedStatement.executeUpdate();
            bool = i == 1;
            //System.err.println(""+b);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public List<user> recupererUtilisateurs() {
        List<user> utilisateurs = new ArrayList<user>();
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT `id`, `nom`, `prenom`, `email`, `Telephone`, `Adresse` FROM `user` ;");

            // Récupération des données
            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                String Tel = resultat.getString("Telephone");
                String ad = resultat.getString("Adresse");
                user u = new user();
                u.setId(id);
                u.setNom(nom);
                u.setPrenom(prenom);
                u.setEmail(email);
                u.setTelephone(Tel);
                u.setAdresse(ad);
                utilisateurs.add(u);
            }
        } catch (SQLException e) {

        }

        return utilisateurs;
    }

    public boolean deleteUser(user u) throws SQLException {
        String sql = "DELETE FROM user where id = ?";

        loadDatabase();
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setInt(1,u.getId());
        boolean rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }
      public boolean updateUser(user u) throws SQLException {
        String sql = "UPDATE `user` SET `nom`=?,`prenom`=?,`email`=?,`password`=?,`Telephone`=?,`Adresse`=? WHERE `id`=?";
        loadDatabase();
         
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, u.getNom());
        statement.setString(2, u.getPrenom());
        statement.setString(3, u.getEmail());
        statement.setString(4, hashFunction(u.getPassword()));
        statement.setString(5, u.getTelephone());
        statement.setString(6, u.getAdresse());
        statement.setInt(7, u.getId());
        boolean rowUpdated = statement.executeUpdate() > 0;
       return rowUpdated;     
    }
    public static void main(String[] args) throws SQLException {
        new ConnectionDB().loadDatabase();
        user users = new user( "ly"," ibrahima", "ibrahima", "passer", "775674874", "yeumbeul");
        boolean b = new ConnectionDB().ajouterUtilisateur(users);
        List<user> all = new ConnectionDB().recupererUtilisateurs();
        System.out.println(""+b);
    }
     
}
