package rautatieoptimaattori;

import java.util.HashMap;

public class Solmu {

    public String nimi;
    HashMap<Solmu, Integer> naapurit = new HashMap<>();

    public Solmu(String nimi) {
        this.nimi = nimi;
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

}
