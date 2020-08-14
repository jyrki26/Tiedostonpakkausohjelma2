# Viikkoraportti 2

Aikaa työn tekoon on käytetty tällä viikolla noin 20 tuntia.

### Mitä olen tällä viikolla tehnyt?
Tällä viikolla olen aloittanut varsinaisen koodaamisen ja testauksen sekä lisännyt ja yrittänyt saada kaikki pluginit toimimaan. Olen lisännyt Checkstylen ja JaCoCon pluginit.

### Miten ohjelma on edistynyt?
Ohjelma ei ole edistynyt niin paljon kuin olisin halunnut. Pluginien kanssa on ongelmia, joiden ratkaiseminen on vaatinut aikaa, joten varsinaiselle koodaamiselle on jäänyt vähemmän aikaa.

### Mitä opin tällä viikolla?
Perehdyin lisää Huffmanin algoritmiin ja siihen miten sen voi toteuttaa Javalla.

### Mitä on tuottanut vaikeuksia?
Yksikkötestit menevät läpi ilman ongelmia, jos pom.xml -tiedostoon ei ole lisätty JaCoCon pluginia. Jos plugin on lisätty, testejä ei pysty ajamaan läpi Netbeansissa tai Git Bashissa. Plugin aiheuttaa seuraavan virheen
> Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test (default-test) on project Tiedostonpakkausohjelma: There are test failures.

Olen yrittänyt lisätä surefire-pluginin eri versioilla ja kokeillut eri versioita JaCoCosta, mutta en ole löytänyt ratkaisua.

### Mitä teen seuraavaksi?
Seuraavaksi käytän aikaa Huffmanin algoritmin saamiseksi valmiiksi ja sen tallentamiseksi tiedostoon.
