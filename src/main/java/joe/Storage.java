package joe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Storage {

    private static final String userHome = System.getProperty("user.home");
    private static final String FILE_PATH = userHome + "/tasks.txt";
    private final TaskList toDoItemArrayList;
    private final File file;

    public Storage(TaskList toDoItemArrayList) {
        this.toDoItemArrayList = toDoItemArrayList;
        this.file = new File(FILE_PATH);
    }

    public Storage load() {
        if (this.file.exists()) {
            try {
                readFromFile();
            } catch (IOException ioException) {
                System.out.println("Something went wrong during loading tasks.");
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                System.out.println("Creating a file for storing the tasks was unsuccessful.");
            }
        }
        return this;
    }

    public void writeAndClose() {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            IntStream.rangeClosed(1, this.toDoItemArrayList.size())
                    .boxed()
                    .map(i -> this.toDoItemArrayList.toTaskString(i))
                    .forEachOrdered(taskDescription -> {
                        try {
                            writer.write(taskDescription + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    });
            writer.close();
        } catch (IOException e) {
            System.out.println("Saving tasks unsuccessful. File not found!");
        }
    }

    public void readFromFile() throws IOException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            String taskTypeToken = tokens[0];
            Task item;
            switch (taskTypeToken) {
            case "[T]":
                item = Todo.readInTodo(line);
                break;
            case "[E]":
                item = Event.readInEvent(line);
                break;
            case "[D]":
                item = Deadline.readInDeadline(line);
                break;
            default:
                System.out.println("Unknown task type during file loading");
                throw new IOException();
            }
            this.toDoItemArrayList.loadToList(item);
        }
    }
}
