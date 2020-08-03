package persistence;

import model.LaundryCard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// a reader that can read laundry card balance data to file
public class Reader {
    public static final String DELIMITER = ",";
    private Reader reader;


    // EFFECTS: returns a list of cards parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<LaundryCard> readLaundryCards(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of cards parsed from list of strings
    // where each string contains data for one account
    private static List<LaundryCard> parseContent(List<String> fileContent) {
        List<LaundryCard> cards = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            cards.add(parseCard(lineComponents));
        }
        return cards;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 1 where element 0 represents the
    // balance of the laundry card to be constructed
    // EFFECTS: returns a laundry card constructed from components
    private static LaundryCard parseCard(List<String> components) {
        int balance = Integer.parseInt(components.get(0));
        return new LaundryCard(balance);
    }
}
