package rautatieoptimaattori;

import java.util.HashMap;

public class Solmu {

    private String nimi;
    public HashMap<Solmu, Long> naapurit = new HashMap<>();
    private long koordinaattiX;
    private long koordinaattiY;
    private int id;

    public Solmu(String nimi, int id, double x, double y) {
        this.nimi = nimi;
        this.id = id;
        double muunnosX = x * 1000000;
        double muunnosY = y * 1000000;
        this.koordinaattiX = (long) muunnosX;
        this.koordinaattiY = (long) muunnosY;
    }

    public void lisaaYhteys(Solmu paikka, long etaisyys) {
        naapurit.put(paikka, etaisyys);
    }

    // Setterit
    public void setId(int i) {
        this.id = i;
    }
    
    public void setNimi(String nomen) {
        this.nimi = nomen;
    }

    public void setXY(long x, long y) {
        this.koordinaattiX = x;
        this.koordinaattiY = y;
    }

    // Getterit
    public int getId() {
        return this.id;
    }

    public long getEtaisyys(Solmu paikka) {
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
