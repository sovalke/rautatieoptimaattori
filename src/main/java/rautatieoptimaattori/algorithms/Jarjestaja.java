package rautatieoptimaattori.algorithms;

public class Jarjestaja {

    private long[] longApu;
    private long[] longTaulu;

    /**
     * Lomitusjärjestämiseen perustuva järjestämisalgoritmi.
     */
    public Jarjestaja() {

    }

    /**
     * Järjestämisalgoritmi.
     * @param luvut järjestettävä taulukko
     * @param alku indeksi, josta järjestäminen alkaa
     * @param loppu indeksi, johon järjestäminen loppuu
     * @return järjestetty taulukko
     */
    public long[] jarjesta(long[] luvut, int alku, int loppu) {
        longTaulu = luvut;
        longApu = new long[luvut.length];
        jarjesta2(longTaulu, alku, loppu);
        return longTaulu;
    }

    /**
     * Järjestää annetun osuuden taulukosta.
     */
    private boolean jarjesta2(long[] luvut, int alku, int loppu) {
        if (loppu == alku || loppu <= 0) {
            // Ei tarvitse järjestää: lukuja on 0 tai 1
            return false;
        }

        int jakoKohta = alku + ((loppu - alku) / 2);

        if (loppu == alku + 1) {
            if (luvut[alku] > luvut[loppu]) {
                long isompi = luvut[alku];
                luvut[alku] = luvut[loppu];
                luvut[loppu] = isompi;
            }
        } else {
            // Järjestetään väli alku ... jakokohta:
            jarjesta2(luvut, alku, jakoKohta);

            // Järjestetään väli jakokohta + 1 ... loppu:
            jarjesta2(luvut, jakoKohta + 1, loppu);
        }
        lomita(alku, jakoKohta, jakoKohta + 1, loppu);
        return true;
    }

    /**
     * Lomittaa luvut.
     */
    private void lomita(int alku1, int loppu1, int alku2, int loppu2) {
        int alku = alku1;
        int loppu = loppu2;

        for (int i = alku; i < loppu; i++) {

            if (alku2 > loppu2 || (alku1 <= loppu1 && longTaulu[alku1] < longTaulu[alku2])) {
                longApu[i] = longTaulu[alku1];
                alku1++;
            } else {
                longApu[i] = longTaulu[alku2];
                alku2++;
            }
        }

        for (int i = alku; i < loppu; i++) {
            longTaulu[i] = longApu[i];
        }

    }

}
