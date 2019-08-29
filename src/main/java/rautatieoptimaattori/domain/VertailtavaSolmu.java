package rautatieoptimaattori.domain;

import java.util.Objects;

public class VertailtavaSolmu implements Comparable {

    private final Solmu solmu;
    public double etaisyys;

    /**
     * Konstruktori.
     *
     * @param solmu lisättävä solmu
     * @param etaisyys solmun etäisyys
     */
    public VertailtavaSolmu(Solmu solmu, double etaisyys) {
        this.solmu = solmu;
        this.etaisyys = etaisyys;
    }

    /**
     * Hakee solmun.
     *
     * @return solmu
     */
    public Solmu getSolmu() {
        return this.solmu;
    }

    /**
     * Tarkistaa, onko solmu sama kuin tutkittava objekti o.
     * 
     * @param o tutkittava objekti
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VertailtavaSolmu)) {
            return false;
        }
        VertailtavaSolmu s = (VertailtavaSolmu) o;
        return s.solmu.equals(this.solmu);
    }

    /**
     * Luo hash-koodin solmulle.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.solmu);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.etaisyys) ^ (Double.doubleToLongBits(this.etaisyys) >>> 32));
        return hash;
    }

    /**
     * Vertailee solmua ja objektia o: kumpi on suurempi?
     */
    @Override
    public int compareTo(Object o) {
        VertailtavaSolmu toinen = (VertailtavaSolmu) o;
        return (int) (this.etaisyys - toinen.etaisyys);
    }
}
