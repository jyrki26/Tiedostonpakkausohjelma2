# Toteutusdokumentti

### Ohjelman toiminta
Ohjelma toteuttaa txt-tiedoston pakkauksen Huffmanin algoritmilla. Ohjelmalle annetaan tiedoston osoite kovalevyllä, jonka jälkeen ohjelma pakkaa sen. Ohjelmalla voi myös purkaa pakatun tiedoston. <br/> <br/>

Ohjelman toiminta perustuu alkuperäisessä tiedostossa olevien merkkien muuntamiseen niiden lukumäärän perusteella bittimuotoisiksi, niin useimmin esiintyvien merkkien esitys on lyhyempi ja harvemmin esiintyvien pidempi.
Tämän jälkeen merkit muutetaan tavun mittaisiksi merkeiksi, jotka tallennetaan pakattuun tiedostoon. Lisäksi tiedostoon tallennetaan koodi, jonka avulla pakatut merkit voi purkaa. Purettaessa merkit muutetaan ensiksi bittimuotoiseen muotoon ja tämän jälkeen puretaan koodin perusteella alkuperäiseksi tekstiksi ja tallenetaan tiedostoon.

### Ohjelman laskennalliset aika- ja tilavaatimukset
Huffmanin algoritmin laskennallinen aikavaatimus on O(n logn), jossa n on pakattavassa tiedostossa olevien eri merkkien määrä.

### Lähteet
TODO