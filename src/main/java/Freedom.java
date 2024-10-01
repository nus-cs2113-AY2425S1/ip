import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import freedom.exceptions.CannotCreateDirectory;
import freedom.exceptions.CannotReadFile;
import freedom.exceptions.InvalidCommand;
import freedom.tasks.TaskList;
import freedom.tasks.Task;
import freedom.tasks.ToDo;
import freedom.tasks.Deadline;
import freedom.tasks.Event;

public class Freedom {
    static ArrayList<Task> tasks = new ArrayList<Task>();
    static int lastIndex = 0;

    private static TaskList taskList;

    static final String LOGO = "\t________________________________________\n";
    static final String DATA_FILE_PATH = "./data/freedom.txt";

    public static void main(String[] args) {
        final String START_MESSAGE = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        final String CLOSING_MESSAGE = "\tBye. Hope to see you again soon!\n";

        System.out.println(LOGO + START_MESSAGE + LOGO);

        taskList = new TaskList();

        Scanner in = new Scanner(System.in);

        try {
            checkData();
            loadData();

            // Super loop for getting inputs
            while (in.hasNextLine()) {
                String line = in.nextLine();

                if (line.equals("bye")) {
                    break;
                }
                handleInput(line);
            }

            saveData();
        } catch (Exception e) {
            System.out.print("");
        }

        System.out.println(LOGO + CLOSING_MESSAGE + LOGO);
    }

    public static void handleInput(String input) {
        final int COMMAND_INDEX = 0;
        String[] words = input.split(" ");
        String description;

        try {
            switch (words[COMMAND_INDEX]) {
            case "list":
                taskList.printList();
                return;
            case "mark":
            case "unmark":
            case "delete":
                taskList.editTask(words);
                return;
            case "todo":
                taskList.addTask("todo", input);
                break;
            case "deadline":
                taskList.addTask("deadline", input);
                break;
            case "event":
                taskList.addTask("event", input);
                break;
            default:
                throw new InvalidCommand();

            }

            saveData();
        } catch (InvalidCommand e) {
            System.out.println(LOGO + "\tSorry! I do not understand your command");
            System.out.println(LOGO);
        } catch (Exception e) {
            System.out.print("");
        }
    }

    public static void loadData() throws Exception{
        final int COMMAND_INDEX = 0;
        final int STATUS_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int TIME_ONE = 3;
        final int TIME_TWO = 4;

        boolean isDone;

        try {
            File data = new File(DATA_FILE_PATH);
            Scanner read = new Scanner(data);
            while (read.hasNextLine()) {
                String[] words = read.nextLine().split("[|]");
                isDone = words[STATUS_INDEX].trim().equals("X");
                switch(words[COMMAND_INDEX].trim()) {
                    case "T":
                        tasks.add(new ToDo(words[DESCRIPTION_INDEX].trim(), isDone));
                        break;
                    case "D":
                        tasks.add(new Deadline(words[DESCRIPTION_INDEX].trim(),
                                isDone, words[TIME_ONE].trim()));
                        break;
                    case "E":
                        tasks.add(new Event(words[DESCRIPTION_INDEX].trim(), isDone,
                                words[TIME_ONE].trim(), words[TIME_TWO].trim()));
                        break;
                    default:
                        throw new CannotReadFile();
                }
                lastIndex++;
            }
            System.out.println("\tData Loaded!");
            taskList.printList();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (CannotReadFile e) {
            System.out.print(LOGO);
            System.out.println("\tCannot Load Data! File might be corrupted :(");
            System.out.println(LOGO);
            throw new Exception();
        } catch (Exception e) {
            System.out.print("");
        }
    }

    public static void saveData() {
        Task taskToAdd;

        try (FileWriter writer = new FileWriter(DATA_FILE_PATH)) {
            for (int i = 0; i < lastIndex; i++) {
                taskToAdd = tasks.get(i);
                writer.write(taskToAdd.generateStorageLine());
            }
        } catch (IOException e) {
            System.out.print(LOGO);
            System.out.println("\tCannot open data file :((");
            System.out.println(LOGO);
        }
    }

    public static void checkData() throws Exception{
        try {
            File directory = new File("./data");
            if(!directory.exists()) {
                if(!directory.mkdir()) {
                    throw new CannotCreateDirectory();
                }
            }
            File dataFile = new File("./data/freedom.txt");
            if(dataFile.createNewFile()) {
                System.out.println("\tData file created!");
            }
        } catch (IOException e) {
            System.out.print(LOGO);
        } catch (CannotCreateDirectory e) {
            System.out.print(LOGO);
            System.out.println("\tCannot create directory :(");
            System.out.println(LOGO);
            throw new Exception();
        }
    }
}
