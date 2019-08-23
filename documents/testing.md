# Ohjelman testaus

## Yksikkötestaus: JUnit

Projektille on pyritty luomaan mahdollisimman kattava JUnit-yksikkötestien paketti. Testien koodikattavuutta on testattu Pitin avulla.

Tällä hetkellä (17.8.2019) yksikkötestien koodikattavuus on seuraava:
	
| Luokka             | Rivikattavuus  | Mutaatiokattavuus |
| -------------------|:---------------|:------------------|
| **Algoritmit**     |                |                   |
| - Astar            | 90 %           | 76 %              |
| - Dijkstra         | 91 %           | 90 %              |
| **Domain-luokat**  |                |                   |
| - Solmu            | 100 %          | 100 %             |
| - Verkko           | 100 %          | 88 %              |
| - VertailtavaSolmu | 64 %           | 50 %              |
| **Kaikki yhteensä**| 92 %           | 84 %              | 

### JUnit-testeissä käytetty aineisto

Tällä hetkellä JUnit-testeissä käytetään pientä aineistoa, joka generoidaan jokaista testiä varten erikseen. Pienimmät aineistot ovat vain yhden tai kahden solmun kokoisia – näitä käytetään mm. getterien testauksessa.

Jatkossa osassa testeistä on tarkoitus käyttää merkittävästi laajempaa aineistoa, jotta tietyt testeissä havaitut kattavuuserot saadaan korjattua.

### Syyt JUnit-testien kattavuuseroihin

**Algoritmien tapauksessa** rivit, joita yksikkötestit eivät käsittele, ovat yksittäisiä. Rivit mm. tarkistavat, onko jokin solmu jo käsitelty. Testeissä käytettävä testidata on vielä tässä vaiheessa sen verran pientä, että näitä tilanteita ei testissä tule vastaan. Täten myös rivit jäävät testien ulkopuolelle.
* __Korjausajatus:__ kasvatetaan testien dataa niin suureksi, että myös nämä rivit tulevat käyttöön.

**Domain-luokkien tapauksessa** ongelmia aiheuttavat VertailtavaSolmu-luokan rivit, joilla on tarkoitus vertailla, onko vertailtava olio yhtä suuri toisen olion kanssa. Kyse on siis compare-luokasta. Minulla ei tässä vaiheessa ole tietoa siitä, miten tällaista metodia voisi tarkistaa JUnitin avulla.
* __Korjausajatus:__ selvitetään, miten compare-olioita voi testata yksikkötesteillä.


## Suorituskykytesti

Projektille on luotu suorituskykytesti, joka mittaa A*:n ja Dijkstran nopeuden eroja, kun samaa reittiä haetaan 100, 1000 ja 10 000 kertaa.

Testissä käytetään testidataa, joka sijaitsee projektin kansiossa /data/testdata/. Testidatasta käsitellään seuraavia yhteysvälejä (välit on valittu testiä ajatellen siten, että osa matkoista on lyhyitä ja osa pitkiä):
* Helsinki asema-Jepua
* Helsinki asema-Kemi
* Hanko asema-Jokela
* Rovaniemi-Turku asema
* Helsinki asema-Turku asema

Yleisesti ottaen A* oli selvästi nopeampi kuin Dijkstra; ääritapauksissa erot ovat yli 10-kertaisia.

Alla olevassa taulukossa on molempien nopeuden mediaani sekä suhteellinen nopeusero.

| Reitti                    | Kierrosta  | Dijkstra (ms) | A* (ms)  | Dijkstra / A*    |
| --------------------------|:-----------|:--------------|:---------|:-----------------|
| Helsinki asema-Jepua      | 100        | 1555430       | 1050279  | 1,48             |
| Helsinki asema-Jepua      | 1000       | 992292        | 502423   | 1,98             |
| Helsinki asema-Jepua      | 10 000     | 176112        | 117618   | 1,50             |
| Helsinki asema-Kemi       | 100        | 252658        | 284727   | 0,89             |
| Helsinki asema-Kemi       | 1000       | 197587        | 171931   | 1,15             |
| Helsinki asema-Kemi       | 10 000     | 165767        | 171445   | 0,97             |
| Hanko asema-Jokela        | 100        | 128288        | 17782    | 7,21             |
| Hanko asema-Jokela        | 1000       | 191444        | 15211    | 12,59            |
| Hanko asema-Jokela        | 10000      | 168424        | 14900    | 11,30            |
| Rovaniemi-Turku asema     | 100        | 234700        | 114979   | 2,04             |
| Rovaniemi-Turku asema     | 1000       | 178323        | 92411    | 1,93             |
| Rovaniemi-Turku asema     | 10 000     | 172062        | 91407    | 1,88             |
| Helsinki asema-Turku asema| 100        | 234143        | 19247    | 12,17            |
| Helsinki asema-Turku asema| 1000       | 194267        | 22228    | 8,74             |
| Helsinki asema-Turku asema| 10 000     | 168537        | 24250    | 6,95             |
| --------------------------|:-----------|:--------------|---------:|:-----------------|
|                           |            |               |keskim.   | 4,85             |
