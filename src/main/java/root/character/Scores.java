package character;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Scores {
    static HashMap<String, String> scores = new HashMap<>();
    final static String source = "scores.txt";

    public static void save(int score) {
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String myDateToString = myDate.format(myFormat);

        String content = score + "," + myDateToString + System.lineSeparator();

        try {
            FileWriter fw = new FileWriter(source, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            System.out.println("Données sauvegardées avec succès.");

            read();
        } catch (IOException exception) { System.out.println("Erreur à l'écriture du fichier."); }
    }

    public static void read() throws IOException {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = br.readLine()) != null) { linesList.add(line); }
        } catch (IOException exception) { System.out.println("Erreur à la lecture du fichier."); }

        String[] linesArray = linesList.toArray(new String[0]);
        extract(linesArray);
    }

    public static void extract(String[] linesArray) {
        int size = linesArray.length;
        int[] unsortedScores = new int[size];
        String[] correspondingScoresDate = new String[size];

        for (int i = 0; i < linesArray.length; i++) {
            unsortedScores[i] = Integer.parseInt(linesArray[i].trim().split(",")[0]); // Le score est le premier élément ([score,date])
            correspondingScoresDate[i] = linesArray[i].trim().split(",")[1];
        }

        createMap(unsortedScores, correspondingScoresDate, size);
        sortedArray(unsortedScores);
    }

    public static void createMap(int[] unsortedScores, String[] correspondingScoresDate, int size) {
        for (int i = 0 ; i < size; i++) { scores.put(Integer.toString(unsortedScores[i]), correspondingScoresDate[i]); }
    }

    public static void sortedArray(int[] unsortedScores) {
        Arrays.sort(unsortedScores);
        String[] finalArray = new String[unsortedScores.length];

        for (int i = 0; i < unsortedScores.length; i++) {
            String newLine = scores.get(Integer.toString(unsortedScores[i]));
            finalArray[i] = unsortedScores[i] + "," + newLine;
        }

        writeBack(finalArray);
    }

    public static void writeBack(String[] finalArray) {
       try (BufferedWriter bw = new BufferedWriter(new FileWriter(source))) {
           for (int i = finalArray.length - 1; i >= 0; i--) {
               bw.append(finalArray[i]);
               bw.newLine();
           }
       } catch (IOException exception) { System.out.println("Erreur à la réécriture."); }
    }
}
