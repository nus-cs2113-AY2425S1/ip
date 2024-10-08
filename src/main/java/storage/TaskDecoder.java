package storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.*;


public class TaskDecoder {
    private static final String pathName = "./data/Tasks.txt";
    private static final Path path = Path.of("./data");
    public static TaskList readTasks() throws IOException {
        File tasks = new File(pathName);
        Scanner input = new Scanner(tasks);
        ArrayList<Task> list = new ArrayList<>();
        while (input.hasNextLine()) {
            String in = input.nextLine();
            String[] splitInput = in.split(" \\| ");
            if (splitInput[0].equals("[T]")) {
                list.add(new Todo(splitInput[2], Boolean.parseBoolean(splitInput[1])));
            } else if (splitInput[0].equals("[D]")) {
                list.add(new Deadlines(splitInput[2], Boolean.parseBoolean(splitInput[1]),
                        LocalDate.parse(splitInput[3])));
                System.out.println(splitInput[0]);
            } else if (splitInput[0].equals("[E]")) {
                list.add(new Event(splitInput[2], Boolean.parseBoolean(splitInput[1]),
                        LocalDate.parse(splitInput[3]), LocalDate.parse(splitInput[4])));
            }
        }
        return new TaskList(list);
    }
}
