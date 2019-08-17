# Työn määrittelyt

## Yleiset

Työssä käytettävä ohjelmointikieli on Java.

Työssä käytetään kaksisuuntaista painotettua verkkoa kuvaamaan rautatieverkkoa. Verkon painot kuvaavat etäisyyksiä.

Työn tarkoitus on etsiä mahdollisimman lyhyt (tehokas) reitti valitulta rautatieasemalta määränpäähän.

## Algoritmit ja tietorakenteet

### Valitut algoritmit

Työssä käytetään reittien etsimiseen Dijkstran algoritmia ja A*-algoritmia (tehokkuuden vertailu).

Nämä algoritmit on valittu ennen kaikkea siksi, että ne ovat helposti lähestyttäviä ja hyvin tunnettuja reitinhakualgoritmeja. Lisäksi niitä voidaan pitää kohtalaisen tehokkaina ja suoraviivaisina toteuttaa.

### Tietorakenteet

Tärkein työssä käytettävä tietorakenne on keko (heap). Tämä rakenne on optimaalinen Dijkstran algoritmin toteuttamiseen ja soveltunee myös A\*:iin.

Tarpeen vaatiessa työssä käytettäneen myös listarakenteita.

## Ohjelman syötteet

Ohjelmalle annetaan syötteenä painotettu verkko, joka koostuu rautatieaseman nimistä ja etäisyyksistä muihin asemiin. Ohjelman mukana tulee valmis demoverkko testausta varten. Verkkoon voi lisätä manuaalisesti lisää solmuja.

Verkosta haetaan lyhintä etäisyyttä ilmoittamalla halutun lähtöpaikan ja määränpään nimet muodossa "Lähtöpaikka-Määränpää", esimerkiksi "Helsinki-Lahti". Tämän jälkeen ohjelma laskee ja tulostaa lyhimmän mahdollisen reitin kilometreinä.

## Aika- ja tilavaativuudet

Tavoitteena on saavuttaa ohjelmalla Dijkstran algoritmille ja A\*:lle tyypilliset aikavaativuudet. Dijkstran kohdalla tämä tarkoittaa aikavaativuutta O(n + nlogn) (Laaksonen 2019: 127). A\* on hyvin läheistä sukua Dijkstralle, mutta sen pitäisi olla tehokkaampi (aikavaativuus O(n)) (Wikipedia).

Tilavaativuus on korkeintaan yhtä suuri kuin algoritmin aikavaativuus (Laaksonen 2019: 18).


# Lähteet

* Laaksonen, Antti 2019: Tietorakenteet ja algoritmit -kurssimoniste. Päivitetty 21.6.2019.
* Wikipedia: A* search algorithm. https://en.wikipedia.org/wiki/A*_search_algorithm. Luettu 24.7.2019.


