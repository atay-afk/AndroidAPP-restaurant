package com.example.ordertaking.tools;


import com.example.ordertaking.database.dbAcess;

import java.util.ArrayList;

public class Meal {
    public boolean isSelected;
    String description,designation,categorie;

    public static ArrayList<Meal> getBurgers() {
        return burgers;
    }

    public static ArrayList<Meal> getTacos() {
        return tacos;
    }

    public static ArrayList<Meal> getSandwich() {
        return sandwich;
    }

    public static ArrayList<Meal> getPizzas() {
        return pizzas;
    }

    public static ArrayList<Meal> getPlats() {
        return plats;
    }

    public static ArrayList<Meal> getPasta() {
        return pasta;
    }

    public static ArrayList<Meal> getSalades() {
        return salades;
    }

    public static ArrayList<Meal> getBoisson() {
        return boisson;
    }

    Double prix;
    int quantity=1;
    public static ArrayList<Meal> burgers=new ArrayList<>(),tacos=new ArrayList<>(),sandwich=new ArrayList<>()
            ,pizzas=new ArrayList<>(),plats=new ArrayList<>(),pasta=new ArrayList<>(),salades=new ArrayList<>(),boisson=new ArrayList<>();

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getDesignation() {
        return designation;
    }

    public Double getPrix() {
        return prix;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public static void setNull(){
        boisson.clear();
        burgers.clear();
        tacos.clear();
        sandwich.clear();
        pizzas.clear();
        salades.clear();
        pasta.clear();
        plats.clear();
    }

    public static void setMeals() {

        for(Meal meal: dbAcess.products){

            //burgers
            if(meal.getCategorie().equals("hamburger"))
                burgers.add(meal);
            //tacos
            if(meal.getCategorie().equals("tacos"))
                tacos.add(meal);
            //sandwich
            if(meal.getCategorie().equals("Sandwich"))
                sandwich.add(meal);
            //pizza
            if(meal.getCategorie().equals("Pizza"))
                pizzas.add(meal);
            //plat
            if(meal.getCategorie().equals("Plat"))
                plats.add(meal);
            //pasta
            if(meal.getCategorie().equals("Pasta "))
                pasta.add(meal);
            //salade
            if(meal.getCategorie().equals("Salade"))
                salades.add(meal);
            //boisson
            if(meal.getCategorie().equals("Boissons"))
                boisson.add(meal);
        }
    }

}
