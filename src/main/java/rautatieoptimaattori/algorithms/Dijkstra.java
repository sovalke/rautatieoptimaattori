package rautatieoptimaattori.algorithms;

import java.util.HashMap;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.domain.VertailtavaSolmu;
import tietorakenteet.OmaHashMap;
import tietorakenteet.OmaKeko;

public class Dijkstra {

    private final Verkko verkko;

    /**
     * Konstruktori.
     *
     * @param v käytettävä verkko
     */
    public Dijkstra(Verkko v) {
        this.verkko = v;
    }

    /**
     * Laskee parhaan reitin pituuden.
     *
     * @param alku alkusolmu
     * @param loppu loppusolmu
     * @return reitin pituus (millisekunteina)
     * @throws java.lang.Exception
     */
    public long reitinPituus(Solmu alku, Solmu loppu) throws Exception {

        if (!this.verkko.onkoSolmua(alku)) {
            throw new Exception("Kääk! Lähtöasemaa ei löydy!");
        }

        if (!this.verkko.onkoSolmua(loppu)) {
            throw new Exception("Kääk! Määränpääasemaa ei löydy!");
        }

        OmaHashMap<Solmu, Long> etaisyydet = new OmaHashMap<>();
        OmaHashMap<Solmu, Boolean> kasitelty = new OmaHashMap<>();
        OmaKeko keko = new OmaKeko(this.verkko.getKoko());

        // Lisätään lähtöpiste kekoon ja etäisyysarvioon.
        keko.add(new VertailtavaSolmu(alku, 0));
        etaisyydet.put(alku, 0L);

        while (!keko.isEmpty()) {

            // Napataan käsiteltävä solmu.
            Solmu kasiteltava = keko.poll().getSolmu();

            // Onko solmua käsitelty vielä? Jos on, hypätään yli.
            if (kasitelty.containsKey(kasiteltava)) {
                continue;
            }

            // Muutoin käydään läpi solmun naapurit.
            for (HashMap.Entry<Solmu, Long> naapuri : kasiteltava.naapurit.entrySet()) {

                // Onko naapurisolmu jo etäisyysarviotaulukossa?
                Solmu tutkittavaNaapuri = naapuri.getKey();
                long uusi = etaisyydet.get(kasiteltava) + kasiteltava.getEtaisyys(tutkittavaNaapuri);

                // Ei ole; lisää se etäisyysarviointitaulukkoon ja kekoon.
                if (!etaisyydet.containsKey(tutkittavaNaapuri)) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);
                    keko.add(new VertailtavaSolmu(tutkittavaNaapuri, uusi));

                    continue;
                }

                // On; verrataan uutta ja vanhaa etäisyyttä
                long vanha = etaisyydet.get(tutkittavaNaapuri);
                if (uusi < vanha) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);

                    // Siirrytään tutkimaan naapuria.
                    keko.add(new VertailtavaSolmu(tutkittavaNaapuri, uusi));
                }

            }

            kasitelty.put(kasiteltava, true);

        }
        long millisekunnit = etaisyydet.get(loppu);

        return millisekunnit;
    }

}
