package rautatieoptimaattori.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import rautatieoptimaattori.Verkko;

public class Aineistokasittelija {

    Verkko verkko;
    BufferedReader csvLukija;

    public Aineistokasittelija() {
        this.verkko = new Verkko();
    }

    public void lisaaAsemat(String polku) throws FileNotFoundException, IOException {
        csvLukija = new BufferedReader(new FileReader(polku));
        String asemaAlku = "Name,id,lat,long";

        // Tallennetaan rivit taulukoksi.
        String rivit;
        boolean asema = false;

        while ((rivit = csvLukija.readLine()) != null) {
            String[] data = rivit.split("\"[\\r\\n]+\"");

            for (String a : data) {
                String nimi;
                int id;
                double x;
                double y;

                if (a.equalsIgnoreCase(asemaAlku)) {
                    // Kyse on nimenomaan ASEMALISTAUKSESTA.
                    asema = true;
                }

                // Katkaistaan rivi aina pilkun kohdalta.
                String[] olio = a.split(",");

                // Tallennetaan pilkuilla erotellut aseman tiedot verkkoon.
                // Ensimmäistä riviä ei käsitellä.
                if (olio[0].equalsIgnoreCase("name") || olio[0].equalsIgnoreCase("from")) {
                    continue;
                }

                // Käsitellään vain, jos kaikki tarvittavat kentät (4) ovat olemassa
                // JA kyseessä on asemadata.
                if (olio.length == 4 && asema == true) {
                    nimi = olio[0];
                    id = Integer.parseInt(olio[1]);
                    x = Double.parseDouble(olio[2]);
                    y = Double.parseDouble(olio[3]);
                    this.verkko.lisaaAsema(nimi, id, x, y);
                }
            }
        }
        csvLukija.close();
        System.out.println("Verkossa on nyt " + this.verkko.getKoko() + " solmua.");
    }

    public void lisaaYhteydet(String polku) throws IOException, ParseException {
        csvLukija = new BufferedReader(new FileReader(polku));
        String yhteysAlku = "from,to,departs,arrives";

        // Tallennetaan rivit taulukoksi.
        String rivit;
        boolean yhteys = false;

        while ((rivit = csvLukija.readLine()) != null) {
            String[] data = rivit.split("\"[\\r\\n]+\"");

            for (String a : data) {

                // Kyse on YHTEYSLUETTELOSTA.
                if (a.equalsIgnoreCase(yhteysAlku)) {
                    yhteys = true;
                }

                // Katkaistaan rivi aina pilkun kohdalta.
                String[] olio = a.split(",");

                // Tallennetaan pilkuilla erotellut yhteystiedot verkkoon.
                // Ensimmäistä riviä ei käsitellä.
                if (olio[0].equalsIgnoreCase("name") || olio[0].equalsIgnoreCase("from")) {
                    continue;
                }

                // Jos kaksi ensimmäistä kenttää ovat identtiset, dataa ei käsitellä;
                // (joko lähtöasema ja määränpää ovat samat TAI datassa on muuten virhe).
                if (olio[0].equalsIgnoreCase(olio[1])) {
                    continue;
                }

                // Käsitellään vain, jos kaikki tarvittavat kentät ovat olemassa.
                if (olio.length == 4 && yhteys == true) {
                    int lahtopaikka = Integer.parseInt(olio[0]);
                    int maaranpaa = Integer.parseInt(olio[1]);
                    String selko = this.verkko.getSolmu(lahtopaikka).getNimi() + "-" + this.verkko.getSolmu(maaranpaa).getNimi();

                    Date lahtoAika = null;
                    Date saapumisAika = null;

                    // Aikaleimaformaatti
                    SimpleDateFormat aikaleima = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

                    lahtoAika = aikaleima.parse(olio[2]);
                    saapumisAika = aikaleima.parse(olio[3]);

                    // Aikaleimojen erotus
                    long erotus = saapumisAika.getTime() - lahtoAika.getTime();

                    // Lisätään laskettu yhteys verkkoon.
                    this.verkko.lisaaYhteys(lahtopaikka, maaranpaa, erotus);
                }
            }
        }

        csvLukija.close();
    }

    public Verkko getVerkko() {
        return this.verkko;
    }

}
