package rautatieoptimaattori.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;

public class Aineistokasittelija {

    Verkko verkko;
    BufferedReader csvLukija;

    /**
     * Konstruktori.
     */
    public Aineistokasittelija() {
        this.verkko = new Verkko();
    }

    /**
     * Lisää kaikki tiedoston asemat verkkoon.
     *
     * @param polku tiedoton polku
     * @return verkon koko lisäysten jälkeen
     * @throws java.io.FileNotFoundException
     */
    public int lisaaAsemat(String polku) throws FileNotFoundException, IOException, Exception {
        csvLukija = new BufferedReader(new FileReader(polku));

        String ensimmainenRivi = csvLukija.readLine();
        String asemaAlku = "Name,id,lat,long";

        if (!ensimmainenRivi.equalsIgnoreCase(asemaAlku)) {
            throw new Exception("Data ei ole oikeassa muodossa.");
        }

        // Tallennetaan rivit taulukoksi.
        String rivi;

        while ((rivi = csvLukija.readLine()) != null) {

            String nimi;
            int id;
            double x;
            double y;

            String[] olio = rivi.split(",");

            // Käsitellään vain, jos kaikki tarvittavat kentät (4) ovat olemassa
            if (olio.length == 4) {
                nimi = olio[0];
                id = Integer.parseInt(olio[1]);
                x = Double.parseDouble(olio[2]);
                y = Double.parseDouble(olio[3]);
                this.verkko.lisaaAsema(nimi, id, x, y);
            }
        }

        csvLukija.close();

        return this.verkko.getKoko();
    }

    /**
     * Lisää asemien välille yhteydet tiedostosta.
     *
     * @param polku tiedoston polku
     * @throws java.io.IOException
     * @throws java.text.ParseException
     * @throws java.lang.Exception
     */
    public void lisaaYhteydet(String polku) throws IOException, ParseException, Exception {
        csvLukija = new BufferedReader(new FileReader(polku));
        String yhteysAlku = "from,to,departs,arrives";
        String ensimmainenRivi = csvLukija.readLine();

        if (!ensimmainenRivi.equalsIgnoreCase(yhteysAlku)) {
            throw new Exception("Data ei ole oikeassa muodossa.");
        }

        // Tallennetaan rivit taulukoksi.
        String rivi;

        while ((rivi = csvLukija.readLine()) != null) {
            String[] olio = rivi.split(",");

            // Käsitellään vain, jos kaikki tarvittavat kentät ovat olemassa.
            if (olio.length == 4) {

                // Jos kaksi ensimmäistä kenttää ovat identtiset, dataa ei käsitellä;
                // (joko lähtöasema ja määränpää ovat samat TAI datassa on muuten virhe).
                if (olio[0].equalsIgnoreCase(olio[1])) {
                    continue;
                }

                int lahtopaikka = Integer.parseInt(olio[0]);
                int maaranpaa = Integer.parseInt(olio[1]);

                try {
                    Solmu lahtoSolmu = this.verkko.getSolmu(lahtopaikka);
                    Solmu maaranpaaSolmu = this.verkko.getSolmu(maaranpaa);

                    Date lahtoAika;
                    Date saapumisAika;

                    // Aikaleimaformaatti
                    SimpleDateFormat aikaleima = 
                            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    lahtoAika = aikaleima.parse(olio[2]);
                    saapumisAika = aikaleima.parse(olio[3]);
                    long erotus = saapumisAika.getTime() - lahtoAika.getTime();

                    // Lisätään laskettu yhteys verkkoon.
                    this.verkko.lisaaYhteys(lahtoSolmu, maaranpaaSolmu, erotus);
                } catch (Exception ex) {
                    Logger.getLogger(Aineistokasittelija.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        csvLukija.close();
    }

    /**
     * Palauttaa verkon.
     *
     * @return verkko
     */
    public Verkko getVerkko() {
        return this.verkko;
    }

}
