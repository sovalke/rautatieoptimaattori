package rautatieoptimaattori.algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.domain.VertailtavaSolmu;

public class Astar {

    private Verkko verkko;

    public Astar(Verkko v) {
        this.verkko = v;
    }

    // Varsinainen algoritmi
    public double reitinPituus(Solmu alku, Solmu loppu) throws Exception {

        if (! this.verkko.onkoSolmua(alku) ) {
            throw new Exception("Kääk! Lähtöasemaa ei löydy!");
        }

        if (! this.verkko.onkoSolmua(loppu)) {
            throw new Exception("Kääk! Määränpääasemaa ei löydy!");
        }

        // System.out.println("Etäisyysarvio matkalle " + alku.getNimi() + "-" + loppu.getNimi() + " on " + etaisyysArvio(alku, loppu));

        // Luodaan keko ja tarvittavat HashMapit.
        HashMap<Solmu, Long> etaisyydet = new HashMap<>();
        HashMap<Solmu, Boolean> kasitelty = new HashMap<>();
        PriorityQueue<VertailtavaSolmu> keko = new PriorityQueue();

        // Lisätään lähtöpiste kekoon ja etäisyysarvioon.
        keko.add(new VertailtavaSolmu(alku, 0));
        etaisyydet.put(alku, 0L);

        while (!keko.isEmpty()) {

            // Napataan käsiteltävä solmu.
            Solmu kasiteltava = keko.poll().getSolmu();

            if (kasiteltava.equals(loppu)) {
                // System.out.println("Ollaan päästy maaliin!");
                break;
            }

            // Onko solmua käsitelty vielä? Jos on, hypätään yli.
            if (kasitelty.containsKey(kasiteltava)) {
                //System.out.println("---hypätään solmun " + kasiteltava + " yli");
                continue;
            }

            // Muutoin käydään läpi solmun naapurit.
            // Miksi tässä ei voi käyttää getNaapurit-metodia? Tulee virheilmoitus.
            for (HashMap.Entry<Solmu, Long> naapuri : kasiteltava.naapurit.entrySet()) {

                // Onko naapurisolmu jo etäisyysarviotaulukossa?
                Solmu tutkittavaNaapuri = naapuri.getKey();
                long uusi = etaisyydet.get(kasiteltava) + kasiteltava.getEtaisyys(tutkittavaNaapuri);

                // Ei ole; lisää se etäisyytaulukkoon ja kekoon.
                if (!etaisyydet.containsKey(tutkittavaNaapuri)) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);

                    double arvio = uusi + etaisyysArvio(tutkittavaNaapuri, loppu);
                    keko.add(new VertailtavaSolmu(tutkittavaNaapuri, arvio));
                    // System.out.println(uusi + " / " + arvio);

                    continue;
                }

                // On; verrataan uutta ja vanhaa etäisyyttä
                double vanha = etaisyydet.get(tutkittavaNaapuri);
                if (uusi < vanha) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);
                    double arvio = uusi + etaisyysArvio(tutkittavaNaapuri, loppu);

                    // Siirrytään tutkimaan naapuria.
                    keko.add(new VertailtavaSolmu(tutkittavaNaapuri, arvio));
                    //System.out.println(arvio);
                }

            }

            kasitelty.put(kasiteltava, true);

        }
        long millisekunnit = etaisyydet.get(loppu);
        // System.out.println("A*:       etäisyys " + alku.getNimi() + "-" + loppu.getNimi() + " on " + millisekunnit + " ms.");
        return millisekunnit;
    }

    private double etaisyysArvio(Solmu piste, Solmu maaranpaa) {
        // Hyödynnetään Pythagoraan lausetta etäisyysarvion laskemiseen.

        double x = maaranpaa.getX() - piste.getX();
        double y = maaranpaa.getY() - piste.getY();

        // etaisyysaArvio = neliöjuuri(x^2 + y^2)
        double etaisyysArvio = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return etaisyysArvio;
    }
}