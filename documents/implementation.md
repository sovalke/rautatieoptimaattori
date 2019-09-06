# Ohjelman toteutus

## Ohjelman yleisrakenne

+ **.gitignore**: gitin asetukset
+ **checkstyle.xml**: Checkstyle-työkalun asetukset
+ **pom.xml**: asetustiedosto Mavenia ym. varten
+ **README.md**: lueminut-infotiedosto
+ **/data**: sisältää csv-muotoiset tiedostot, joita käytetään ohjelman aineistona
+ **/data/testdata**: sisältää tiedostot, joita käytetään suorituskykytestauksessa
+ **/documents**: sisältää ohjelman dokumentaation (käyttöohje, testausraportti, toteutus ym.)
    - **/reports**: sisältää tiran viikkoraportit
+ **/src**: varsinainen ohjelmakoodi
    - **/main/java**: java-paketit & itse ohjelmakoodi
    - **/test/java/rautatieoptimaattori**: ohjelmaan liittyvät JUnit-testit


## Käytetyistä valmiista rakenteista

Ohjelman ydintoiminnot (mm. algoritmit) on toteutettu täysin omien tietorakenteiden avulla. Muutamissa aputoiminnoissa on kuitenkin käytetty apuna *Arrays.sort*-toimintoa. Näitä aputoimintoja ovat:

* **Käyttöliittymä**-luokka Lukija, jossa Arrays.sortia käytetään aakkostamaan tulostettavia asema- ja yhteysvälilistauksia. Jälleen kerran kyse **ei ole** ohjelman ydintoiminnallisuudesta vaan kosmeettisesta lisästä - listaukset toimisivat aivan yhtä hyvin ilman aakkostamistakin.

* Lisäksi **OmaHashMap**- ja **OmaLista**-luokissa on import Collections-luokalle. **Collections-luokkaa ei kuitenkaan käytetä.** Import on jätetty paikalleen, koska luokat toteuttavat rajapintoja (Map, Set, Iterable), jotka puolestaan edellyttävät ylimääräisten metodien toteuttamista. Nämä metodit vaativat parametreina Collections-tyypin olioita, tai vaihtoehtoisesti palauttavat Collections-luokan olioita return-arvoina. Nämä metodit on tässä ohjelmassa jätetty toteuttamatta, mutta niiden vaatimat importit on jätetty paikoilleen, jotta ei tule virheilmoituksia.


## Käsitelävä data

Ohjelman käyttämä data (junien ja asemien tiedot) tallennetaan **data**-kansioon .csv-tiedostoina. Tiedostojen sisällä datan on noudatettava seuraavia rakenteita:

### Junien tiedot

Junien tiedot tallennetaan .csv-dokumenttiin, jonka ensimmäisen rivi on:

    from,to,departs,arrives

Mikäli ensimmäinen rivi poikkeaa tästä, ohjelma ei käsittele tiedostoa.

Ensimmäisen rivin jälkeen data esitetään siten, että jokaiselle riville tulee aina yhden junan tiedot. Jokainen tietokenttä erotetaan edellisestä pilkulla.

| Sarake        | Sisältö                        |
| ------------- |:------------------------------:|
| from          | int (aseman id)                |
| to            | int (aseman id)                |
| departs       | aikaleima (simple date format) |
| arrives       | aikaleima (simple date format) |

Esimerkki kelpuutettavasta tiedostosta:

    from,to,departs,arrives
    1,10,2019-07-30T03:57:00.000Z,2019-07-30T04:02:00.000Z
    10,10,2019-07-30T04:02:00.000Z,2019-07-30T04:03:00.000Z

### Asemien tiedot

Asemien tiedot tallennetaan .csv-dokumenttiin, jonka ensimmäinen rivi on:

    Name,id,lat,long

Mikäli ensimmäinen rivi poikkeaa tästä, ohjelma ei käsittele tiedostoa.

Ensimmäisen rivin jälkeen data esitetään siten, että jokaiselle riville tulee aina yhden aseman tiedot. Jokainen tietokenttä erotetaan edellisestä pilkulla.

| Sarake        | Sisältö                        |
| ------------- |:------------------------------:|
| Name          | String (aseman nimi)           |
| id            | int (aseman id)                |
| lat           | long (aseman gps-koordinaatti) |
| long          | long (aseman gps-koordinaatti) |

Esimerkki kelpuutettavasta tiedostosta:

    Name,id,lat,long
    Ahonpää,1343,64.537118,25.006783
    Ahvenus,1000,61.291923,22.498185


## Aika- ja tilavaativuudet

Projektin alussa todettiin, että Dijkstran algoritmin tilavaativuus on O(n + nlogn) (Laaksonen 2019: 127) ja A-Starin O(n) (Wikipedia). Tältä pohjalta A-Starin pitäisi olla näistä algoritmeista nopeampi, *joskaan ei tavattoman paljon*. Eron ei pitäisi olla esimerkiksi eksponentiaalinen.

Suorituskykytestaus tukee tätä ajatusta. Testauksessa A-Star oli säännönmukaisesti nopeampi kuin Dijkstra, mutta ei koskaan räjähdysmäisen paljon. Ero oli A-Starin hyväksi 50-60 %:n luokkaa, eli jos Dijkstralla kului matkaan viisi sekuntia, A-Starilla kului samaan matkaan keskimäärin 2,5-3 sekuntia.

Tarkemmat vertailut algoritmien välillä voi lukea [testausdokumentaatiosta](documents/testing.md).


## Puutteita ja parannusehdotuksia

Tällä hetkellä ohjelma toimii kohtalaisen hyvin, mutta siinä on vielä kehittämisen varaa. Ainakin seuraavia toimintoja ja ominaisuuksia voisi tulevaisuudessa lisätä:

* Graafinen käyttöliittymä
* Tarkan reittilistauksen tulostaminen
* Reitin mittaaminen kilometreissä millisekuntien sijaan
* Heuristiikan parantaminen
* Aseman tunnistaminen osittaisesta nimestä (esim. pelkkä hakusana *Helsinki* antaisi tulokseksi aseman nimeltä *Helsinki asema*)


# Lähteet

* Laaksonen, Antti 2019: Tietorakenteet ja algoritmit -kurssimoniste. Päivitetty 21.6.2019.
* Wikipedia: A* search algorithm. https://en.wikipedia.org/wiki/A*_search_algorithm. Luettu 24.7.2019.