package rautatieoptimaattori.domain;

import rautatieoptimaattori.tietorakenteet.OmaHashMap;

/**
 * Asemaa eli solmua kuvaava luokka.
 */
public class Solmu {

    private String nimi;
    public OmaHashMap<Solmu, Long> naapurit = new OmaHashMap<>();
    private long koordinaattiX;
    private long koordinaattiY;
    private int id;

    /**
     * Konstruktori: luo uuden solmun.
     *
     * @param nimi Aseman nimi
     * @param id Aseman tunniste
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Solmu(String nimi, int id, double x, double y) {
        this.nimi = nimi;
        this.id = id;
        double muunnosX = x * 1000000;
        double muunnosY = y * 1000000;
        this.koordinaattiX = (long) muunnosX;
        this.koordinaattiY = (long) muunnosY;
    }

    /**
     * Lisää kahdensuuntaisen yhteyden haluttuun solmuun.
     *
     * @param paikka haluttu paikka
     * @param etaisyys etäisyys (millisekunteina)
     */
    public void lisaaYhteys(Solmu paikka, long etaisyys) {
        naapurit.put(paikka, etaisyys);
    }

    /**
     * Asettaa solmulle uuden id:n.
     *
     * @param id uusi id
     * @return 1, jos onnistuu
     */
    public int setId(int id) {
        this.id = id;
        return 1;
    }

    /**
     * Asettaa solmulle uuden nimen.
     *
     * @param nimi uusi nimi
     * @return 1, jos onnistuu
     */
    public int setNimi(String nimi) {
        if (nimi.isEmpty()) {
            return -1;
        }
        this.nimi = nimi;
        return 1;
    }

    /**
     * Asettaa solmulle uudet koordinaatit.
     *
     * @param x uusi x-koordinaatti
     * @param y uusi y-koordinaatti
     * @return 1, jos onnistuu
     */
    public int setXY(long x, long y) {
        this.koordinaattiX = x;
        this.koordinaattiY = y;
        return 1;
    }

    /**
     * Hakee solmun tunnisteen.
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Hakee solmun etäisyyden halutusta paikasta.
     *
     * @param paikka haettava solmu
     * @return etäisyys (millisekunteina)
     */
    public long getEtaisyys(Solmu paikka) {
        return naapurit.get(paikka);
    }

    /**
     * Hakee solmun nimen.
     *
     * @return nimi
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     * Hakee solmun X-koordinaatin.
     *
     * @return double koordinaatti
     */
    public double getX() {
        return this.koordinaattiX;
    }

    /**
     * Hakee solmun Y-koordinaatin.
     *
     * @return double koordinaatti
     */
    public double getY() {
        return this.koordinaattiY;
    }

    /**
     * Hakee solmun naapurit.
     *
     * @return naapurit OmaHashMapina.
     */
    public OmaHashMap getNaapurit() {
        return naapurit;
    }

}
