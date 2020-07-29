package persistence;

import model.LaundryCard;
import model.LaundryTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// A reader that can read laundryTask data from a file
public class Reader {
    public static final String DELIMITER = ",";
    private static int machineID;

    // EFFECTS: returns a list of tasks parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<LaundryTask> readLaundryTasks(File file) throws IOException {
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
    private static List<LaundryTask> parseContent(List<String> fileContent) {
        List<LaundryTask> tasks = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            tasks.add(parseTask(lineComponents));
        }

        return tasks;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 1 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static LaundryTask parseTask(List<String> components) {
        int machineID = Integer.parseInt(components.get(0));
        return new LaundryTask(machineID);

    }
}

