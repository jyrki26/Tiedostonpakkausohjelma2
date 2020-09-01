package Tiedostonpakkausohjelma.fileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileHandler {

    private File file;

    public FileHandler(File file) {
        this.file = file;
    }

    /**
     * Metodi lukee tiedoston, joka halutaan pakata.
     *
     * @return Luettu tiedosto String-muodossa.
     *
     */
    public String Read() {
        String text = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                text = text + line + "\n";
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String correct = text.substring(0, text.length() - 1);
        return correct;
    }

    public String readFile(File fileToRead) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

    public void writeToFile(String text, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
            System.out.println("Tallennus onnistui");
            writer.close();
        } catch (Exception e) {
            System.out.println("Tallennus epäonnistui " + e);
        }

    }

    public void writeBytes(String filename, byte[] bytes) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(bytes);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
    public byte[] readBytes(String file) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        return bytes;
    }
}
