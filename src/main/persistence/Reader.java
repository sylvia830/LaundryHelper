package persistence;

import model.LaundryCard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    public static final String DELIMITER = ",";

    public static List<LaundryCard> readLaundryCards(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    private static List<LaundryCard> parseContent(List<String> fileContent) {
        List<LaundryCard> cards = new ArrayList<>();

        for (String line: fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            cards.add(parseCard(lineComponents));
        }
        return cards;
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static LaundryCard parseCard(List<String> components) {
        int balance = Integer.parseInt(components.get(0));
        return new LaundryCard(balance);
    }
}
