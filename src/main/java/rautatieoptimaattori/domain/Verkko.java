package rautatieoptimaattori.domain;

import java.util.HashMap;
import tietorakenteet.OmaLista;

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
    }

    // Lisätään yhteys kahden aseman välille.
    public int lisaaYhteys(Solmu a, Solmu b, long i) {
        try {
            a.lisaaYhteys(b, i);
            b.lisaaYhteys(a, i);
            return 1;
        } catch (NullPointerException ex) {
            return -1;
        }
    }

    // Palautetaan tunnetut yhteysvälit listana.
    public OmaLista Reitit() {
        OmaLista<String> lista = new OmaLista<>();
        
        for (HashMap.Entry<Integer, Solmu> entry : this.solmut.entrySet()) {
            Solmu tulos = entry.getValue();
            HashMap<Solmu, Long> naapurit = tulos.getNaapurit();

            for (HashMap.Entry<Solmu, Long> naapuri : naapurit.entrySet()) {
                long erotus = naapuri.getValue();
                long sekunnit = erotus / 1000 % 60;
                long minuutit = erotus / (60 * 1000) % 60;
                long tunnit = erotus / (60 * 60 * 1000);
                lista.add(tulos.getNimi() + "-" + naapuri.getKey().getNimi() + " " + tunnit + ":" + minuutit + ":" + sekunnit);
            }
        }
        return lista;
    }

    // Tarkistetaan, onko solmua olemassa.
    public boolean onkoSolmua(Solmu solmu) {
        return this.solmut.containsValue(solmu);
    }

    // Haetaan haluttu solmu.
    public Solmu getSolmu(Integer id) throws Exception {
        if (this.solmut.containsKey(id)) {
            return this.solmut.get(id);
        }
        throw new Exception("Asemaa ei löydy.");
    }

    // Haetaan tieto asemien lukumäärästä.
    public int getKoko() {
        return this.solmut.size();
    }

}
