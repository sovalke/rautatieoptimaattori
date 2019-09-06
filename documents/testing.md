# Ohjelman testaus

## Yksikkötestaus: JUnit

Projektille on pyritty luomaan mahdollisimman kattava JUnit-yksikkötestien paketti. Testien koodikattavuutta on testattu Pitin avulla. Kokonaisuudessaan ohjelman koodi- ja mutaatiokattavuus on seuraava:

![Pit-testauksen tulokset](/documents/img/pit.png)

**Huom.!** Tietorakenteet-paketin luokissa OmaHashMap ja OmaLista on implementoitu ylempien luokkien rajapintoja (Map, Set, Iterable). Aivan kaikkia näihin rajapintoihin kuuluvia metodeita ei kuitenkaan ole toteutettu, koska kaikille metodeille ei ole tässä työssä käyttöä eivätkä ne kuulu tämän työn ydinalueeseen. Toteuttamatta jätetyt metodit vaikuttavat rivikattavuuteen, sillä niille ei tietenkään voi laatia yksikkötestejä. Tästä syystä ohjelman rivikattavuus ei ole 100 %.

Alla olevassa erittelytaulukossa on listattu kunkin luokan tarkemmat rivi- ja mutaatiokattavuudet. Luokkien OmaHashMap ja OmaLista kohdalla on ilmoitettu rivikattavuudelle kaksi prosenttilukua. Ensimmäinen lukema laskee mukaan kaikki ohjelmatiedostojen rivit, myös toteuttamattomat metodit. Toinen lukema on laskettu vain toteutettujen metodien perusteella.

Ohjelman luokkien koodikattavuus on seuraava:
	
| Luokka             | Rivikattavuus  | Mutaatiokattavuus |
| -------------------|:---------------|:------------------|
| **Algoritmit**     |                |                   |
| - Astar            | 100 %          | 73 %              |
| - Dijkstra         | 100 %          | 88 %              |
| - Jarjestaja       | 100 %          | 63 %              |
| **Domain-luokat**  |                |                   |
| - Solmu            | 100 %          | 100 %             |
| - Verkko           | 100 %          | 82 %              |
| - VertailtavaSolmu | 100 %          | 100 %             |
| **Tietorakenteet** |                |                   |
| - OmaHashMap       | 95 %  / 100 %* | 91  %             |
| - OmaKeko          | 100 %          | 85 %              |
| - OmaLista         | 86 % / 100 %*  | 84 %              |
| - OmaPari          | 100 %          | 100 %             |


### JUnit-testeissä käytetty aineisto

Useimmissa JUnit-testeissä käytetään pientä, muutaman tai korkeintaan parinkymmenen solmun kokoista aineistoa, joka generoidaan testiä varten lennossa.

Ainoa poikkeus koskee A*-algoritmin testejä, joista osassa on käytetty kansiossa data/testdata/ olevia tiedostoja. Suuri aineisto on tarpeen mm. tilanteissa, joissa testataan algoritmin tapaa käsitellä samaa solmua useaan kertaan. Kovin pienellä datalla tällaista tilannetta ei tule vastaan, joten testi käyttää n. 3 mt kokoista valmista datasettiä. 


### JUnit-testien toistaminen

JUnit-testit voi toistaa ajamalla komentoriviltä ohjelman kansiossa komennon `mvn test`.


## Checkstyle

Projekti on tarkistettu Mavenin Checkstyle-pluginin avulla. Checkstyle antoi vain yhden varoituksen: OmaKeko-luokan rivillä 52 on muuttuja, jonka esittelyn ja käytön välillä on liian monta riviä koodia.

Ikävä kyllä tämä on välttämätöntä ohjelman toiminnalle, ja etäisyyden pienennysyritykset johtivat ohjelman toiminnan vioittumiseen. Tästä syystä tämä huomautus on jätetty lopulliseen versioon.

![Checkstylen yleisnäkymä](/documents/img/checkstyle1.png)

![Tarkempi virheilmoitus](/documents/img/checkstyle2.png)

## Suorituskykytesti

Projektille on luotu suorituskykytesti, joka mittaa A*:n ja Dijkstran nopeuden eroja, kun samaa reittiä haetaan 100, 1000 ja 10 000 kertaa. Testi järjestää algoritmien suoritusajat ja ottaa vertailuun suoritusaikojen mediaanit. Mediaanien valintaa varten data järjestetään lomitusjärjestämisellä (Järjestäjä-luokka).

Tätä dokumenttia varten testit ajettiin suhteellisen vanhalla kannettavalla tietokoneella (2,5 GHz Intel Core i5; 8 Gt RAM).

Testissä käytettiin testidataa, joka sijaitsee projektin kansiossa /data/testdata/. Testidatasta käsitellään seuraavia yhteysvälejä (välit on valittu testiä ajatellen siten, että osa matkoista on lyhyitä ja osa pitkiä):

* Helsinki asema-Jepua
* Helsinki asema-Kemi
* Hanko asema-Jokela
* Rovaniemi-Turku asema
* Helsinki asema-Turku asema

Yleisesti ottaen A* oli selvästi nopeampi kuin Dijkstra; ääritapauksissa erot A-Starin hyväksi ovat melkein 20-kertaisia (mm. välillä Helsinki asema - Turku asema A* käyttää reitin etsimiseen vain 6 % Dijkstran ajasta).

Alla olevassa taulukossa on molempien algoritmien nopeuden mediaani sekä suhteellinen nopeusero prosentteina.


| Reitti	                | 100 krt	 | 1000 krt      | 10 000 krt |
| --------------------------|:-----------|:--------------|:-----------|
| **Helsinki asema-Jepua**   	|            |               |            |
| - Dijkstra            	| 1947773	 | 1051410	     | 189612     |
| - A-Star                 	| 1121873	 | 773238	     | 100793     |
| -- *Ero A-Starin hyväksi* | 42 %       | 26 %          | 47 %       |
| **Helsinki asema-Kemi** 	|       	 |      	     |            |
| - Dijkstra            	| 535754	 | 190921	     | 190222     |
| - A-Star                	| 362253	 | 190348	     | 176980     |
| -- *Ero A-Starin hyväksi* | 32 %       | 0,3 %         | 7,0 %       |
| **Hanko asema-Jokela**    |            |       	     |            |
| - Dijkstra                | 187835     | 185137	     | 184616     |
| - A-Star                  | 29603	     | 14996         | 15953      |
| -- *Ero A-Starin hyväksi* | 84 %       | 92 %          | 91 %       |
| **Rovaniemi-Turku asema**	|        	 |       	     |            |
| - Dijkstra                | 200636	 | 186894	     | 186873     |
| - A-Star                	| 95975	     | 94681	     | 95603      |
| -- *Ero A-Starin hyväksi* | 52 %       | 49 %          | 49 %       |
| **Helsinki asema-Turku asema** |            |       	     |            |
| - Dijkstra	            | 405258	 | 193876	     | 185122     |
| - A-Star	                | 23576	     | 24110	     | 26906      |
| -- *Ero A-Starin hyväksi* | 94 %       | 75 %          | 85 %       |
| **Keskimäärin**           | 61 %       | 48 %          | 56 %       |

Ero A-Starin hyväksi on laskettu siten, että A-Starin suoritusaika on jaettu Dijkstran suoritusajalla.
Tämän jälkeen saatu luku on vähennetty 1:stä ja kerrottu sadalla. Eli:

(1 - (A-Starin suoritusaika) / (Dijkstran suoritusaika)) * 100 %. 

### Suorituskykytestin toisintaminen

Suorituskykytestin voi ajaa valitsemalla ohjelman käyttöliittymässä komennon "S" (suorituskykytesti).


### Suorituskykytesti kuvaajina

![Helsinki-Jepua](/documents/img/hki-jepua.png)

![Helsinki-Kemi](/documents/img/hki-kemi.png)

![Hanko-Jokela](/documents/img/hanko-jokela.png)

![Rovaniemi-Turku](/documents/img/rovaniemi-turku.png)

![Helsinki-Turku](/documents/img/hki-turku.png)
