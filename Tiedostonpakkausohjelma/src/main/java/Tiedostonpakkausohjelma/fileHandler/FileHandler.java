
package Tiedostonpakkausohjelma.fileHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        return text;
    }

    public void writeToFile(String text, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
            System.out.println("Tallennus onnistui");
            writer.close();
        }
        catch(Exception e){
            System.out.println("Tallennus epäonnistui " + e);
        }
        
    }
}
