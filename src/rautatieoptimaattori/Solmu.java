package rautatieoptimaattori;

import java.util.HashMap;

public class Solmu {

    public String nimi;
    public Integer kokonaisEtaisyys;
    HashMap<Solmu, Integer> naapurit = new HashMap<>();

    public Solmu(String nimi) {
        this.nimi = nimi;
        this.kokonaisEtaisyys = Integer.MAX_VALUE;
    }

    public void lisaaYhteys(Solmu paikka, int etaisyys) {
        naapurit.put(paikka, etaisyys);
    }

    public int getEtaisyys(Solmu paikka) {
        return naapurit.get(paikka);
    }

    public HashMap haeNaapurit() {
        return naapurit;
    }
    
    public int getKokonaisEtaisyys() {
        return this.kokonaisEtaisyys;
    }
    
    public void setKokonaisEtaisyys(int x) {
        if (x < this.kokonaisEtaisyys) {
            this.kokonaisEtaisyys = x;
        }
    }

}
