package rautatieoptimaattori;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Astar {

    HashMap<String, Solmu> solmut = new HashMap<>();
    HashMap<Integer, Solmu> solmutId = new HashMap<>();

    // Lisätään asema koordinaatteineen.
    void lisaaAsema(String nimi, int id, double x, double y) {
        if (solmut.containsKey(nimi)) {
            Solmu paivitettava = solmut.get(nimi);
            paivitettava.setXY(x, y);
            paivitettava.setId(id);
            solmutId.put(id, paivitettava);
        } else {
            Solmu lisattava = new Solmu(nimi, id, x, y);
            solmut.put(lisattava.getNimi(), lisattava);
            solmutId.put(lisattava.getId(), lisattava);
        }
        Solmu a = solmut.get(nimi);
        System.out.println("Lisätty asema " + a.getNimi() + " (" + a.getId() + "), koordinaatit " + a.getX() + " / " + a.getY());
    }

    // Lähtöpaikka ja määränpää annetaan merkkijonona.
    void lisaaYhteys(String lahtopaikka, String maaranpaa, int i) {
        if (!solmut.containsKey(lahtopaikka)) {
            System.out.println("Asemaa " + lahtopaikka + " ei löydy");
        }
        if (!solmut.containsKey(maaranpaa)) {
            System.out.println("Asemaa " + maaranpaa + " ei löydy");
        }

        // Lisätään kaari molempiin suuntiin.
        Solmu a = solmut.get(lahtopaikka);
        Solmu b = solmut.get(maaranpaa);
        lisaaYhteys(a, b, i);
    }

    // Lähtöpaikka ja määränpää annetaan id-numerona.
    void lisaaYhteys(int lahtopaikkaId, int maaranpaaId, int i) {
        if (!solmutId.containsKey(lahtopaikkaId)) {
            System.out.println("Asemaa " + lahtopaikkaId + " ei löydy");
        }
        if (!solmutId.containsKey(maaranpaaId)) {
            System.out.println("Asemaa " + maaranpaaId + " ei löydy");
        }

        // Lisätään kaari molempiin suuntiin.
        Solmu a = solmutId.get(lahtopaikkaId);
        Solmu b = solmutId.get(maaranpaaId);
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

        VertailtavaSolmu(Solmu solmu, double etaisyys) {
            this.solmu = solmu;
            this.etaisyys = etaisyys;
        }

        Solmu getSolmu() {
            return this.solmu;
        }

        public boolean equals(Object o) {
            if (!(o instanceof VertailtavaSolmu)) {
                return false;
            }
            VertailtavaSolmu s = (VertailtavaSolmu) o;
            return s.solmu.equals(this.solmu);
        }

        public int compareTo(Object o) {
            VertailtavaSolmu toinen = (VertailtavaSolmu) o;
            return (int) (this.etaisyys - toinen.etaisyys);
        }
    }

    // Varsinainen algoritmi
    void reitinPituus(String lahtopaikka, String maaranpaa) {

        // Asetetaan halutut alku- ja päätepisteet.
        Solmu alku = solmut.get(lahtopaikka);
        Solmu loppu = solmut.get(maaranpaa);

        System.out.println("Etäisyysarvio matkalle " + alku.getNimi() + "-" + loppu.getNimi() + " on " + etaisyysArvio(alku, loppu));

        // Luodaan keko ja tarvittavat HashMapit.
        HashMap<Solmu, Double> etaisyydet = new HashMap<>();
        HashMap<Solmu, Boolean> kasitelty = new HashMap<>();
        PriorityQueue<VertailtavaSolmu> keko = new PriorityQueue();

        // Lisätään lähtöpiste kekoon ja etäisyysarvioon.
        keko.add(new VertailtavaSolmu(alku, 0));
        etaisyydet.put(alku, 0.0);

        while (!keko.isEmpty()) {

            // Napataan käsiteltävä solmu.
            Solmu kasiteltava = keko.poll().getSolmu();

            // Onko solmua käsitelty vielä? Jos on, hypätään yli.
            if (kasitelty.containsKey(kasiteltava)) {
                continue;
            }

            double pieninArvio = Double.POSITIVE_INFINITY;
            Solmu valittu = kasiteltava;

            // Muutoin käydään läpi solmun naapurit.
            // Miksi tässä ei voi käyttää getNaapurit-metodia? Tulee virheilmoitus.
            for (HashMap.Entry<Solmu, Integer> naapuri : kasiteltava.naapurit.entrySet()) {

                // Onko naapurisolmu jo etäisyysarviotaulukossa?
                Solmu tutkittavaNaapuri = naapuri.getKey();
                double uusi = etaisyydet.get(kasiteltava) + kasiteltava.getEtaisyys(tutkittavaNaapuri);
                double kokonaisArvio = uusi + etaisyysArvio(tutkittavaNaapuri, loppu);
                System.out.println("-- Naapuri " + tutkittavaNaapuri.getNimi() + ", f: " + kokonaisArvio);

                // Päivitetään pienin etäisyysarvio ja tallennetaan tieto parhaasta solmusta.
                if (kokonaisArvio < pieninArvio) {
                    pieninArvio = kokonaisArvio;
                    valittu = tutkittavaNaapuri;
                }

                // Solmu ei ole vielä etäisyystaulukossa; lisätään se
                if (!etaisyydet.containsKey(tutkittavaNaapuri)) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);
                    continue;
                }

                // Solmu on taulukossa; verrataan uutta ja vanhaa etäisyyttä
                double vanha = etaisyydet.get(tutkittavaNaapuri);
                if (uusi < vanha) {
                    etaisyydet.put(tutkittavaNaapuri, uusi);
                }

                System.out.println("Määränpäänä " + loppu.getNimi());
                System.out.println("Valittuna " + valittu.getNimi());

            }
            
            // Määränpää saavutettu, nyt voi stopata.
            if (loppu.getId() == valittu.getId()) {
                break;
            }
            System.out.println("Valittu solmuksi " + valittu.getNimi() + ", f: " + pieninArvio);
            keko.add(new VertailtavaSolmu(valittu, pieninArvio));

            kasitelty.put(kasiteltava, true);

        }

        System.out.println("Etäisyys " + alku.getNimi() + "-" + loppu.getNimi() + " on " + etaisyydet.get(loppu) + " km.");
        System.out.println("--------------");
    }

    double etaisyysArvio(Solmu piste, Solmu maaranpaa) {
        // Hyödynnetään Pythagoraan lausetta etäisyysarvion laskemiseen.

        // Varmistetaan, että etäisyys ilmaistaan itseisarvoina.        
        double x = Math.abs(maaranpaa.getX() - piste.getX());
        double y = Math.abs(maaranpaa.getY() - piste.getY());

        // etaisyysaArvio = neliöjuuri(x^2 + y^2)
        double etaisyysArvio = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * 100;
        return etaisyysArvio;
    }
}
