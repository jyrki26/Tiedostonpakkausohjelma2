# Testausraportti

### Testien suorittaminen
Yksikkötestit voidaan suorittaa joko Java-editorissa ajamalla testitiedostot tai antamalla terminaalissa kansiossa, jossa ohjelma on komento 
```terminal
gradle test
```
Suorituskykytestauksessa käytetyt tiedostot löytyvät kansiosta dokumentaatio. <br/>

Jacocon testikattavuusraportin saa luotua tiedoston juurikansiossa komennolla:
```
gradle jacocoTestReport
```

Tämän jälkeen raportti löytyy kansiosta ```/Tiedostonpakkausohjelma2/Tiedostonpakkausohjelma/build/jacoco/test/```

### Yksikkötestaus
Yksikkötestaus on suoritettu omille tietorakenteille ja keskeisiltä osin ohjelman logiikasta vastaaville luokille. Testaus on suoritettu käyttäen JUnitin avulla luotuja testitapauksia. Yksikkötestauksessa on painotettu pakkauksessa tools olevien omien tietorakenteiden toimivuuden testausta. Lisäksi logiikasta vastaavaassa pakkauksessa algorithms olevia luokkia on testattu melko laajalti. Käyttöliittymää koskevaa pakettia ui ei ole testattu yksikkötestauksella, koska se ainoastaan antaa komennot aiemmin mainituille luokille. Alla testikattavuusraportti.

![Testikattavuusraportti](https://github.com/jyrki26/Tiedostonpakkausohjelma2/blob/master/dokumentaatio/testikattavuus.png)


### Suorituskykytestaus
Suorituskykyä on testattu vertaamalla pakatun tiedoston kokoa alkuperäiseen tiedostoon ja vertaamalla pakkaamiseen käytettyä aikaa pakatun tiedoston kokoon. Suorituskyky on tämän perusteella kohtuullinen. Pakattu tiedosto on noin keskimäärin noin 57 prosenttia alkuperäisen koosta, jos alkuperäinen tiedosto on riittävän suuri. Pienissä muutaman tavun kokoisissa tiedostoissa pakkaaminen ei pienennä tiedostoa, koska pakkauksen purkamisessa käytetty merkkijono vie suhteessa niin paljon tilaa. <br/>
Testauksessa on käytetty BBC:n avoimesti saatavilla olevia txt-muotoisia tiedostoja sekä yhtä huomattavasti suurempaa tiedostoa. Kaikki tiedostot löytyvät repositorion kansiosta dokumentaatio. Testaukseen on valittu erikokoisia tiedostoja, jotta suorituskykyä voidaan verrata laajasti. Testaus on suoritettu sammuttaen ohjelma aina yhden pakatun tiedoston jälkeen ja pakkaamalla sama tiedosto uudestaan sammuttamatta ohjelmaa. Pakkausta on testattu kummallakin tavalla viisi kertaa jokaisella tiedostolla. BBC:n tiedostot ovat saatavissa osoitteesta: http://mlg.ucd.ie/datasets/bbc.html. Tiedosto test4.txt on luotu ns. lorem ipsum -generaattorilla. <br/>

Vastaavasti on myös testattu pakkauksen viemää aikaa. Pakkaamisen viemä aika vaihtelee suuresti tiedoston mukaan,  keskimäärin ohjelma pakkaa noin 37 tavua millisekunnissa, jos ohjelma on juuri käynnistetty. Jos ohjelma on ollut käynnissä pakkaa se kuitenkin noin 114 tavua millisekunnissa. Tähän aikaan on laskettu mukaan tiedoston lukeminen ja tallentaminen, joten varsinaiseen pakkaamisen menevä aika on nopeampi. Ero pakkausaikojen keskiarvossa saattaa johtua siitä, että ohjelman ollessa käynnissä tiedosto on jo välimuistissa ja sen käsittelyyn menee näin ollen vähemmän aikaa. Tätä tukisi myös se, että testatessa tiedosto test4.txt oli eri kansiossa ja sen suoritusajat olivat molemmilla tavoilla hitaampia kuin muiden tiedostojen. 
Alla olevissa kuvaajissa on kuvattu pakkaukseen tarvittu aika yksikössä pakattuja tavuja sekunnissa. Kuvaajasta näkee selvästi kuinka huomattavat erot eri tiedostojen välillä. Tiedostot on kuvattu suuruusjärjestyksessä niin, että 397.txt on pienin ja test4.txt on suurin. Koko testidata löytyy dokumentaatiosta tiedostosta testadata.odt.

![Kuvaaja 1](https://github.com/jyrki26/Tiedostonpakkausohjelma2/blob/master/dokumentaatio/chart1.png)
![Kuvaaja 1](https://github.com/jyrki26/Tiedostonpakkausohjelma2/blob/master/dokumentaatio/chart2.png)

### Muu testaus

Ohjelmaa on testattu tutkivalla testauksella suorittamalla ohjelmaa erilaisilla tiedostoilla.



