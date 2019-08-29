package rautatieoptimaattori.domain;

import java.util.HashMap;
import tietorakenteet.OmaLista;

public class Verkko {

    private final HashMap<Integer, Solmu> solmut = new HashMap<>();
    
    /**
     * Lisää uuden aseman koordinaatteineen.
     * @param nimi aseman nimi
     * @param id aseman tunnus
     * @param x aseman x-koordinaatti
     * @param y aseman y-koordinaatti
     * @return lisätty solmu (olio)
     */
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

    
    /**
     * Lisää yhteyden kahden aseman välille.
     *
     * @param asemaA ensimmäinen solmu
     * @param asemaB toinen solmu
     * @param aika solmujen välinen etäisyys (millisekunteina)
     * @return 1, jos onnistuu
     */
    public int lisaaYhteys(Solmu asemaA, Solmu asemaB, long aika) {
        try {
            asemaA.lisaaYhteys(asemaB, aika);
            asemaB.lisaaYhteys(asemaA, aika);
            return 1;
        } catch (NullPointerException ex) {
            return -1;
        }
    }
    
    /**
     * Palauttaa tunnetut yhteysvälit listana.
     *
     * @return OmaLista yhteyksistä
     */
    public OmaLista reitit() {
        OmaLista<String> lista = new OmaLista<>();
        
        for (HashMap.Entry<Integer, Solmu> entry : this.solmut.entrySet()) {
            Solmu tulos = entry.getValue();
            HashMap<Solmu, Long> naapurit = tulos.getNaapurit();

            for (HashMap.Entry<Solmu, Long> naapuri : naapurit.entrySet()) {
                long erotus = naapuri.getValue();
                lista.add(tulos.getNimi() + "-" + naapuri.getKey().getNimi() + " " + erotus);
            }
        }
        return lista;
    }

    /**
     * Tarkistaa, onko solmu olemassa.
     *
     * @param solmu tarkistettava solmu
     * @return true, jos löytyy
     */
    public boolean onkoSolmua(Solmu solmu) {
        return this.solmut.containsValue(solmu);
    }

    /**
     * Hakee halutun solmun.
     *
     * @param id haettavan solmun id
     * @return haettava solmu
     * @throws java.lang.Exception
     */
    public Solmu getSolmu(Integer id) throws Exception {
        if (this.solmut.containsKey(id)) {
            return this.solmut.get(id);
        }
        throw new Exception("Asemaa ei löydy.");
    }

    /**
     * Hakee asemien lukumäärän.
     *
     * @return int solmujen lkm
     */
    public int getKoko() {
        return this.solmut.size();
    }

}
