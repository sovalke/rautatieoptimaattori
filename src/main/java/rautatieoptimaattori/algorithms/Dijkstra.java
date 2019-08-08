package rautatieoptimaattori.algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import rautatieoptimaattori.Solmu;
import rautatieoptimaattori.Verkko;
import rautatieoptimaattori.VertailtavaSolmu;

public class Dijkstra {
    
    private Verkko verkko;
    
    public Dijkstra(Verkko v) {
        this.verkko = v;
    }

    // Varsinainen Dijkstran algoritmi
    public double reitinPituus(Integer lahtopaikka, Integer maaranpaa) throws Exception {
        
        // Asetetaan halutut alku- ja päätepisteet.
        Solmu alku = this.verkko.getSolmu(lahtopaikka);
        Solmu loppu = this.verkko.getSolmu(maaranpaa);

        // Luodaan keko ja tarvittavat HashMapit.
        HashMap<Solmu, Long> etaisyydet = new HashMap<>();
        HashMap<Solmu, Boolean> kasitelty = new HashMap<>();
        PriorityQueue<VertailtavaSolmu> keko = new PriorityQueue();

        // Lisätään lähtöpiste kekoon ja etäisyysarvioon.
        keko.add(new VertailtavaSolmu( alku, 0 ));
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
              if(! etaisyydet.containsKey(tutkittavaNaapuri)) {
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
        long tunnit = millisekunnit / (60 * 60 * 1000);
        long sekunnit = millisekunnit / 1000 % 60;
        long minuutit = millisekunnit / (60 * 1000) % 60;

        System.out.println("Dijkstra: etäisyys " + alku.getNimi() + "-" + loppu.getNimi() + " on " + tunnit + " h " + minuutit + " min " + sekunnit + " s (" + millisekunnit + " millisekuntia).");
        return etaisyydet.get(loppu);
    }

}