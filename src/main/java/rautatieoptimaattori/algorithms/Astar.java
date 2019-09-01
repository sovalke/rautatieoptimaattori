package rautatieoptimaattori.algorithms;

import java.util.HashMap;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.domain.VertailtavaSolmu;
import rautatieoptimaattori.tietorakenteet.OmaHashMap;
import rautatieoptimaattori.tietorakenteet.OmaKeko;

public class Astar {

    private final Verkko verkko;

    /**
     * Konstruktori.
     *
     * @param v käytettävä verkko
     */
    public Astar(Verkko v) {
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

            Solmu kasiteltava = keko.poll().getSolmu();

            if (kasiteltava.equals(loppu)) {
                break;
            }

            // Onko solmua käsitelty vielä? Jos on, hypätään yli.
            if (kasitelty.containsKey(kasiteltava)) {
                continue;
            }

            // Muutoin käydään läpi solmun naapurit.
            for (HashMap.Entry<Solmu, Long> naapuri : kasiteltava.naapurit.entrySet()) {

                // Onko naapurisolmu jo etäisyysarviotaulukossa?
                Solmu tutkittavaNaapuri = naapuri.getKey();
                long uusi = etaisyydet.get(kasiteltava) 
                        + kasiteltava.getEtaisyys(tutkittavaNaapuri);

                // Ei ole; lisää se etäisyytaulukkoon ja kekoon.
                if (!etaisyydet.containsKey(tutkittavaNaapuri)) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);

                    double arvio = uusi + etaisyysArvio(tutkittavaNaapuri, loppu);
                    keko.add(new VertailtavaSolmu(tutkittavaNaapuri, arvio));
                    continue;
                }

                // On; verrataan uutta ja vanhaa etäisyyttä
                double vanha = etaisyydet.get(tutkittavaNaapuri);
                if (uusi < vanha) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);
                    double arvio = uusi + etaisyysArvio(tutkittavaNaapuri, loppu);

                    // Siirrytään tutkimaan naapuria.
                    keko.add(new VertailtavaSolmu(tutkittavaNaapuri, arvio));
                }

            }

            kasitelty.put(kasiteltava, true);

        }
        long millisekunnit = etaisyydet.get(loppu);
        return millisekunnit;
    }

    /**
     * Laskee määränpäälle etäisyysarvion.
     *
     * @param piste : piste, jossa ollaan
     * @param maaranpaa : piste, johon halutaan
     */
    private double etaisyysArvio(Solmu piste, Solmu maaranpaa) {
        // Hyödynnetään Pythagoraan lausetta etäisyysarvion laskemiseen.
        double x = maaranpaa.getX() - piste.getX();
        double y = maaranpaa.getY() - piste.getY();
        double etaisyysArvio = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return etaisyysArvio;
    }
}
