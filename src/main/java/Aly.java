import java.util.InputMismatchException;
import java.util.Scanner;

public class Aly {

    //Constants
    private static final String RETURNING_TO_MAIN_MENU_MESSAGE = "Returning to main menu!";
    private static final String RETURN_TO_MAIN_MENU_MESSAGE = "Enter 0 to return to main menu anytime!";
    private static final int MAX_TASKS = 100;
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
    private static Task[] tasks = new Task[MAX_TASKS];

    //User can pick which function they want to use, old functions don't need to be deleted
    private static void initialise() {
        boolean isExit = false;
        while (!isExit) {
            printLine();
            System.out.println("I'm hungry, what do you want?");
            System.out.println("Faster lah! Enter 1 for echo function, "
                    + "2 for task creation function, "
                    + "3 for task marking function, 0 to exit");
            printLine();
            try {
                String input = in.nextLine().trim();
                switch (input) {
                case "0":
                    isExit = true;
                    break;
                case "1":
                    System.out.println(RETURN_TO_MAIN_MENU_MESSAGE);
                    printLine();
                    echo();
                    break;
                case "2":
                    System.out.println(RETURN_TO_MAIN_MENU_MESSAGE);
                    printLine();
                    manageList(tasks);
                    break;
                case "3":
                    System.out.println(RETURN_TO_MAIN_MENU_MESSAGE);
                    printLine();
                    markTask(tasks);
                    break;
                default:
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Can read instructions anot? Enter a number from 0 to 3 only lah!");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
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
            System.out.println("Waiting for Input...");
            String line = in.nextLine().trim();
            if (line.equals("0")) {
                isExit = true;
                System.out.println(RETURNING_TO_MAIN_MENU_MESSAGE);
            } else {
                printLine();
                System.out.println(line);
                printLine();
            }
        }
    }

    //User can pick which function to use, new functions can be added on easily
    private static void manageList(Task[] listItems) {
        boolean isExit = false;
        int index = 0;
        index = getIndex(listItems, index);
        while (!isExit) {
            System.out.println("Enter 'list' to see your list of tasks,"
                    + " 'todo/deadline/event' to add that respective type of task, "
                    + "'0' to exit");
            String input = in.nextLine().trim();
            String firstWord = input.split(" ")[0];
            String taskDetails = input.replace(firstWord, "");

            switch (firstWord) {
            case "list":
                listTasks(listItems);
                break;
            case "todo":
                index = addTodo(listItems, taskDetails, index);
                break;
            case "deadline":
                index = addDeadline(listItems, taskDetails, index);
                break;
            case "event":
                index = addEvent(listItems, taskDetails, index);
                break;
            case "0":
                isExit = true;
                System.out.println(RETURNING_TO_MAIN_MENU_MESSAGE);
                break;
            default:
                printLine();
                System.out.println("Can read instructions anot? Try again!");
                printLine();
                break;
            }
        }
    }

    //Reusable to print any list of tasks
    private static void listTasks(Task[] listItems) {
        printLine();
        int count = 1;
        System.out.println("Your task list:");
        for (Task listItem : listItems) {
            if (listItem == null) {
                break;
            }
            System.out.println(count + "." + listItem);
            count++;
        }
        System.out.println("Long siah... Shag bro, better faster do!");
        printLine();
    }

    private static int addTodo(Task[] listItems, String task, int index) {
        printLine();
        listItems[index] = new Todo(task.trim());
        index++;
        System.out.println("Added this task: " + task.trim());
        System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");
        printLine();
        return index;
    }

    private static int addDeadline(Task[] listItems, String task, int index) {
        printLine();
        String[] taskParts = task.split("/by");
        if (taskParts.length != 2) {
            System.out.println("Wrong format!");
            printLine();
            return index;
        }
        String taskDeadline = taskParts[0].trim();
        String taskBy = taskParts[1].trim();
        listItems[index] = new Deadline(taskDeadline, taskBy);
        index++;
        System.out.println("Added this task: " + taskDeadline);
        System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");
        printLine();
        return index;
    }

    private static int addEvent(Task[] listItems, String task, int index) {
        printLine();
        String[] taskParts = task.split("/from|/to");
        if (taskParts.length != 3) {
            System.out.println("Wrong format!");
            printLine();
            return index;
        }
        String taskEvent = taskParts[0].trim();
        String taskFrom = taskParts[1].trim();
        String taskTo = taskParts[2].trim();
        listItems[index] = new Event(taskEvent, taskFrom, taskTo);
        index++;
        System.out.println("Added this task: " + taskEvent);
        System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");
        printLine();
        return index;
    }

    //Task statuses can be toggled easily and future statuses can be added easily as well
    private static void markTask(Task[] taskList) {
        boolean isExit = false;
        int index = 0;
        index = getIndex(taskList, index);
        while (!isExit) {
            System.out.println("Enter 'list' to see task list, "
                    + "'mark'/'unmark' with a number to toggle respective task status,"
                    + " '0' to exit");
            String input = in.nextLine().trim();
            String[] splitInput = input.split(" ");
            String firstWord = splitInput[0];

            switch (firstWord) {
            case "list":
                listTasks(taskList);
                break;
            case "mark":
            case "unmark":
                handleMarking(firstWord, splitInput, index, taskList);
                break;
            case "0":
                isExit = true;
                System.out.println(RETURNING_TO_MAIN_MENU_MESSAGE);
                break;
            default:
                printLine();
                System.out.println("Can read instructions anot? Try again!");
                printLine();
                break;
            }
        }
    }

    //Reusable function to find the current index in any list
    private static int getIndex(Task[] listItems, int index) {
        for (int i = 0; i < 101; i++) {
            if (listItems[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static void handleMarking(String firstWord, String[] splitInput, int index, Task[] taskList){
            if (splitInput.length != 2) {
                System.out.println("No task number provided!");
            } else {
                printLine();
                int indexNum = Integer.parseInt(splitInput[1]);
                if (indexNum > index || indexNum <= 0) {
                    System.out.println("Task number out of bounds!");
                } else {
                    markAsDone(firstWord, taskList, indexNum);
                }
            }
            printLine();
        }

    private static void markAsDone(String firstWord, Task[] taskList, int indexNum) {
        if (firstWord.equals("mark")) {
            taskList[indexNum - 1].setDone(true);
            System.out.println("\"" + taskList[indexNum - 1].description + "\" marked as done!");
        } else {
            taskList[indexNum - 1].setDone(false);
            System.out.println("\"" + taskList[indexNum - 1].description + "\" marked as undone!");
        }
    }

    //Start of Aly chatbot
    public static void main(String[] args) {
        printLine();
        System.out.print("Hello! My name is \n" + LOGO);
        initialise();
    }
}