import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Doot {
    private static final String DIVIDER = "____________________________________________________________\n\n";
    private static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static int taskIdx = 0;
    private static final String FILE_NAME = "dootData.txt";

    public static void main(String[] args) {
        loadTaskData();

        Scanner scanner = new Scanner(System.in);
        System.out.print(DIVIDER + "Hello! I'm  Doot\nWhat can I do for you?\n" + DIVIDER);
        String currentInput = scanner.nextLine();
        while (!currentInput.equals("bye")) {
            findCommand(currentInput);
            currentInput = scanner.nextLine();
        }
        System.out.print(DIVIDER + "Bye. Hope to see you again soon!" + "\n" + DIVIDER);
        scanner.close();
    }

    public static void loadTaskData() {
        try {
            FileInputStream fileReader = new FileInputStream(FILE_NAME);
            ObjectInputStream objectReader = new ObjectInputStream(fileReader);
            boolean fileHasData = true;
            while (fileHasData) {
                try {
                    Object taskToAdd = objectReader.readObject();
                    taskList[taskIdx] = (Task) taskToAdd;
                    taskIdx++;
                } catch (EOFException e) {
                    fileHasData = false;
                }
            }
            objectReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Read file does not exist, will be created!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    public static void writeTaskData() {
        try {
            FileOutputStream fileWriter = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectWriter = new ObjectOutputStream(fileWriter);
            for (Task task : taskList) {
                if (task != null) {
                    objectWriter.writeObject(task);
                }
            }
            objectWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void findCommand(String command) {
        String wordDigit = "\\d{1,3}$";
        String deadlineMatch = "(\\w+ )*/by .+";
        String eventMatch = "(\\w+ )*/from (\\w+ )*/to .+";
        boolean containsSpace = command.contains(" ");
        String cmd = containsSpace ? command.substring(0, command.indexOf(" ")) : command;
        String args = containsSpace ? command.substring(command.indexOf(" ") + 1) : "";
        if (args.matches(wordDigit)) {
            handleWordDigit(cmd, args);
        } else if (args.matches(eventMatch)) {
            handleEvent(cmd, args);
        } else if (args.matches(deadlineMatch)) {
            handleDeadline(cmd, args);
        } else {
            handleDefault(cmd, args);
        }
        writeTaskData();
    }

    private static void handleWordDigit(String command, String args) {
        int digit = Integer.parseInt(args);
        switch (command) {
            case "mark":
                markTask(digit);
                break;
            case "unmark":
                unmarkTask(digit);
                break;
            default:
                addToList(command);
                break;
        }
    }

    private static void handleDeadline(String command, String args) {
        String[] parts = args.split(" /by ");
        String wordOne = parts[0];
        String wordTwo = parts[1];
        switch (command) {
            case "deadline":
                makeDeadline(wordOne, wordTwo);
                break;
            default:
                addToList(command + args);
                break;
        }
    }

    private static void handleEvent(String command, String args) {
        String[] parts = args.split(" /from ");
        String wordOne = parts[0];
        String wordTwo = parts[1];
        parts = wordTwo.split(" /to ");
        wordTwo = parts[0];
        String wordThree = parts[1];
        switch (command) {
            case "event":
                makeEvent(wordOne, wordTwo, wordThree);
                break;
            default:
                addToList(command + args);
                break;
        }
    }

    private static void handleDefault(String command, String args) {
        switch (command) {
            case "todo":
                try {
                    makeToDo(args);
                } catch (DootException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "list":
                printList();
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    public static void makeDeadline(String description, String by) {
        taskList[taskIdx] = new Deadline(description, by);
        taskIdx++;
        System.out.print(DIVIDER + "Got it. I've added this task:\n" + taskList[taskIdx - 1].toString() + "\n"
                + "Now you have " + taskIdx + " tasks in the list.\n" + DIVIDER);
    }

    public static void makeEvent(String description, String to, String from) {
        taskList[taskIdx] = new Event(description, to, from);
        taskIdx++;
        System.out.print(DIVIDER + "Got it. I've added this task:\n" + taskList[taskIdx - 1].toString() + "\n"
                + "Now you have " + taskIdx + " tasks in the list.\n" + DIVIDER);
    }

    public static void makeToDo(String description) throws DootException {
        if (description.equals("")) {
            throw new DootException("The description of a todo cannot be empty.");
        }
        taskList[taskIdx] = new ToDo(description);
        taskIdx++;
        System.out.print(DIVIDER + "Got it. I've added this task:\n" + taskList[taskIdx - 1].toString() + "\n"
                + "Now you have " + taskIdx + " tasks in the list.\n" + DIVIDER);
    }

    public static void markTask(int idx) {
        taskList[idx - 1].markDone();
        System.out.println(DIVIDER + "Nice! I've marked this task as done: " + taskList[idx - 1].getDescription() + "\n"
                + DIVIDER);
    }

    public static void unmarkTask(int idx) {
        taskList[idx - 1].markUnDone();
        System.out.println(DIVIDER + "OK, I've marked this task as not done yet: " + taskList[idx - 1].getDescription()
                + "\n" + DIVIDER);
    }

    public static void addToList(String toAdd) {
        taskList[taskIdx] = new Task(toAdd);
        taskIdx++;
        System.out.print(DIVIDER + "added: " + toAdd + "\n" + DIVIDER);
    }

    public static void printList() {
        System.out.print(DIVIDER);
        int curIdx = 1;
        System.out.println("Here are the tasks in your list:");
        while (curIdx != taskIdx + 1) {
            Task curTask = taskList[curIdx - 1];
            System.out.println(curIdx + ". " + curTask.toString());
            curIdx++;
        }
        System.out.print(DIVIDER);

    }
}
