package rautatieoptimaattori;

import java.util.HashMap;

public class Verkko {

    private HashMap<String, Solmu> solmut = new HashMap<>();

    // Lisätään uusi asema koordinaatteineen.
    void lisaaAsema(String nimi, int id, double x, double y) {
        if (solmut.containsKey(nimi)) {
            Solmu paivitettava = solmut.get(nimi);
            paivitettava.setXY(x, y);
            paivitettava.setId(id);
        } else {
            Solmu lisattava = new Solmu(nimi, id, x, y);
            solmut.put(lisattava.getNimi(), lisattava);
        }
        Solmu a = solmut.get(nimi);
        System.out.println("Lisätty asema " + a.getNimi() + " (" + a.getId() + "), koordinaatit " + a.getX() + " / " + a.getY());
    }

    // Lisää kahdensuuntainen yhteys asemien välille.
    void lisaaYhteys(String lahtopaikka, String maaranpaa, int i) {
        // Lisätään verkkoon lähtö- ja päätepisteet, jos ne puuttuvat.
        if (!solmut.containsKey(lahtopaikka)) {
            solmut.put(lahtopaikka, new Solmu(lahtopaikka));
        }
        if (!solmut.containsKey(maaranpaa)) {
            solmut.put(maaranpaa, new Solmu(maaranpaa));
        }

        // Lisätään kaari molempiin suuntiin.
        Solmu a = solmut.get(lahtopaikka);
        Solmu b = solmut.get(maaranpaa);
        lisaaYhteys(a, b, i);
    }

    void lisaaYhteys(Solmu a, Solmu b, int i) {
        a.lisaaYhteys(b, i);
        b.lisaaYhteys(a, i);
    }

    // Tulostetaan kaikki tunnetut yhteysvälit.
    void tulostaReitit() {
        for (HashMap.Entry<String, Solmu> entry : solmut.entrySet()) {
            Solmu tulos = entry.getValue();

            System.out.println(tulos.getNimi() + ": ");

            HashMap<Solmu, Integer> naapurit = tulos.getNaapurit();

            for (HashMap.Entry<Solmu, Integer> naapuri : naapurit.entrySet()) {
                System.out.println("    " + naapuri.getKey().getNimi() + " " + naapuri.getValue() + " km");
            }
        }
    }

    Solmu getSolmu(String nimi) {
        return this.solmut.get(nimi);
    }


}
