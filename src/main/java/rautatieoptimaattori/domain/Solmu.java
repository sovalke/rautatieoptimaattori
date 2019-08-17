package rautatieoptimaattori.domain;

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
    public int setId(int i) {
        this.id = i;
        return 1;
    }
    
    public int setNimi(String nomen) {
        if (nomen.isEmpty()) {
            return -1;
        }
        this.nimi = nomen;
        return 1;
    }

    public int setXY(long x, long y) {
        this.koordinaattiX = x;
        this.koordinaattiY = y;
        return 1;
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
