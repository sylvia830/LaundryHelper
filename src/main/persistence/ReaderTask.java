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

    public static LinkedList<LaundryTask> readLaundryTask(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    private static LinkedList<LaundryTask> parseContent(List<String> fileContent) {
        LinkedList<LaundryTask> tasks = new LinkedList<LaundryTask>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            tasks.add(parseCard(lineComponents));
        }
        return tasks;
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static LaundryTask parseCard(List<String> components) {
        int machineID = Integer.parseInt(components.get(0));
        return new LaundryTask(machineID);

    }
}
