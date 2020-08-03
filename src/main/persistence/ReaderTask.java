package persistence;

import model.LaundryTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// a reader that can read laundry task data to file
public class ReaderTask {

    public static final String DELIMITER = ",";
    private persistence.Reader reader;

    // EFFECTS: returns a list of tasks parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static LinkedList<LaundryTask> readLaundryTask(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of tasks parsed from list of strings
    // where each string contains data for one account
    private static LinkedList<LaundryTask> parseContent(List<String> fileContent) {
        LinkedList<LaundryTask> tasks = new LinkedList<LaundryTask>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            tasks.add(parseCard(lineComponents));
        }
        return tasks;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 1 where element 0 represents the
    // machineID of the laundry task
    // EFFECTS: returns a laundry task constructed from components
    private static LaundryTask parseCard(List<String> components) {
        int machineID = Integer.parseInt(components.get(0));
        return new LaundryTask(machineID);

    }
}
