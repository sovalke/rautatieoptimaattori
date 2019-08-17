# Viikkoraportti 2

* Minua kehotettiin käyttämään työssä ulkopuolista dataa. Päätin edetä niin, että kirjoitan ensin algoritmin ja käytän rakentamisessa apuna yksinkertaista itse generoitua verkkoa. Kun olen saanut algoritmit toimintakuntoon, otan mukaan ulkopuolisen datan.

* Tiistai: Dijkstran algoritmin ensimmäinen versio valmis. Käytetty aika: 12 tuntia.

* Keskiviikko: A*-algoritmin ensimmäinen versio valmis. Käytetty aika: 4 tuntia.

* Torstai: A*-algoritmin paranneltu versio valmis. Sekä Dijkstra että A\* refaktoroitu. Käytetty aika: 7 tuntia.

* Perjantai: laadittu muutamia perustason JUnit-testejä sekä yksi A-Star-spesifi testi. Jotta saatoin olla varma, että A-Star toimii oikein, halusin laatia testin, jossa A-Star ja Dijkstra antavat samalle reitille eri tulokset. Valitsin testiin välin Lahti-Turku, sillä tällä reitillä rautatiet tekevät sopivan mutkan. Lyhin reitti kulkee Helsingin kautta, mutta A-Starin:n mielestä paras reitti kulkee Riihimäen kautta, sillä se on linnuntietä noin 10 km lähempänä Turkua kuin Helsinki. Esimerkkiverkossani A-Star kiertää Tampereen kautta, koska Helsingin ja Riihimäen välillä ei ole yhteyttä. Varmistin kuitenkin, että myös oikeassa rataverkossa tämän testin pitäisi toimia, vaikka yhteys Helsinki-Riihimäki olisikin olemassa. (Joudun tosin vaihtamaan oikean aineiston kohdalla kilometrien tilalle minuutit ja sekunnit.) Käytetty aika: 2 tuntia.

* MUISTIIN: rakenna paremmat ja kattavammat testit ensi viikon aikana.

Viikolla käytetty työaika: 25 tuntia.
