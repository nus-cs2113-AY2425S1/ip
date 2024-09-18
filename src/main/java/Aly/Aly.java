package Aly;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Aly {

    //Constants
    private static final Path SAVE_FILE = Paths.get("data","taskdata.txt");
    private static final String RETURNING_TO_MAIN_MENU_MESSAGE = "Returning to main menu!";
    private static final String RETURN_TO_MAIN_MENU_MESSAGE = "Enter 'exit' to return to main menu anytime!";
    private static final String LOGO = "    _      _     _   _\n"
            + "   / \\    | |   \\ \\ / /\n"
            + "  / _ \\   | |    \\ V / \n"
            + " / ___ \\  | |__   | |  \n"
            + "/_/   \\_\\ |____|  |_|  \n";

    //Prints line for clarity
    private static final String LINE_SEPARATOR = "=".repeat(112);
    private static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    //Initialising global variables/arrays
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void readTasksFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int index = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] taskDetails = line.split("\\|");

                switch (taskDetails[0]) {
                case "T":
                    taskList.add(new Todo(taskDetails[2].trim()));
                    taskList.get(index).setDone(taskDetails[1].trim().equals("1"));
                    break;
                case "D":
                    taskList.add(new Deadline(taskDetails[2].trim(), taskDetails[3].trim()));
                    taskList.get(index).setDone(taskDetails[1].trim().equals("1"));
                    break;
                case "E":
                    taskList.add(new Event(taskDetails[2].trim(), taskDetails[3].trim(), taskDetails[4].trim()));
                    taskList.get(index).setDone(taskDetails[1].trim().equals("1"));
                    break;
                default:
                    throw new IllegalArgumentException("Idk what task is this siah: " + taskDetails[0]);
                }

                index++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Your file dont have lah... maybe because: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error! Your file sucks because: " + e.getMessage());
        }
    }

    private static void writeTasksToFile(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            for (Task task : taskList) {
                if (task != null) {
                    String toSave = task.toString();
                    char status = '0';
                    if (toSave.charAt(4) == 'X') {
                        status = '1';
                    }
                    char type = toSave.charAt(1);
                    switch (type) {
                    case 'T':
                        int endIndex = 6;
                        toSave = toSave.charAt(1) + "|" + status + "|" + toSave.substring(endIndex);
                        break;
                    case 'D':
                        int byIndex = toSave.indexOf("(by: ");
                        toSave = toSave.charAt(1) + "|" + status + "|" + toSave.substring(6, byIndex-1) + "|" + toSave.substring(byIndex+5,toSave.length()-1);
                        break;
                    case 'E':
                        int fromIndex = toSave.indexOf("(from: ");
                        int toIndex = toSave.indexOf("to: ");
                        toSave = toSave.charAt(1) + "|" + status + "|" + toSave.substring(6,fromIndex-1) + "|" + toSave.substring(fromIndex+7,toIndex) + "|" + toSave.substring(toIndex+4,toSave.length()-1);
                        break;
                    default:
                        throw new IllegalArgumentException("Idk what task is this siah, how to save??");
                    }
                    fileWriter.write(toSave + System.lineSeparator());
                }
            }

            fileWriter.close();

            System.out.println("Tasks recorded successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error! Your file sucks because: " + e.getMessage());
        }
    }

    //User can pick which function they want to use, old functions don't need to be deleted
    private static void initialise() {
        boolean isExit = false;
        printLine();
        createDirectory();
        File file = new File(SAVE_FILE.toString());
        createFile(file);
        readTasksFromFile(file.toString());
        printLine();
        while (!isExit) {
            System.out.println("I'm hungry, what do you want?");
            System.out.println("Faster lah! Enter 'echo' for echo function, "
                    + "'task' for task list function, 'exit' to exit");
            printLine();
            try {
                String input = in.nextLine().trim();
                switch (input) {
                case "exit":
                    isExit = true;
                    break;
                case "echo":
                    System.out.println(RETURN_TO_MAIN_MENU_MESSAGE);
                    printLine();
                    echo();
                    break;
                case "task":
                    System.out.println(RETURN_TO_MAIN_MENU_MESSAGE);
                    printLine();
                    editTasks();
                    break;
                default:
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Can read instructions anot? Do properly leh!");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } finally {
                printLine();
            }
        }
        exit();
    }

    //Exit function
    private static void exit() {
        System.out.println("Bye! Time for MacDonalds!");
        printLine();
        System.exit(0);
    }

    //Echoes user input back to user
    private static void echo() {
        boolean isExit = false;
        while (!isExit) {
            try {
                System.out.println("Waiting for Input...");
                String line = in.nextLine().trim();
                if (line.equals("exit")) {
                    isExit = true;
                    System.out.println(RETURNING_TO_MAIN_MENU_MESSAGE);
                } else {
                    printLine();
                    System.out.println(line);
                    printLine();
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    //User can pick which function to use, new functions can be added on easily
    private static void editTasks() {
        boolean isExit = false;
        int index = getIndex();
        while (!isExit) {
            System.out.println("Possible commands: \n"
                    + "1. 'list' to see your list of tasks\n"
                    + "2. 'todo/deadline/event' to add that type of task\n"
                    + "3. 'mark/unmark/delete' with a number to update that task\n"
                    + "4. 'exit' to exit");
            printLine();
            String input = in.nextLine().trim();
            try {
                String firstWord = input.split(" ")[0];
                String[] splitInput = input.split(" ");
                String taskDetails = input.replace(firstWord, "");

                switch (firstWord) {
                case "list":
                    listTasks();
                    break;
                case "todo":
                    index = addTodo(taskDetails, index);
                    writeTasksToFile(SAVE_FILE.toString());
                    break;
                case "deadline":
                    index = addDeadline(taskDetails, index);
                    writeTasksToFile(SAVE_FILE.toString());
                    break;
                case "event":
                    index = addEvent(taskDetails, index);
                    writeTasksToFile(SAVE_FILE.toString());
                    break;
                case "mark":
                case "unmark":
                case "delete":
                    handleTasks(firstWord, splitInput, index);
                    writeTasksToFile(SAVE_FILE.toString());
                    break;
                case "exit":
                    isExit = true;
                    System.out.println(RETURNING_TO_MAIN_MENU_MESSAGE);
                    break;
                default:
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Can follow instructions properly anot? Read carefully and try again!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You got too much work already bro, task list full liao!");
            } catch (IllegalFormatException e) {
                System.out.println("Can wake up your idea anot, how to do anything without proper input???");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } finally {
                if (!isExit) {
                    printLine();
                }
            }
        }
    }

    private static void createFile(File file) {
        System.out.println("Searching for " + SAVE_FILE + "...");
        try {
            if (file.createNewFile()) {
                System.out.println("Cannot find file so I help you create already! File name: " + SAVE_FILE);
            } else {
                System.out.println("File already exists, I will edit that!");
            }
        } catch (IOException e) {
            System.out.println("Error! File sucks because: " + e.getMessage());
        }
    }

    private static void createDirectory() {
        Path directory = SAVE_FILE.getParent(); // Get the parent directory
        System.out.println("Searching for directory: " + directory);
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Idk why cannot make directory siah: " + e.getMessage());
            }
            System.out.println("Directory didn't exist but I made it for u liao! You're welcome!");
        } else {
            System.out.println("Directory already exists, I will check in there!");
        }
    }

    //Reusable to print any list of tasks
    private static void listTasks() {
        printLine();
        int count = 1;
        System.out.println("Your task list:");
        try {
            for (Task listItem : taskList) {
                if (listItem == null) {
                    break;
                }
                System.out.println(count + "." + listItem);
                count++;
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        if (count == 1) {
            System.out.println("No tasks yet...");
        } else {
            System.out.println("Long siah... Shag bro, better faster do!");
        }
    }

    //Handles to-do inputs
    private static int addTodo(String task, int index) throws IllegalFormatException, ArrayIndexOutOfBoundsException, NullPointerException {
        if (taskList == null) {
            throw new NullPointerException();
        }

        if (task.isEmpty()) {
            throw new IllegalFormatException();
        }

        if (index > taskList.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        printLine();
        taskList.add(index, new Todo(task.trim()));
        index++;
        System.out.println("Added this task: " + task.trim());
        System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");

        return index;
    }

    //Handles deadline inputs
    private static int addDeadline(String task, int index) throws IllegalFormatException, ArrayIndexOutOfBoundsException, NullPointerException {
        String[] taskParts = task.split("\\bby\\b");

        if (taskList == null) {
            throw new NullPointerException();
        }

        if (task.isEmpty() || taskParts.length != 2) {
            throw new IllegalFormatException();
        }

        if (index > taskList.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        String taskDeadline = taskParts[0].trim();
        String taskBy = taskParts[1].trim();
        if (taskDeadline.isEmpty() || taskBy.isEmpty()) {
            throw new IllegalFormatException();
        }

        printLine();
        taskList.add(index, new Deadline(taskDeadline, taskBy));
        index++;
        System.out.println("Added this task: " + taskDeadline);
        System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");

        return index;
    }

    //Handles event inputs
    private static int addEvent(String task, int index) throws IllegalFormatException, ArrayIndexOutOfBoundsException, NullPointerException {
        String[] taskParts = task.split("\\bfrom\\b|\\bto\\b");

        if (task.isEmpty() || taskParts.length != 3) {
            throw new IllegalFormatException();
        }
        if (taskList == null) {
            throw new NullPointerException();
        }
        if (index > taskList.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        String taskEvent = taskParts[0].trim();
        String taskFrom = taskParts[1].trim();
        String taskTo = taskParts[2].trim();
        if (taskEvent.isEmpty() || taskFrom.isEmpty() || taskTo.isEmpty()) {
            throw new IllegalFormatException();
        }

        printLine();
        taskList.add(index, new Event(taskEvent, taskFrom, taskTo));
        index++;
        System.out.println("Added this task: " + taskEvent);
        System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");

        return index;
    }

    //Reusable function to find the current index in any list
    private static int getIndex() {
        int index = 0;
        while (index < taskList.size()) {
            index++;
        }
        return index;
    }

    //Handles any user inputs about toggling task status
    private static void handleTasks(String firstWord, String[] splitInput, int index) throws IllegalFormatException, InputMismatchException {
        int indexNum;
        try {
            indexNum = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalFormatException();
        }

        if (splitInput.length > 2) {
            throw new IllegalFormatException();
        }

        printLine();
        if (indexNum > index || indexNum <= 0) {
            System.out.println("Task number out of bounds!");
        } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
            markAsDone(firstWord, indexNum);
        } else if (firstWord.equals("delete")) {
            delete(indexNum);
        } else {
            throw new IllegalFormatException();
        }
    }

    //Reduce nested loops
    private static void markAsDone(String firstWord, int indexNum) {
        if (firstWord.equals("mark")) {
            taskList.get(indexNum - 1).setDone(true);
            System.out.println("\"" + taskList.get(indexNum - 1).getDescription() + "\" marked as done!");
        } else {
            taskList.get(indexNum - 1).setDone(false);
            System.out.println("\"" + taskList.get(indexNum - 1).getDescription() + "\" marked as undone!");
        }
    }

    //delete tasks
    private static void delete(int indexNum) {
        System.out.println("Deleted this task: " + taskList.get(indexNum - 1).getDescription());
        taskList.remove(indexNum - 1);
    }

    //Start of Aly chatbot
    public static void main(String[] args) {
        printLine();
        System.out.print("Hello! My name is \n" + LOGO);
        initialise();
    }
}