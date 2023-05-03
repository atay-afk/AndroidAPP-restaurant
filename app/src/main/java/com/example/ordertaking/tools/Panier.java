package com.example.ordertaking.tools;

import java.util.ArrayList;

public class Panier {

    public ArrayList<Meal> list=new ArrayList<>();
    private Table table;
    private static Panier instance;

    public static ArrayList<Panier> paniers=new ArrayList<>();

    public static Panier getInstance(){
        if(instance==null)
            instance=new Panier();
        return instance;
    }

    public static void setPaniers(ArrayList<Panier> paniers) {
        Panier.paniers = paniers;
    }
    public void addPanier(Panier p){
        paniers.add(p);
    }
    public void delPanier(Panier p){
        paniers.remove(p);
    }

    public static void setInstance(Panier instance) {
        Panier.instance = instance;
    }

    public void addMeal(Meal a){

            list.add(a);
    }

    public void delMeal(Meal b){
        list.remove(b);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public double getPrice(){
        double price=0;
        for(Meal meals:list){
            price=price+(meals.getPrix()* meals.getQuantity());
        }
        return price;
    }

    public ArrayList<Meal> getList() {
        return list;
    }

    public void setList(ArrayList<Meal> list) {
        this.list = list;
    }


}
