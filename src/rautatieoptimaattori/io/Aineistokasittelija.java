package rautatieoptimaattori.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import rautatieoptimaattori.Solmu;
import rautatieoptimaattori.Verkko;

public class Aineistokasittelija {

    private Verkko verkko = new Verkko();
    BufferedReader csvLukija;

    public Aineistokasittelija(String polku) throws FileNotFoundException, IOException {
        csvLukija = new BufferedReader(new FileReader(polku));
        String asemaAlku = "Name,id,lat,long";
        String yhteysAlku = "from,to,departs,arrives";

        // Tallennetaan rivit taulukoksi.
        String rivit;
        boolean asema = false;
        boolean yhteys = false;

        while ((rivit = csvLukija.readLine()) != null) {
            String[] data = rivit.split("\"[\\r\\n]+\"");

            for (String a : data) {

                String nimi;
                int id;
                Double x;
                Double y;

                if (a.equalsIgnoreCase(asemaAlku)) {
                    // Kyse on ASEMALISTAUKSESTA.
                    asema = true;
                    System.out.println(asema);
                }
                if (a.equalsIgnoreCase(yhteysAlku)) {
                    // Kyse on YHTEYSLUETTALOSTA.
                    yhteys = true;
                    System.out.println("HII");
                }

                // Katkaistaan rivi aina pilkun kohdalta.
                String[] olio = a.split(",");

                // Tallennetaan pilkuilla erotellut aseman tiedot verkkoon.
                // Ensimmäistä riviä ei käsitellä.
                if (olio[0].equalsIgnoreCase("name") || olio[0].equalsIgnoreCase("from")) {
                    //Ensimmäistä riviä ei käsitellä.
                    continue;
                }
                
                // Jos kaksi ensimmäistä kenttää ovat identtiset, dataa ei käsitellä;
                // (joko lähtöasema ja määränpää ovat samat TAI datassa on muuten virhe).
                if (olio[0].equalsIgnoreCase(olio[1])) {
                    continue;
                }
                
                // Käsitellään vain, jos kaikki tarvittavat kentät ovat olemassa
                // JA kyseessä on asemadata.
                if (olio.length == 4 && asema == true) {
                    nimi = olio[0];
                    id = Integer.parseInt(olio[1]);
                    x = Double.parseDouble(olio[2]);
                    y = Double.parseDouble(olio[3]);
                    this.verkko.lisaaAsema(nimi, id, x, y);
                }
                // Käsitellään vain, jos kaikki tarvittavat kentät ovat olemassa
                // JA kyseessä on yhteysdata.

                if (olio.length == 4 && yhteys == true) {

                    // Karsitaan pois yhteydet, joissa lähtöasema ja määränpää ovat samat.
                    System.out.println(olio[0] + ", " + olio[1]);

                }

            }
        }

        csvLukija.close();
        System.out.println(verkko.getKoko());
    }

    private void lataaAsemat(String[] data) {

    }

    public Verkko getVerkko() {
        return this.verkko;
    }

}
