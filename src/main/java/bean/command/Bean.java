package bean.command;

import bean.exceptions.EmptyListException;
import bean.exceptions.InsufficientSpaceException;
import bean.exceptions.InvalidInputException;
import bean.exceptions.TaskNumOutOfBoundsException;
import bean.task.Deadline;
import bean.task.Event;
import bean.task.Task;
import bean.task.Todo;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Bean {
    // Constants
    private final static String SEPARATOR_LINE = "_".repeat(60) + "\n";
    private final static String INDENT = "  ";
    private final static int MAX_LIST_COUNT = 100;
    private final static String LOGO = "  ┏━┓\n" +
            "  ┃ ┃\n" +
            "  ┃ ┗━━┳━━━┳━━━━┳━━━┓\n" +
            "  ┃  ┏┓┃ ┃━┫ ┏┓ ┃ ┏┓ ┓ ┏━━━━━━┓\n" +
            "  ┃  ┗┛┃ ┃━┫ ┏┓ ┃ ┃┃ ┃ ┃• ᴗ • ┫\n" +
            "  ┗━━ ━┻━━━┻━┛┗━┻━┛┗━┛ ┗━━━━━━┛\n";
    private static final String DATA_FILE_PATH = "data/bean.txt";
    private static  final String DELIMITER = "//";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    // Checks if data file exists and creates one if none exist
    public static void initialiseDataFile() throws IOException {
        File dataFile = new File(DATA_FILE_PATH);

        if (!dataFile.exists()) {
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        }
    }

    public static void deserialise(String toDeserialise) {

        if (toDeserialise == null) {
            throw new RuntimeException("Could not load saved data.");
        }

        // 'T||<1/0>||<description>'
        // 'D||<1/0>||<description>||<by>'
        // 'E||<1/0>||<description>||<from>||<to>'
        String[] parts = toDeserialise.split(DELIMITER);
        String taskClass = parts[0];
        Boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (taskClass.equals("T")) {
            // Todo
            tasks.add(new Todo(description, isDone));

        } else if (taskClass.equals("D")) {
            // Deadline
           String by = parts[3];
           tasks.add(new Deadline(description, by, isDone));

        } else if (taskClass.equals("E")) {
            // Event
            String from = parts[3];
            String to = parts[4];
            tasks.add(new Event(description, from, to, isDone));

        } else {
            throw new RuntimeException("Could not load saved data. File corrupted.");
        }
    }

    // TODO method to read bean.txt to retrieve tasks from memory on start
    public static void retrieveFromDataFile() throws IOException {
        File f = new File(DATA_FILE_PATH);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            deserialise(line);
        }
    }

    // Overwrite contents of bean.txt with updated task info when tasks are updated
    public static void overwriteDataFile() throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);
        for (Task task : tasks) {
            fw.write(task.serialise() + "\n");
        }
        fw.close();
    }

    public static void setUp() throws IOException {
        initialiseDataFile();
        retrieveFromDataFile();
    }

    // Print logo with greeting message
    public static void greet() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Howdy! :) I'm bean, your personal assistant.\n" +
                INDENT + "Let me help you keep track of your tasks!\n" +
                SEPARATOR_LINE
        );
    }

    // Print logo with exit message
    public static void exit() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Bye bye, glad I could help! See you soon? :'(\n" +
                SEPARATOR_LINE);
    }

    // Return command (taken as first word) from given user input
    public static String extractCommand(String userInput) {
        // Take first word of input as command
        return userInput.split(" ")[0];
    }

    // Print (single line) message with separator line above and below message
    public static void printFormattedReply(String reply) {
        System.out.println(SEPARATOR_LINE +
                reply + "\n" +
                SEPARATOR_LINE);
    }

    public static void printToDoList() throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println(SEPARATOR_LINE +
                INDENT + "TO DO LIST:\n");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(SEPARATOR_LINE);
    }

    // Extract task number as int from user input for mark and unmark commands
    public static int obtainTaskNum(String userInput) throws TaskNumOutOfBoundsException {
        // Obtain task number by taking second word of input and trim any spaces, then parse as int
        String[] words = userInput.split(" ");
        int taskNum = Integer.parseInt(words[1].trim());
        if (taskNum < 0 || taskNum > tasks.size()) {
            throw new TaskNumOutOfBoundsException();
        }
        return taskNum;
    }

    public static void markTaskAsDone(int taskNum) throws IOException {
        int taskIndex = taskNum - 1;
        tasks.get(taskIndex).setStatus(true);
        // Confirmation message
        printFormattedReply(INDENT + "Task " + taskNum + " has been marked as DONE:\n" +
                INDENT + INDENT + tasks.get(taskIndex).toString());
        overwriteDataFile();
    }

    public static void unmarkTaskAsDone(int taskNum) throws IOException {
        int taskIndex = taskNum - 1;
        tasks.get(taskIndex).setStatus(false);
        // Confirmation message
        printFormattedReply(INDENT + "Task " + taskNum + " has been marked as UNDONE:\n" +
                INDENT + INDENT + tasks.get(taskIndex).toString());
        overwriteDataFile();
    }

    public static void addTask(String userInput, TaskType taskType) throws InsufficientSpaceException, IOException {
        if (tasks.size() >= MAX_LIST_COUNT) {
            throw new InsufficientSpaceException();
        }

        if (taskType == TaskType.TODO) {
            // Extract description
            String description = userInput.split("todo ")[1].trim();

            tasks.add(new Todo(description));

        } else if (taskType == TaskType.DEADLINE) {
            // Extract description and by
            String[] parts = userInput.split("/by ");
            // parts: [0] = "deadline {description} ", [1] = " {by}"
            String description = parts[0].substring("deadline ".length()).trim();
            String by = parts[1].trim();

            tasks.add(new Deadline(description, by));

        } else {
            // taskType == TaskType.EVENT
            // Extract description, from and to
            String[] splitDescription = userInput.split("/from");
            // splitDescription: [0] = "event {description} ", [1] = "{from} /to {to}"
            String description = splitDescription[0].substring("events".length()).trim();
            String[] splitFromTo = splitDescription[1].split("/to");
            // splitFromTo: [0] = "{from} ", [1] = "{to}"
            String from = splitFromTo[0].trim();
            String to = splitFromTo[1].trim();

            tasks.add(new Event(description, from, to));
        }
        appendNextLineToFile(tasks.get(tasks.size() - 1).serialise());
    }

    public static void printInvalidInputMessage() {
        printFormattedReply(INDENT + "Sorry, I am not equipped to respond to that yet... :(\n" +
                INDENT + "These are the commands I understand:\n" +
                INDENT + "1. To add a new task:\n" +
                INDENT + INDENT + "a. todo [description]\n" +
                INDENT + INDENT + "b. deadline [description] /by [by]\n" +
                INDENT + INDENT + "c. event [description] /from [from] /to [to]\n" +
                INDENT + INDENT + INDENT + "example: event dinner /from 6pm /to 8pm\n" +
                INDENT + "2. To view your to do list: list\n" +
                INDENT + "3. To mark a task as done: mark [task number]\n" +
                INDENT + "4. To mark a task as undone: unmark [task number]");
    }

    private static void appendNextLineToFile(String nextLine) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH, true); // create a FileWriter in append mode
        fw.write(nextLine + "\n");
        fw.close();
    }

    public static void processUserInput() throws InvalidInputException {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (tasks.size() < MAX_LIST_COUNT + 1) {
            userInput = in.nextLine();
            String userCommand = extractCommand(userInput);

            try {
                switch (userCommand) {
                case "bye":
                    // To exit
                    return;

                case "list":
                    printToDoList();
                    break;

                case "mark":
                    markTaskAsDone(obtainTaskNum(userInput));
                    break;

                case "unmark":
                    unmarkTaskAsDone(obtainTaskNum(userInput));
                    break;

                case "todo":
                    addTask(userInput, TaskType.TODO);
                    break;

                case "deadline":
                    addTask(userInput, TaskType.DEADLINE);
                    break;

                case "event":
                    addTask(userInput, TaskType.EVENT);
                    break;

                default:
                    throw new InvalidInputException();

                }
            } catch (InvalidInputException e) {
                printInvalidInputMessage();

            } catch (EmptyListException e) {
                printFormattedReply(INDENT + "Nothing in your to do list yet!");

            } catch (TaskNumOutOfBoundsException e) {
                printFormattedReply(INDENT + "Please enter a valid task number!\n" +
                        INDENT + "You currently have " + tasks.size() + " tasks.");

            } catch (InsufficientSpaceException e) {
                printFormattedReply(INDENT + "Sorry, you have reached the maximum list size of " + MAX_LIST_COUNT);

            } catch (IOException e) {
                printFormattedReply("Something went wrong! " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws InvalidInputException, IOException {
        setUp();
        greet();
        processUserInput();
        exit();
    }
}