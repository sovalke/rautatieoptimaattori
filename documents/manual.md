# Käyttöohje


## Aloittaminen

Lataa projekti Githubista. Pura paketti.

Tämän jälkeen voit suorittaa ohjelman. Ohjelman pääluokka on Rautatieoptimaattori.


## Ohjelman käyttäminen

Kun olet **käynnistänyt ohjelman**, näkyviin tulee tekstipohjainen käyttöliittymä. Valittavana on seuraavat komennot:

    Valitse mitä haluat tehdä.
    A - Tulosta verkon asemat
    R - Tulosta kaikki saatavilla olevat reitit
    O - Siirry reittiohjelmaan
    S - Aja suorituskykytesti
    Q - Lopeta

### A: tulosta verkon asemat

Kun syötät ohjelmalle komennon **A** tai **a**, ohjelma tulostaa kaikki sillä hetkellä käytössä olevat asemat. Jokaisen aseman perässä on lisäksi suluissa aseman id.

Tulostamisen jälkeen palataan alkutilaan.

### R: tulosta kaikki saatavilla olevat reitit

Kun syötät ohjelmalle komennon **R** tai **r**, ohjelma tulostaa kaikki sillä hetkellä tunnetut suorat yhteydet eri asemien välillä.

### O: siirry reittiohjelmaan

Kun syötät ohjelmalle komennon **O** tai **o**, ohjelma siirtyy reitinetsintätilaan. Ohjelma tiedustelee ensin lähtöaseman ja sen jälkeen määränpääaseman nimeä.

    REITINHAKUOHJELMA
    Syötä lähtöaseman nimi: Tampere asema
    Syötä määränpääaseman nimi: Turku asema

**Jos oikeaa asemaa ei löydy, tarkista aseman nimi.** Kaikkien asemien nimet eivät ole intuitiivisia (esimerkiksi pelkkä *Helsinki* ei ole aseman nimi, vaan päärautatieasema on *Helsinki asema*). Aseman nimen pitää olla kirjoitettu täsmälleen oikein, jotta ohjelma huolii sen. Huomaathan, että isoilla ja pienillä kirjaimilla on merkitystä.

    Syötä lähtöaseman nimi: Tampere
    Asemaa ei löydy. Yritä uudelleen.
    Syötä lähtöaseman nimi: Tampere asema
    Syötä määränpääaseman nimi: Turku asema

Kun olet antanut sekä lähtö- että määränpääaseman nimen, ohjelma tiedustelee, onko sinulla kiire. Vastaa joko kyllä (k tai K) tai ei (e tai E). Ohjelma valitsee käytettävän algoritmin tämän mukaan.

    Onko sinulla kiire perille?
    K - kyllä, E - ei 
    e
    
    Selvä. Otetaan letkeästi ja käytetään Dijkstraa.
    Haetaan reittiä...
    ------VALMIS.
    
    Matkan pituus on 2 tuntia, 58 minuuttia ja 34 sekuntia.

Kun olet saanut reitin selvitettyä, ohjelma palaa alkutilaan.

### S: aja suorituskykytesti

Kun valitset komennon **S** tai **s**, ohjelma käynnistää suorituskykytestin ja printtaa tulokset.

Kun testi on tehty, ohjelma palaa alkutilaan.

### Q: lopeta

Kun valitset komennon **Q** tai **q**, poistut ohjelmasta.


## Oman datan käyttäminen

Ohjelma on konfiguroitu käyttämään datana kahta tiedostoa (**trains.csv** ja **stations.csv**), jotka sijaitsevat kansiossa **/data/**.

Ohjelman käyttämä data (junien ja asemien tiedot) tallennetaan aina .csv-tiedostoina. Tiedostojen sisällä datan on noudatettava seuraavia rakenteita:

### Junien tiedot

Junien tiedot tallennetaan data-kansioon **trains.csv**-dokumenttiin, jonka ensimmäisen rivi on:

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

Asemien tiedot tallennetaan data-kansioon **stations.csv**-dokumenttiin, jonka ensimmäinen rivi on:

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
