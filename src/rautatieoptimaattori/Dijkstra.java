package rautatieoptimaattori;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {
    HashMap<String, Solmu> solmut = new HashMap<>();

    void lisaaYhteys(String lahtopaikka, String maaranpaa, int i) {
        // Lisätään verkkoon lähtö- ja päätepisteet, jos ne puuttuvat.
        if (!solmut.containsKey(lahtopaikka)) {
            solmut.put(lahtopaikka, new Solmu(lahtopaikka));
        }
        if (!solmut.containsKey(maaranpaa)) {
            solmut.put(maaranpaa, new Solmu(maaranpaa));
        }

        // Lisätään kaari molempiin suuntiin.
        Solmu a = solmut.get(lahtopaikka);
        Solmu b = solmut.get(maaranpaa);
        lisaaYhteys(a, b, i);
    }
    
    void lisaaYhteys(Solmu a, Solmu b, int i) {
        a.lisaaYhteys(b, i);
        b.lisaaYhteys(a, i);
    }

    // Tulostetaan kaikki tunnetut yhteysvälit.
    void tulostaReitit() {
        for (HashMap.Entry<String, Solmu> entry : solmut.entrySet()) {
            Solmu tulos = entry.getValue();
            
            System.out.println(tulos.getNimi() + ": ");

            HashMap<Solmu, Integer> naapurit = tulos.getNaapurit();

            for (HashMap.Entry<Solmu, Integer> naapuri : naapurit.entrySet()) {
                System.out.println("    " + naapuri.getKey().getNimi() + " " + naapuri.getValue() + " km");
            }
        }
    }

    // Vertailumetodi keon toimintaa varten
    private class VertailtavaSolmu implements Comparable {
      private Solmu solmu;
      private double etaisyys;

      VertailtavaSolmu(Solmu solmu, double etaisyys ) {
        this.solmu = solmu;
        this.etaisyys = etaisyys;
      }

      Solmu getSolmu() {
        return this.solmu;
      }

      public boolean equals(Object o) {
        if( ! (o instanceof VertailtavaSolmu ) ) {
          return false;
        }
        VertailtavaSolmu s = (VertailtavaSolmu) o;
        return s.solmu.equals( this.solmu );
      }

      public int compareTo(Object o) {
        VertailtavaSolmu toinen = (VertailtavaSolmu) o;
        	return (int)( this.etaisyys - toinen.etaisyys );
        }
    }

    // Varsinainen Dijkstran algoritmi
    void reitinPituus(String lahtopaikka, String maaranpaa) {
        
        // Asetetaan halutut alku- ja päätepisteet.
        Solmu alku = solmut.get(lahtopaikka);
        Solmu loppu = solmut.get(maaranpaa);

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
