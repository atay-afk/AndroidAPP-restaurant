package com.example.ordertaking.login;

public class User {
    private String username,password,nom,prenom,fonction,tele,adresse;
    private static User instance=new User();

    public String getFonction() {
        return fonction;
    }

    public String getUsername() {
        return username;
    }

    public void setFonction(String f) {
        this.fonction = f;
    }

    public String getTele() {
        return tele;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", fonction='" + fonction + '\'' +
                '}';
    }

    public static User getInstance() {
        return instance;
    }

    public String getPassword() {
        return password;
    }

    public static void setInstance(User USER) {
        instance = USER;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
