package rautatieoptimaattori.suorituskyky;

import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import rautatieoptimaattori.algorithms.Jarjestaja;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.io.Aineistokasittelija;

/**
 * Luokka, joka toteuttaa ohjelman suorituskykytestit.
 */
public class Suorituskykytesti {
    Aineistokasittelija data;
    Verkko verkko;
    Astar astar;
    Dijkstra dijkstra;

    /**
     * Konstruktori
     *
     * @throws java.lang.Exception
     */
    public Suorituskykytesti() throws Exception {
        this.data = new Aineistokasittelija();
        data.lisaaAsemat("./data/testdata/stations.csv");
        data.lisaaYhteydet("./data/testdata/trains.csv");
        this.verkko = data.getVerkko();
        this.astar = new Astar(this.verkko);
        this.dijkstra = new Dijkstra(this.verkko);
    }

    /**
     * Ajaa suorituskykytestin.
     */
    public void testaa() {

        try {
            Solmu alku = this.verkko.getSolmu(1);
            Solmu loppu = this.verkko.getSolmu(303);
            
            kulje(100, alku, loppu);
            kulje(1000, alku, loppu);
            kulje(10000, alku, loppu);
            
            loppu = this.verkko.getSolmu(347);
            kulje(100, alku, loppu);
            kulje(1000, alku, loppu);
            kulje(10000, alku, loppu);
            
            alku = this.verkko.getSolmu(73);
            loppu = this.verkko.getSolmu(28);
            kulje(100, alku, loppu);
            kulje(1000, alku, loppu);
            kulje(10000, alku, loppu);
            
            alku = this.verkko.getSolmu(364);
            loppu = this.verkko.getSolmu(130);
            kulje(100, alku, loppu);
            kulje(1000, alku, loppu);
            kulje(10000, alku, loppu);
            
            alku = this.verkko.getSolmu(1);
            kulje(100, alku, loppu);
            kulje(1000, alku, loppu);
            kulje(10000, alku, loppu);
        } catch (Exception ex) {
            System.out.println("VIRHE: " + ex.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Etsii reitin halutun määrän kertoja.
     *
     * @param krt Montako kertaa
     * @param alku Reitin alkuasema
     * @param loppu Reitin loppuasema
     * @throws java.lang.Exception
     */
    public void kulje(int krt, Solmu alku, Solmu loppu) throws Exception {
        System.out.println("-----------------------------------------");
        String lahtopaikka = alku.getNimi();
        String maaranpaa = loppu.getNimi();
        System.out.println("Kuljetaan " + krt + " kertaa väli " 
                + lahtopaikka + "-" + maaranpaa + "...");
            
        long[] ajat = new long[krt + 1];

        for (int i = 0; i < krt + 1; i++) {
            long alkuAika = System.nanoTime();
            this.dijkstra.reitinPituus(alku, loppu);
            long loppuAika = System.nanoTime();
            ajat[i] = loppuAika - alkuAika;
        }  
            
        Jarjestaja jarjestaja = new Jarjestaja();
        jarjestaja.jarjesta(ajat, 0, krt);
        System.out.println("Dijkstran mediaaniaika: " + ajat[krt / 2] + " millisekuntia");
            
        ajat = new long[krt + 1];
            
        for (int i = 0; i < krt + 1; i++) {
            long alkuAika = System.nanoTime();
            this.astar.reitinPituus(alku, loppu);
            long loppuAika = System.nanoTime();
            ajat[i] = loppuAika - alkuAika;
        }  
            
        Jarjestaja jarjestaja2 = new Jarjestaja();
        jarjestaja2.jarjesta(ajat, 0, krt);
        
        System.out.println("A*:n mediaaniaika: " + ajat[krt / 2] + " millisekuntia");
            
    }
}
