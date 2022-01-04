package models;

import java.util.ArrayList;

public class Production {
    private char notTerminal;
    private ArrayList<String> products;


    public Production() {
        this.products = new ArrayList<String>();
    }

    public Production(char notTerminal, ArrayList<String> products) {
        this.notTerminal = notTerminal;
        this.products = products;
    }

    public void addProduction(String not) {

    }


    public char getNotTerminal() {
        return notTerminal;
    }


    public void setNotTerminal(char notTerminal) {
        this.notTerminal = notTerminal;
    }


    public ArrayList<String> getProducts() {
        return products;
    }


    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }
}
