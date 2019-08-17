# Ohjelman toteutus

## Ohjelman yleisrakenne

.gitignore: gitin asetukset

README.md: lueminut-infotiedosto

pom.xml: asetustiedosto Mavenia ym. varten

**/data**: sisältää csv-muotoiset tiedostot, joita käytetään ohjelman aineistona

**/documents**: sisältää ohjelman dokumentaation

⋅⋅⋅ **/reports**: sisältää viikkoraportit

**/src**: varsinainen ohjelmakoodi

⋅⋅⋅ **/main/java**: java-paketit & itse ohjelmakoodi

⋅⋅⋅ **/test/java/rautatieoptimaattori**: ohjelmaan liittyvät JUnit-testit

## Käsitelävä data

Ohjelman käyttämä data (junien ja asemien tiedot) tallennetaan **data**-kansioon .csv-tiedostoina. Tiedostojen sisällä datan on noudatettava seuraavia rakenteita:

### Junien tiedot

Junien tiedot tallennetaan .csv-dokumenttiin, jonka ensimmäisen rivi on:

> from,to,departs,arrives

Mikäli ensimmäinen rivi poikkeaa tästä, ohjelma ei käsittele tiedostoa.

Ensimmäisen rivin jälkeen data esitetään siten, että jokaiselle riville tulee aina yhden junan tiedot. Jokainen tietokenttä erotetaan edellisestä pilkulla.

| Sarake        | Sisältö                        |
| ------------- |:------------------------------:|
| from          | int (aseman id)                |
| to            | int (aseman id)                |
| departs       | aikaleima (simple date format) |
| arrives       | aikaleima (simple date format) |

Esimerkki kelpuutettavasta tiedostosta:

> from,to,departs,arrives
> 1,10,2019-07-30T03:57:00.000Z,2019-07-30T04:02:00.000Z
> 10,10,2019-07-30T04:02:00.000Z,2019-07-30T04:03:00.000Z

### Asemien tiedot

Asemien tiedot tallennetaan .csv-dokumenttiin, jonka ensimmäinen rivi on:

> Name,id,lat,long

Mikäli ensimmäinen rivi poikkeaa tästä, ohjelma ei käsittele tiedostoa.

Ensimmäisen rivin jälkeen data esitetään siten, että jokaiselle riville tulee aina yhden aseman tiedot. Jokainen tietokenttä erotetaan edellisestä pilkulla.

| Sarake        | Sisältö                        |
| ------------- |:------------------------------:|
| Name          | String (aseman nimi)           |
| id            | int (aseman id)                |
| lat           | long (aseman gps-koordinaatti) |
| long          | long (aseman gps-koordinaatti) |

Esimerkki kelpuutettavasta tiedostosta:

> Name,id,lat,long
>
> Ahonpää,1343,64.537118,25.006783
>
> Ahvenus,1000,61.291923,22.498185


## Aika- ja tilavaativuudet

Työn alla.

## Suorituskyky- ja O-analyysivertailu

Työn alla.

## Puutteita ja parannusehdotuksia

