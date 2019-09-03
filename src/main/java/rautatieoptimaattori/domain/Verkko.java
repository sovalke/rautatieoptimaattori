package rautatieoptimaattori.domain;

import rautatieoptimaattori.tietorakenteet.OmaHashMap;
import rautatieoptimaattori.tietorakenteet.OmaLista;
import rautatieoptimaattori.tietorakenteet.OmaPari;

public class Verkko {

    private final OmaHashMap<Integer, Solmu> solmut = new OmaHashMap<>();
    
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
     * Palauttaa verkon asemat oliolistana.
     * @return Omalista
     */
    public Solmu[] asemat() {
        Solmu[] asemat = new Solmu[this.getKoko()];
        int i = 0;
        
        for (Object naapuri : solmut.entrySet()) {
            if (naapuri != null || i < asemat.length) {
                OmaPari pari = (OmaPari) naapuri;
                asemat[i] = (Solmu) pari.getValue();
                i++;
            }
        }
        return asemat;
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
        
        for (Object entry : this.solmut.entrySet()) {
            OmaPari tutk = (OmaPari) entry;
            Solmu tulos = (Solmu) tutk.getValue();
            OmaHashMap<Solmu, Long> naapurit = (OmaHashMap) tulos.getNaapurit();

            for (Object naapuri : naapurit.entrySet()) {
                OmaPari tutk2 = (OmaPari) naapuri;
                
                long erotus = (long) tutk2.getValue();
                Solmu maaranpaa = (Solmu) tutk2.getKey();
                lista.add(tulos.getNimi() + "-" + maaranpaa.getNimi() + " " + erotus);
            }
        }
        return lista;
    }

    /**
     * Tarkistaa, onko solmu olemassa.
     *
     * @param solmu tarkistettava solmu
     * @return true, jos löytyy
     * @throws java.lang.Exception
     */
    public Solmu onkoSolmua(Solmu solmu) throws Exception {
        Solmu loytynyt = getSolmu(solmu.getId());
        return loytynyt;
    }

    /**
     * Hakee halutun solmun.
     *
     * @param id haettavan solmun id
     * @return haettava solmu
     */
    public Solmu getSolmu(Integer id) {
        if (this.solmut.containsKey(id)) {
            return this.solmut.get(id);
        }
        return null;
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
