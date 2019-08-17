# Ohjelman testaus

## Yksikkötestaus: JUnit

Projektille on pyritty luomaan mahdollisimman kattava JUnit-yksikkötestien paketti. Testien koodikattavuutta on testattu Pitin avulla.

Tällä hetkellä (17.8.2019) yksikkötestien koodikattavuus on seuraava:

* Kokonaiskattavuus (rivikattavuus/mutaatiokattavuus):
	- 92 % / 84 %

* Algoritmien kattavuus (rivikattavuus/mutaatiokattavuus):
	- Dijkstra: 91 % / 90 %
	- A*: 90 % / 76 %

* Domain-luokkien kattavuus (rivikattavuus/mutaatiokattavuus):
	- Solmu: 100 % / 100 %
	- Verkko: 100 % / 88 %
	- VertailtavaSolmu: 64 % / 50 %

### Syyt JUnit-testien kattavuuseroihin

**Algoritmien tapauksessa** rivit, joita yksikkötestit eivät käsittele, ovat yksittäisiä. Rivit mm tarkistavat, onko jokin solmu jo käsitelty. Testeissä käytettävä testidata on vielä tässä vaiheessa sen verran pientä, että näitä tilanteita ei testissä tule.

__Korjausajatus:__ Kasvatetaan testien dataa niin suureksi, että myös nämä rivit siirtyvät käyttöön.

**Domain-luokkien tapauksessa** ongelmia aiheuttavat VertailtavaSolmu-luokan rivit, joilla on tarkoitus vertailla, onko vertailtava olio yhtä suuri toisen olion kanssa. Minulla ei tässä vaiheessa ole tietoa siitä, miten tällaista metodia voisi tarkistaa JUnitin avulla. Selvitän asiaa.


## Suorituskykytesti

Projektille on tarkoitus luoda suorituskykytesti. Toistaiseksi tällaista testiä ei ole alla kuvatuista ongelmista johtuen.

Projektille on yritetty luoda suorituskykytestiä Jmeteriä käyttäen. Ikävä kyllä tämä ei onnistunut, sillä vaikka JMeterin käyttöönotto onnistui, toimivien testien luominen osoittautui vaikeaksi.

Yritin luoda testiä myös JUnitia käyttäen. Ikävä kyllä en saanut myöskään tätä testiä toimimaan. Turvauduin lopulta tira-labran esimerkkitestiin, mutta se oli erittäin vaikealukuinen, enkä saanut selville, kuinka se toimi. Nähdäkseni siinä ei kutsuta testattavia luokkia ollenkaan, vaikka sen pitäisi testata juuri niiden suorituskykyä.

Pyrin selvittämään mahdollisimman nopeasti, miten suorituskykytestin saisi kasattua. Lisään sen heti, kun saan toimivan testin aikaan.