package rautatieoptimaattori;

import java.util.HashMap;

public class Verkko {

    private HashMap<Integer, Solmu> solmut = new HashMap<>();

    // Lisätään uusi asema koordinaatteineen.
    public Solmu lisaaAsema(String nimi, int id, double x, double y) {
        if (solmut.containsKey(id)) {
            Solmu paivitettava = solmut.get(id);
            long x1 = (long) x * 1000000;
            long y1 = (long) y * 1000000;
            paivitettava.setXY(x1, y1);
            paivitettava.setNimi(nimi);
        } else {
            Solmu lisattava = new Solmu(nimi, id, x, y);
            solmut.put(lisattava.getId(), lisattava);
        }
        return solmut.get(id);
        //System.out.println("Lisätty asema " + a.getNimi() + " (" + a.getId() + "), koordinaatit " + a.getX() + " / " + a.getY());
    }

    public int lisaaYhteys(Solmu a, Solmu b, long i) {
        try {
            a.lisaaYhteys(b, i);
            b.lisaaYhteys(a, i);
            return 1;
        } catch (NullPointerException ex) {
            return -1;
        }
        //System.out.println("Lisätty yhteys " + a.getNimi() + "-" + b.getNimi());
    }

    // Tulostetaan kaikki tunnetut yhteysvälit.
    public void tulostaReitit() {
        
        for (HashMap.Entry<Integer, Solmu> entry : this.solmut.entrySet()) {
            Solmu tulos = entry.getValue();

            HashMap<Solmu, Long> naapurit = tulos.getNaapurit();

            for (HashMap.Entry<Solmu, Long> naapuri : naapurit.entrySet()) {
                long erotus = naapuri.getValue();
                long sekunnit = erotus / 1000 % 60;
                long minuutit = erotus / (60 * 1000) % 60;
                long tunnit = erotus / (60 * 60 * 1000);
                System.out.println(tulos.getNimi() + "-" + naapuri.getKey().getNimi() + " " + tunnit + ":" + minuutit + ":" + sekunnit);
            }
        }
    }

    public boolean onkoSolmua(Solmu solmu) {
        return this.solmut.containsValue(solmu);
    }

    public Solmu getSolmu(Integer id) throws Exception {
        if (this.solmut.containsKey(id)) {
            return this.solmut.get(id);
        }

        throw new Exception("Asemaa ei löydy.");
    }

    public int getKoko() {
        return this.solmut.size();
    }

}
