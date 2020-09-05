package Tiedostonpakkausohjelma.ui;

import Tiedostonpakkausohjelma.algorithms.DecompressHuffman;
import Tiedostonpakkausohjelma.algorithms.Huffman;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Luokka vastaa käyttöliittymästä.
 *
 */
public class AppUi {

    Scanner scanner;
    File file;
    FileHandler filereader;
    int number;

    public AppUi() {
        scanner = new Scanner(System.in);
    }

    /**
     * Metodi käynnistää ohjelman käyttöliittymän.
     *
     * @throws java.io.IOException
     */
    public void start() throws IOException {
        System.out.println("Käynnissä");
        while (true) {
            try {
                dialog();
            } catch (NumberFormatException e) {
                System.out.println("Valinta on virheellinen. Yritä uudestaan.");
            }
            if (number == 3) {
                System.out.println("Ohjelma sammutettu.");
                break;
            } else {
            }
        }
    }

    /**
     * Metodi käynnistää käyttöliittymän dialogin, jossa valitaan mitä halutaan
     * tehdä.
     *
     */
    void dialog() throws IOException {
        System.out.println("Paina 1 + ENTER, jos haluat pakata tiedoston");
        System.out.println("Paina 2 + ENTER, jos haluat purkaa tiedoston");
        System.out.println("Paina 3 + ENTER, jos haluat lopettaa");
        System.out.println("Anna valinta: ");
        number = Integer.parseInt(scanner.nextLine());
        if (number == 1) {
            compressHuffman();
        }
        if (number == 2) {
            decompressHuffman();
        }
    }

    /**
     * Metodi käynnistää tiedoston pakkauksen Huffmanin algoritmilla.
     *
     */
    void compressHuffman() throws IOException {
        System.out.println("Anna pakattavan tiedoston osoite niin, että \\-merkin tilalla on /-merkki ");
        file = new File(scanner.nextLine());
        System.out.println("Anna nimi, jolla pakattu tiedosto tallennetaan. ");
        String name = new String(scanner.nextLine());
        long startTime = System.currentTimeMillis();
        filereader = new FileHandler(file);
        Huffman huffman = new Huffman(filereader, name);
        try {
            huffman.startHuffman();
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("Aikaa pakkaamiseen kului: " + endTime + " millisekuntia");
            System.out.println(filereader.compressionRate());
        } catch (IOException e) {
            System.out.println("Tiedoston luku ei onnistunut. Tarkista tiedoston nimi ja yritä uudestaan.");
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("Tiedoston luku ei onnistunut. Tarkista tiedoston nimi ja yritä uudestaan.");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Metodi käynnistää Huffmanin algoritmilla pakatun tiedoston purkamisen.
     *
     */
    void decompressHuffman() throws IOException {
        System.out.println("Anna purettavan tiedoston osoite niin, että \\-merkin tilalla on /-merkki ");
        String text = scanner.nextLine();
        System.out.println("Anna puretun tiedoston nimi.");
        String name = scanner.nextLine();
        long startTime = System.currentTimeMillis();
        file = new File(text);
        filereader = new FileHandler(file);
        DecompressHuffman decompress = new DecompressHuffman(text, filereader, name);
        try {
            decompress.decompress();
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("Aikaa purkamiseen meni: " + endTime + " millisekuntia");
        } catch (IOException e) {
            System.out.println("Tiedoston luku ei onnistunut. Tarkista tiedoston nimi ja yritä uudestaan.");
        } catch (Exception e) {
        }
    }

}
