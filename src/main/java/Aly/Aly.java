package Aly;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aly {

    //Constants
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

    //User can pick which function they want to use, old functions don't need to be deleted
    private static void initialise() {
        boolean isExit = false;
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
                    + "2. 'todo/deadline/event' to add that respective type of task\n"
                    + "3. 'mark/unmark' with a number to toggle respective task status\n"
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
                    break;
                case "deadline":
                    index = addDeadline(taskDetails, index);
                    break;
                case "event":
                    index = addEvent(taskDetails, index);
                    break;
                case "mark":
                case "unmark":
                    handleMarking(firstWord, splitInput, index);
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
        String[] taskParts = task.split("by");

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
        String[] taskParts = task.split("from|to");

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
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    //Handles any user inputs about toggling task status
    private static void handleMarking(String firstWord, String[] splitInput, int index) throws IllegalFormatException, InputMismatchException {
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
        } else {
            markAsDone(firstWord, indexNum);
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

    //Start of Aly chatbot
    public static void main(String[] args) {
        printLine();
        System.out.print("Hello! My name is \n" + LOGO);
        initialise();
    }
}