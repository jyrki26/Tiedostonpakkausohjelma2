# Toteutusdokumentti

### Ohjelman toiminta
Ohjelma toteuttaa txt-tiedoston pakkauksen Huffmanin algoritmilla. Ohjelmalle annetaan tiedoston osoite kovalevyllä ja nimi pakatulle tiedostolle, jonka jälkeen ohjelma pakkaa sen. Ohjelmalla voi myös purkaa pakatun tiedoston. <br/> <br/>

Ohjelman toiminta perustuu alkuperäisessä tiedostossa olevien merkkien muuntamiseen niiden lukumäärän perusteella bittimuotoisiksi, niin että useimmin esiintyvien merkkien esitys on lyhyempi ja harvemmin esiintyvien pidempi.
Tämän jälkeen merkit muutetaan tavuiksi, jotka tallennetaan pakattuun tiedostoon. Lisäksi tiedostoon tallennetaan koodi, jonka avulla pakatut merkit voi purkaa. Purettaessa merkit muutetaan ensiksi bittimuotoiseen muotoon ja tämän jälkeen puretaan koodin perusteella alkuperäiseksi tekstiksi ja tallenetaan tiedostoon.

### Ohjelman rakenne
Ohjelmassa on ohjelman käynnistävä luokka Main ja neljä pakkausta, joista Tiedostonpakkausohjelma.ui vastaa käyttöliittymästä, Tiedostonpakkausohjelma.fileHandler tiedoston luvusta ja tiedostoon kirjoittamisesta, Tiedostonpakkausohjelma.tools sisältää omat tietorakenteet ja työkalun tavujen muuttamiseksi String-muotoiseksi ja takaisin sekä logiikasta vastaava luokka Tiedostonpakkausohjelma.algorithms. Algorithms sisältää myös binääripuun ja hajautustaulun solmut luovat oliot. Luokka Huffman vastaa tiedoston pakkaamisesta ja luokka DecompressHuffman pakatun tiedoston purkamisesta.

### Ohjelman laskennalliset aika- ja tilavaatimukset
Huffmanin algoritmin laskennallinen aikavaatimus on O(n log n), jossa n on pakattavassa tiedostossa olevien eri merkkien määrä. Pienintä solmua poistettaessa puuta kutsutaan 2 (n-1) kertaa ja pienimmän solmun poistamisen aikavaatimus on O(log n), joten kokonaisvaatimus on edellä mainittu O(n log n). Muiden osien, esimerkiksi hajautustaulun, aikavaatimukset ovat tätä pienempiä, joten ne eivät vaikuta kokonaisaikavaatimukseen. <br/>

Tilavaatimus on O(n), jossa n on uniikkien merkkien lukumäärä, koska merkkien esiintymisten lukumäärää tallentaessa jokaiselle merkille tulee tallentaa esiintymisten lukumäärä hajautustauluun.

### Puutteet ja parannusehdotukset
Työn oli alunperin tarkoitus vertailla kahta tai useampaa pakkausalgoritmia. Lähdin toteuttamaan ohjelmaa niin, että sovellus tallentaa pakattavan tiedoston tekstimuodossa, joka aiheutti ongelmia, koska kaikki merkit eivät tallentuneet oikein. Tämän ongelman ratkaiseminen vei sen verran aikaa, että en ehtinyt toteuttaa toista algoritmia. Tässä on siis selvä puute. <br/>

Ohjelman rakennetta voisi muutenkin parantaa. Koska ohjelma lukee pakattavan tiedoston tekstimuodossa, ei ohjelma joko toimi ollenkaan tai saattaa toimia virheellisesti muilla tiedostotyypeillä kuin txt. Tässä olisi siis selvä kehityskohde. Kurssin aihealueen ulkopuolella oleva kehityskohde olisi graafisen käyttöliittymän toteuttaminen. Vaikka ohjelma on nyt melko helpokäyttöinen, tekisi graafinen käyttöliittymä siitä yksinkertaisemman käyttää.




### Lähteet
* https://fi.wikipedia.org/wiki/Huffmanin_koodaus
* https://en.wikipedia.org/wiki/Huffman_coding
* https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
* https://algs4.cs.princeton.edu/55compression/Huffman.java.html
* https://stackoverflow.com/questions/759707/efficient-way-of-storing-huffman-tree
* https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/