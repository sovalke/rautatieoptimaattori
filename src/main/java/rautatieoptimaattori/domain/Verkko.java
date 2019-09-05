package rautatieoptimaattori.domain;

import java.util.Arrays;
import rautatieoptimaattori.tietorakenteet.OmaHashMap;
import rautatieoptimaattori.tietorakenteet.OmaLista;
import rautatieoptimaattori.tietorakenteet.OmaPari;

public class Verkko {

    private final OmaHashMap<Integer, Solmu> solmut = new OmaHashMap<>();
    private final OmaHashMap<String, Solmu> solmut2 = new OmaHashMap<>();

    /**
     * Lisää uuden aseman koordinaatteineen.
     *
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

            String vanhaNimi = paivitettava.getNimi();
            solmut2.remove(vanhaNimi); // Poistetaan vanha nimi

            paivitettava.setNimi(nimi); // Tallennetaan uusi nimi
            solmut2.put(paivitettava.getNimi(), paivitettava); // Tallennetaan uusi nimi

        } else {
            Solmu lisattava = new Solmu(nimi, id, x, y);
            solmut.put(lisattava.getId(), lisattava);
            solmut2.put(lisattava.getNimi(), lisattava);
        }
        return solmut.get(id);
    }

    /**
     * Palauttaa verkon asemat taulukkona.
     *
     * @return Solmu[]
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
        Arrays.sort(asemat);
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
    public String[] reitit() {
        OmaLista<String> lista = new OmaLista<>();

        for (Object entry : this.solmut.entrySet()) {
            OmaPari tutk = (OmaPari) entry;
            Solmu tulos = (Solmu) tutk.getValue();
            OmaHashMap<Solmu, Long> naapurit = (OmaHashMap) tulos.getNaapurit();

            for (Object naapuri : naapurit.entrySet()) {
                OmaPari tutk2 = (OmaPari) naapuri;

                long erotus = (long) tutk2.getValue();
                Solmu maaranpaa = (Solmu) tutk2.getKey();
                lista.add(tulos.getNimi() + " - " + maaranpaa.getNimi());
            }
        }

        String[] valit = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            valit[i] = lista.value(i);
        }

        Arrays.sort(valit);
        return valit;
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

    public Solmu getSolmu(String nimi) {
        if (this.solmut2.containsKey(nimi)) {
            return this.solmut2.get(nimi);
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
