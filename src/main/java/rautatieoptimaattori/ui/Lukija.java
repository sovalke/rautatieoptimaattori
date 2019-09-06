package rautatieoptimaattori.ui;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.suorituskyky.Suorituskykytesti;

/**
 * Käyttöliittymäluokka.
 */
public class Lukija {

    Verkko verkko;
    Scanner lukija = new Scanner(System.in);
    Astar astar;
    Dijkstra dijkstra;

    /**
     * Konstruktori: asettaa käytettävän verkon ja alustaa algoritmit.
     *
     * @param verkko Käytettävä verkko
     * @throws java.text.ParseException
     */
    public Lukija(Verkko verkko) throws ParseException, Exception {
        this.verkko = verkko;
        this.astar = new Astar(this.verkko);
        this.dijkstra = new Dijkstra(this.verkko);
    }
    
    /**
     * Kuuntelee käyttäjän syötettä ja suorittaa metodeita pyyntöjen mukaan.
     *
     * @throws java.lang.Exception
     */
    public void kuuntele() throws Exception {
        System.out.println("------------------------------------------");
        while (true) {
            System.out.println("Valitse mitä haluat tehdä.");
            System.out.println("A - Tulosta verkon asemat");
            System.out.println("R - Tulosta kaikki saatavilla olevat reitit");
            System.out.println("O - Siirry reittiohjelmaan");
            System.out.println("S - Aja suorituskykytesti");
            System.out.println("Q - Lopeta");
            System.out.print("\nAnna käsky: ");
            String kasky = lukija.nextLine();
            System.out.println("Valittu komento " + kasky);

            if (kasky.equals("A") || kasky.equals("a")) {
                asemalistaus();
            }

            if (kasky.equals("R") || kasky.equals("r")) {
                reitit();
            }

            if (kasky.equals("O") || kasky.equals("o")) {
                reittiohjelma();
            }
            
            if (kasky.equals("S") || kasky.equals("s")) {
                Suorituskykytesti testi = new Suorituskykytesti();
                testi.testaa();
            }

            if (kasky.equals("Q") || kasky.equals("q") || kasky.equals("")) {
                System.out.println("Kiitos ja näkemiin!");
                break;
            }

        }
    }

    /**
     * Listaa kaikki verkon asemat.
     */
    public void asemalistaus() {
        Solmu[] asemat = verkko.asemat();
                
        System.out.println("------------------------------------------");
        System.out.println("\nASEMALISTAUS");
        System.out.println("Asemia on yhteensä " + asemat.length);
        System.out.println("");
        
        String[] asematTeksti = new String[asemat.length];

        // Kopioidaan asemien nimet ja id:t uuteen taulukkoon muodossa
        // nimi (id).
        for (int i = 0; i < asemat.length; i++) {
            asematTeksti[i] = asemat[i].getNimi() + "(" + asemat[i].getId() + ")";
        }

        // Tällä sort-komennolla aakkostetaan tulostettavat asemat.
        // Ei liity ohjelman ydintoimintoihin.
        Arrays.sort(asematTeksti);
        
        // Tulostetaan nimet.
        for (String rivi : asematTeksti) {
            System.out.println(rivi);
        }
        
        System.out.println("------------------------------------------");
    }
    
    /**
     * Listaa kaikki verkon suorat yhteyet solmujen välillä.
     */
    public void reitit() {
        String[] yhteydet = verkko.reitit();
        
        // Tällä sort-komennolla aakkostetaan tulostettavat reitit kauniimmiksi.
        // Ei liity ohjelman ydintoimintoihin.
        Arrays.sort(yhteydet);
        
        System.out.println("------------------------------------------");
        System.out.println("\nSAATAVILLA OLEVAT YHTEYSVÄLIT");
        System.out.println("Yhteyksiä yhteensä " + yhteydet.length);
        System.out.println("");
        for (int i = 0; i < yhteydet.length; i++) {
            System.out.println(yhteydet[i]);
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Kuuntelee käyttäjän syötettä (lähtöpaikka ja määränpää) ja suorittaa
     * reitinhakualgoritmin.
     * @throws java.lang.Exception
     */
    public void reittiohjelma() throws Exception {
        System.out.println("------------------------------------------");
        System.out.println("REITINHAKUOHJELMA");
        Solmu alku = null;
        Solmu loppu = null;

        // OSA 1: LÄHTÖASEMAN HAKU
        while (true) {
            System.out.print("Syötä lähtöaseman nimi: ");
            String kasky = lukija.nextLine();
            alku = asemaKysely(kasky);

            if (alku != null) {
                break;
            }
        }

        // OSA 2: MÄÄRÄNPÄÄASEMAN HAKU
        while (true) {
            System.out.print("Syötä määränpääaseman nimi: ");
            String kasky = lukija.nextLine();
            loppu = asemaKysely(kasky);

            if (loppu != null) {
                break;
            }
        }

        // OSA 3: ALGORITMIN VALINTA JA OHJELMAN SUORITUS
        while (true) {
            System.out.println("\nOnko sinulla kiire perille?\nK - kyllä, E - ei ");
            String kasky = lukija.nextLine();

            if (kasky.isEmpty() || kasky.equals("")) {
                System.out.println("   Syötä K tai E.");
                continue;
            }
            if (kasky.equals("K") || kasky.equals("k")) {
                System.out.println("\nSelvä. Pannaan hommaan vauhtia ja käytetään A*:aa.");
                long reitti = this.astar.reitinPituus(alku, loppu);
                String aika = aika(reitti);
                System.out.println("Haetaan reittiä...");
                System.out.println("------VALMIS.");
                System.out.println("\nMatkan pituus on " + aika + ".");
                System.out.println("------------------------------------------");
                break;
            }
            if (kasky.equals("E") || kasky.equals("e")) {
                System.out.println("\nSelvä. Otetaan letkeästi ja käytetään Dijkstraa.");
                long reitti = this.dijkstra.reitinPituus(alku, loppu);
                String aika = aika(reitti);
                
                System.out.println("Haetaan reittiä...");
                System.out.println("------VALMIS.");
                System.out.println("\nMatkan pituus on " + aika + ".");
                System.out.println("------------------------------------------");
                break;
            }
        }

    }

    /**
     * Etsii halutun aseman verkosta.
     *
     * @param kasky Käyttäjän antama syöte (aseman nimi).
     * @return löydetty solmu (null, jos ei löydy)
     */
    public Solmu asemaKysely(String kasky) {
        Solmu solmu = null;
        if (kasky.isEmpty() || kasky.equals("")) {
            System.out.println("   Syötä aseman nimi.");
            return solmu;
        }
        try {
            solmu = verkko.getSolmu(kasky);
            if (solmu == null) {
                System.out.println("   Asemaa ei löydy. Yritä uudelleen.");
            }
        } catch (Error e) {
            System.out.println(e);
            return solmu;
        }
        return solmu;
    }

    /**
     * Muuttaa millisekunnit tunneiksi, minuuteiksi ja sekunneiksi.
     *
     * @param ms millisekunnit
     * @return String aika tunteina, minuutteina ja sekunteina
     */
    public static String aika(long ms) {
        long kokSekunnit = ms/1000;
        long tunnit = (kokSekunnit / 3600);
        long minuutit = (kokSekunnit / 60) % 60;
        long sekunnit = kokSekunnit % 60;
        
        String minString = (minuutit == 0)
            ? "00" : ((minuutit < 10) ? "0" + minuutit : "" + minuutit);
        String sekString = (sekunnit == 0) ? "00" : ((sekunnit < 10)
            ? "" + sekunnit : "" + sekunnit);
        if (tunnit > 0)
            return tunnit + " tuntia, " + minString + " minuuttia ja " + sekString + " sekuntia";
        else if (minuutit > 0)
            return minuutit + ":" + sekString;
        else return ":" + sekString;
    }

}
