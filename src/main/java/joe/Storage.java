package joe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Facilitates storing/reading the tasks specified by the user in/from a
 * tasks.txt file in the user's home directory
 */
public class Storage {

    private static final String userHome = System.getProperty("user.home");
    private static final String FILE_PATH = userHome + "/tasks.txt";
    private final TaskList taskList;
    private final File file;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
        this.file = new File(FILE_PATH);
    }

    /**
     * Initiation method: Reads previously defined tasks stored in the FILE_PATH location into this object's TaskList.
     * If no file exists, a new file is created
     * @return this Storage object, with an existing (and possibly empty) tasks.file to read and write Tasks to.
     */
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

    /**
     * Closing method: takes the tasks of this object's TaskList and writes them
     * into the file in the FILE_PATH location
     */
    public void writeAndClose() {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            IntStream.rangeClosed(1, this.taskList.size())
                    .boxed()
                    .map(i -> this.taskList.toTaskString(i))
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

    /**
     * Reads in Task descriptions from the file in FILE_PATH and loads them into this objectsTaskList
     * @throws IOException if the file is corrupted or other unexpected problems arise when reading from the file
     */
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
            this.taskList.loadToList(item);
        }
    }
}
