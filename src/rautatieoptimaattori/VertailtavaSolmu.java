package rautatieoptimaattori;

public class VertailtavaSolmu implements Comparable {

    private Solmu solmu;
    private double etaisyys;

    VertailtavaSolmu(Solmu solmu, double etaisyys) {
        this.solmu = solmu;
        this.etaisyys = etaisyys;
    }

    Solmu getSolmu() {
        return this.solmu;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VertailtavaSolmu)) {
            return false;
        }
        VertailtavaSolmu s = (VertailtavaSolmu) o;
        return s.solmu.equals(this.solmu);
    }

    @Override
    public int compareTo(Object o) {
        VertailtavaSolmu toinen = (VertailtavaSolmu) o;
        return (int) (this.etaisyys - toinen.etaisyys);
    }
}
