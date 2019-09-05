package rautatieoptimaattori;

import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.io.Aineistokasittelija;
import rautatieoptimaattori.ui.Lukija;

public class Rautatieoptimaattori {

    /**
     * Pääluokka. Tänne laitetaan kaikki suoritettava tavara.
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("TERVETULOA RAUTATIEOPTIMAATTORIIN!");
        System.out.println("------------------------------------------");

        System.out.println("Ladataan dataa...");
        Aineistokasittelija data = new Aineistokasittelija();
        int asemaLkm = data.lisaaAsemat("./data/stations.csv");
        data.lisaaYhteydet("./data/trains.csv");
        Verkko verkko = data.getVerkko();
        System.out.println("------VALMIS.");

        Lukija lukija = new Lukija(verkko);
        lukija.kuuntele();
    }

}
