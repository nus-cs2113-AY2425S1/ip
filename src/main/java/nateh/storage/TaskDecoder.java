package nateh.storage;

import nateh.classes.Deadlines;
import nateh.classes.Event;
import nateh.classes.Task;
import nateh.classes.Todo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskDecoder {
    private static final String pathName = "./data/Tasks.txt";
    private static final Path path = Path.of("./data");
    public static ArrayList<Task> readTasks() throws IOException {
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
                        splitInput[3]));
                System.out.println(splitInput[0]);
            } else if (splitInput[0].equals("[E]")) {
                list.add(new Event(splitInput[2], Boolean.parseBoolean(splitInput[1]),
                        splitInput[3], splitInput[4]));
            }
        }
        return list;
    }
}
