package rautatieoptimaattori;

import java.util.HashMap;

public class Solmu {

    private String nimi;
    public HashMap<Solmu, Integer> naapurit = new HashMap<>();
    private double koordinaattiX;
    private double koordinaattiY;
    private int id;

    public Solmu(String nimi, int id, double x, double y) {
        this.nimi = nimi;
        this.id = id;
        this.koordinaattiX = x;
        this.koordinaattiY = y;
    }

    public void lisaaYhteys(Solmu paikka, int etaisyys) {
        naapurit.put(paikka, etaisyys);
    }

    // Setterit
    public void setId(int i) {
        this.id = i;
    }

    public void setXY(double x, double y) {
        this.koordinaattiX = x;
        this.koordinaattiY = y;
    }

    // Getterit
    public int getId() {
        return this.id;
    }

    public int getEtaisyys(Solmu paikka) {
        return naapurit.get(paikka);
    }

    public String getNimi() {
        return this.nimi;
    }

    public HashMap getNaapurit() {
        return naapurit;
    }

    public double getX() {
        return this.koordinaattiX;
    }

    public double getY() {
        return this.koordinaattiY;
    }

}
