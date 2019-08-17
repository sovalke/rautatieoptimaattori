package rautatieoptimaattori.algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.domain.VertailtavaSolmu;

public class Dijkstra {

    private Verkko verkko;

    public Dijkstra(Verkko v) {
        this.verkko = v;
    }

    // Varsinainen Dijkstran algoritmi
    public double reitinPituus(Solmu alku, Solmu loppu) throws Exception {

        if (!this.verkko.onkoSolmua(alku)) {
            throw new Exception("Kääk! Lähtöasemaa ei löydy!");
        }

        if (!this.verkko.onkoSolmua(loppu)) {
            throw new Exception("Kääk! Määränpääasemaa ei löydy!");
        }

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

            // Onko solmua käsitelty vielä? Jos on, hypätään yli.
            if (kasitelty.containsKey(kasiteltava)) {
                continue;
            }

            // Muutoin käydään läpi solmun naapurit.
            // Miksi tässä ei voi käyttää getNaapurit-metodia? Tulee virheilmoitus.
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

        // System.out.println("Dijkstra: etäisyys " + alku.getNimi() + "-" + loppu.getNimi() + " on " + millisekunnit + " ms.");
        return millisekunnit;
    }

}
