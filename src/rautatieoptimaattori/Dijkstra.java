package rautatieoptimaattori;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {
    
    private Verkko verkko;
    
    public Dijkstra(Verkko v) {
        this.verkko = v;
    }

    // Varsinainen Dijkstran algoritmi
    void reitinPituus(String lahtopaikka, String maaranpaa) {
        
        // Asetetaan halutut alku- ja päätepisteet.
        Solmu alku = this.verkko.getSolmu(lahtopaikka);
        Solmu loppu = this.verkko.getSolmu(maaranpaa);

        // Luodaan keko ja tarvittavat HashMapit.
        HashMap<Solmu, Integer> etaisyydet = new HashMap<>();
        HashMap<Solmu, Boolean> kasitelty = new HashMap<>();
        PriorityQueue<VertailtavaSolmu> keko = new PriorityQueue();

        // Lisätään lähtöpiste kekoon ja etäisyysarvioon.
        keko.add(new VertailtavaSolmu( alku, 0 ));
        etaisyydet.put(alku, 0);

        while (!keko.isEmpty()) {

            // Napataan käsiteltävä solmu.
            Solmu kasiteltava = keko.poll().getSolmu();

            // Onko solmua käsitelty vielä? Jos on, hypätään yli.
            if (kasitelty.containsKey(kasiteltava)) {
                continue;
            }

            // Muutoin käydään läpi solmun naapurit.
            // Miksi tässä ei voi käyttää getNaapurit-metodia? Tulee virheilmoitus.
            for (HashMap.Entry<Solmu, Integer> naapuri : kasiteltava.naapurit.entrySet()) {

              // Onko naapurisolmu jo etäisyysarviotaulukossa?
              Solmu tutkittavaNaapuri = naapuri.getKey();
              int uusi = etaisyydet.get(kasiteltava) + kasiteltava.getEtaisyys(tutkittavaNaapuri);

              // Ei ole; lisää se etäisyysarviointitaulukkoon ja kekoon.
              if(! etaisyydet.containsKey(tutkittavaNaapuri)) {
                etaisyydet.put(tutkittavaNaapuri, uusi);
                keko.add(new VertailtavaSolmu(tutkittavaNaapuri, uusi));

                continue;
              }

              // On; verrataan uutta ja vanhaa etäisyyttä
              int vanha = etaisyydet.get(tutkittavaNaapuri);
              if (uusi < vanha) {
                  etaisyydet.put(tutkittavaNaapuri, uusi);

                  // Siirrytään tutkimaan naapuria.
                  keko.add(new VertailtavaSolmu(tutkittavaNaapuri, uusi));
              }

            }

            kasitelty.put(kasiteltava, true);

        }

        System.out.println("Etäisyys " + alku.getNimi() + "-" + loppu.getNimi() + " on " + etaisyydet.get(loppu) + " km.");
    }

}
