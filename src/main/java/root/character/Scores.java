package character;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scores {
    public static void save(int score) {
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String myDateToString = myDate.format(myFormat);

        try {
            FileWriter fw = new FileWriter("scores.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(Integer.toString(score)).append(", ").append(myDateToString);
            writer.newLine();

            writer.flush();
            writer.close();
        } catch (IOException exception) {
            System.out.println("Erreur à l'écriture du fichier.");
        }
    }
}
