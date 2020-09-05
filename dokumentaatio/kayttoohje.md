# Käyttöohje

### Käynnistäminen
Ohjelman voi käynnistää joko Netbeanssissa tai menevällä konsolissa ohjelman sisältävään kansioon ja kirjoittamalla
```
gradle run
```
Lisäksi tiedoston voi käynnistää repositoriosta löytyvästä jar-tiedostosta menemällä terminaalissa siihen kansioon, jossa tiedosto on ja antamalla komennon 
```
java -jar Tiedostonpakkausohjelma.jar
```

### Ohjelman käyttäminen
Ohjelmassa on kolme vaihtoehtoa. Haluttu vaihtoehto valitaan ohjeiden mukaisesti joko numerolla 1, 2 tai 3. <br/>
Tiedostoa pakattaessa tulee antaa tiedoston osoite niin, että \-merkin tilalla on /-merkki. Tämän jälkeen tiedosto pakataan ohjelman juurikansioon käyttäjän antamalla nimellä. Tiedostomuotoa ei tarvitse antaa. <br/>
Tiedostoa purettaessa tulee antaa vastaavasti purettavan tiedoston osoite. Purettava tiedosto luodaan myös ohjelman juurikansioon käyttäjän antamalla nimellä. <br/>
Mikäli ohjelmaa käytetään jar-tiedostosta pakataan ja puretaan tiedostot siihen kansioon, jossa jar-tiedosto on. Helpointa tapa käyttää ohjelmaa on siirtää pakattava tiedosto ohjelman juurikansioon tai jar-tiedostoa käytettäessä siihen kansioon, jossa tiedosto on. Tällöin koko osoitteen kirjoittamisen sijaan riitää tiedoston nimen kirjoittaminen.
