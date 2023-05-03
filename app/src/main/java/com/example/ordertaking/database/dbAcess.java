package com.example.ordertaking.database;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.ordertaking.tools.Meal;
import com.example.ordertaking.tools.Panier;
import com.example.ordertaking.tools.Table;
import com.example.ordertaking.login.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class dbAcess {

    public static ArrayList<User> users=new ArrayList<>();
    public static ArrayList<Meal> products=new ArrayList<>();
    public static ArrayList<Table> tables=new ArrayList<>();

    public static Connection connect(){
        Connection connect;
        ConnectionHelper connectionHelper=new ConnectionHelper();
        connect=connectionHelper.connectionclass();
        return connect;
    }

    public static void DBConnect(){
        try {
            connect();
            tables.clear();
            products.clear();
            users.clear();
            if(connect()!=null){

                String query1="select categorie,description,designation,prix from Meal ";
                Statement st=connect().createStatement();
                ResultSet rs= st.executeQuery(query1);
                while(rs.next()){
                    Meal product=new Meal();

                    product.setCategorie(rs.getString("categorie"));
                    product.setDescription(rs.getString("description"));
                    product.setDesignation(rs.getString("designation"));
                    product.setPrix(rs.getDouble("prix"));

                    products.add(product);

                }

                query1="select responsable,num,etat from Tables";
                st=connect().createStatement();
                rs= st.executeQuery(query1);
                while(rs.next()){
                    Table table=new Table();

                    table.setResponsable(rs.getString("responsable"));
                    table.setNum(rs.getInt("num"));
                    table.setEtat(rs.getString("etat"));

                    tables.add(table);

                }

                query1="select username,password,nom,prenom,fonction,adresse,numTel from Users ";
                st=connect().createStatement();
                rs= st.executeQuery(query1);
                while(rs.next()){
                    User user=new User();

                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setFonction(rs.getString("fonction"));
                    user.setTele(rs.getString("numTel"));
                    user.setAdresse(rs.getString("adresse"));

                    users.add(user);

                }


            }
        }
        catch (Exception ex) {
            Log.e("error",ex.getMessage());
        }
    }

    public static void updateTable(String a,int b){
        try {
            connect();
            String SQLUpdate="UPDATE Tables SET etat='"+a+"' WHERE num="+b;
            Statement sta=connect().createStatement();
            int count=sta.executeUpdate(SQLUpdate);
            tables.clear();
            products.clear();
            users.clear();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateUser(String a,String b,String c,String d,String username){
        try {
            connect();
            String SQLUpdate="UPDATE Users SET nom='"+a+"',prenom='"+b+"',adresse='"+c+"',numTel='"+d+"' WHERE username='"+username+"'";
            Statement sta=connect().createStatement();
            int count=sta.executeUpdate(SQLUpdate);
            tables.clear();
            products.clear();
            users.clear();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateStatus(String a,String username){
        try {
            connect();
            String SQLUpdate="UPDATE Users SET status='"+a+"' WHERE username='"+username+"'";
            Statement sta=connect().createStatement();
            int count=sta.executeUpdate(SQLUpdate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)


    public static void updateVente(Panier panier){
        try {
            connect();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            for(Meal meals:panier.list) {
                double price = meals.getPrix() * meals.getQuantity();
                String SQLUpdate = "INSERT INTO VENTE(designation,quantite,prixVente,total,date)" +
                        " VALUES ('" + meals.getDesignation() + "','" +meals.getQuantity()+ "','" +meals.getPrix()+"','" +price+ "','" +dtf.format(now)+"')";
                Statement sta = connect().createStatement();
                int count = sta.executeUpdate(SQLUpdate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
