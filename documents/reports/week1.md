# Viikkoraportti 1

* Valittu aiheeksi Dijkstran ja A\*-algoritmin tehokkuuden vertailu reitinhaussa. Minulla oli alun perin toinen aihe (nominien taivuttaminen alboritmisesti), mutta koska ko. aiheen laajuutta ja vaativuutta on hyvin vaikea määritellä etukäteen ja lisäksi työssä ei välttämättä olisi tarvittavaa algoritmista syvyyttä, päätin valita selkeästi rajatun ja tavanomaisemman aiheen.

* Tarkennettu aihetta: rautatiereittien etsiminen kaksisuuntaisesta, painotetusta verkosta. Tämä on mielestäni järkevin mahdollinen tapa mallintaa rautatieverkkoa.

* Luotu Github-repo ja otettu siihen yhteys terminaalin kautta. Tämä oli viikon vaikein pala. Jostain syystä pushaaminen ei HTTPS:n kautta onnistunut – Githubin mielestä minulla ei ollut tarvittavia oikeuksia omaan projektiini – joten päätin käyttää ssh:ta. Tämä aiheuttikin päänsärkyä. Ilmeisesti MacOS:n päivityksen yhteydessä on tullut muutos käyttöjärjestelmän toimintaan, eikä olemassaolevia ssh-avaimia ollutkaan automaattisesti käytettävissä, kun yritin ottaa yhteyttä Githubiin Terminalin kautta. Parin tunnin säätämisen jälkeen sain ssh-avaimet käyttöön. Tämä edellytti pientä käyttöjärjestelmän asetusten säätelyä ja uuden asetustiedoston luomista komentorivin kautta, mikä on itselleni melko vierasta puuhaa. Onnistui onneksi lopulta, ja nyt ssh toimii.

* Pohdittu, mikä tietorakenne soveltuisi työhön parhaiten. Päätin valita keon, sillä sitä käytetään Laaksosen Tira-materiaalissa Dijkstran algoritmin toteutuksessa. A\* lienisi ehkä järkevämpää toteuttaa linkitettynä listana, mutta koska linkitetty lista ja keko ovat sangen läheisiä sukulaisia, päätin lähteä kokeilemaan, voisiko sekä Dijkstran että A\*:n toteuttaa keon avulla.

* Laadittu määrittelydokumentti ja ensimmäinen viikkoraportti.

* Käytetty aika yhteensä: noin 12 tuntia.