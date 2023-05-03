package com.example.ordertaking.tools;

public class Table {
    private int num;
    private String responsable,etat;

    public int getNum() {
        return num;
    }

    public Table(){}

    public Table(int num, String responsable, String etat) {
        this.num = num;
        this.responsable = responsable;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Table{" +
                "num=" + num +
                ", responsable='" + responsable + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }

    public String getResponsable() {
        return responsable;
    }

    public String getEtat() {
        return etat;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
