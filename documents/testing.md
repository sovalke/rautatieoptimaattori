# Ohjelman testaus

## Yksikkötestaus: JUnit

Projektille on pyritty luomaan mahdollisimman kattava JUnit-yksikkötestien paketti. Testien koodikattavuutta on testattu Pitin avulla.

Tällä hetkellä (17.8.2019) yksikkötestien koodikattavuus on seuraava:

* **Kokonaiskattavuus** (rivikattavuus/mutaatiokattavuus):
	- 92 % / 84 %

* **Algoritmien kattavuus** (rivikattavuus/mutaatiokattavuus):
	- Dijkstra: 91 % / 90 %
	- A*: 90 % / 76 %

* **Domain-luokkien kattavuus** (rivikattavuus/mutaatiokattavuus):
	- Solmu: 100 % / 100 %
	- Verkko: 100 % / 88 %
	- VertailtavaSolmu: 64 % / 50 %

### Syyt JUnit-testien kattavuuseroihin

**Algoritmien tapauksessa** rivit, joita yksikkötestit eivät käsittele, ovat yksittäisiä. Rivit mm. tarkistavat, onko jokin solmu jo käsitelty. Testeissä käytettävä testidata on vielä tässä vaiheessa sen verran pientä, että näitä tilanteita ei testissä tule vastaan. Täten myös rivit jäävät testien ulkopuolelle.
* __Korjausajatus:__ kasvatetaan testien dataa niin suureksi, että myös nämä rivit tulevat käyttöön.

**Domain-luokkien tapauksessa** ongelmia aiheuttavat VertailtavaSolmu-luokan rivit, joilla on tarkoitus vertailla, onko vertailtava olio yhtä suuri toisen olion kanssa. Kyse on siis compare-luokasta. Minulla ei tässä vaiheessa ole tietoa siitä, miten tällaista metodia voisi tarkistaa JUnitin avulla.
* __Korjausajatus:__ selvitetään, miten compare-olioita voi testata yksikkötesteillä.


## Suorituskykytesti

Projektille on tarkoitus luoda suorituskykytesti. Toistaiseksi tällaista testiä ei ole alla kuvatuista ongelmista johtuen.

Projektille on yritetty luoda suorituskykytestiä **JMeteriä** käyttäen. Ikävä kyllä tämä ei onnistunut, sillä vaikka JMeterin käyttöönotto onnistui, toimivien testien luominen osoittautui vaikeaksi.

Yritin luoda testiä myös **JUnitia** käyttäen. Ikävä kyllä en saanut myöskään tätä testiä toimimaan. Turvauduin lopulta tira-labran [esimerkkitestiin](https://github.com/TiraLabra/Testing-and-rmq/tree/master/src/main/java/rmq/util), mutta se oli erittäin vaikealukuinen. En siis saanut selville, kuinka se toimii – ilmeisesti RMQ:n vaikeaselkoisuus on tähän syynä. Lisäksi tiran esimerkkikoodissa ei ymmärtääkseni kutsuta testattavia luokkia ollenkaan, vaikka sen pitäisi testata juuri niiden suorituskykyä. Minulle on epäselvää, miten tämä testi pystyy testaamaan luokkia, joihin se ei viittaa lainkaan.

* __Ratkaisu:__ Pyrin selvittämään mahdollisimman nopeasti, miten suorituskykytestin saa kasattua ja miten RMQ liittyy asiaan. Lisään testin heti, kun saan toimivan testin aikaan.