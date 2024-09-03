import java.util.Scanner;

public class Aly {

    private static final int MAX_TASKS = 100;
    private static final String LINE_SEPARATOR = "-----------------------------------------------------------------------------";
    private static final String LOGO = "    _      _     _   _\n"
            + "   / \\    | |   \\ \\ / /\n"
            + "  / _ \\   | |    \\ V / \n"
            + " / ___ \\  | |__   | |  \n"
            + "/_/   \\_\\ |____|  |_|  \n";
    private static Scanner in = new Scanner(System.in);
    private static Task[] tasks = new Task[MAX_TASKS];

    private static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    private static void initialise() {
        boolean isExit = false;
        while (!isExit) {
            printLine();
            System.out.println("I'm hungry, what do you want?");
            System.out.println("Faster lah! Enter 1 for echo function, 2 for task creation function, 3 for task marking function, 0 to exit");
            printLine();
            String input = in.nextLine().trim();
            switch (input) {
            case "0":
                isExit = true;
                break;
            case "1":
                System.out.println("Enter 0 to return to main menu anytime!");
                printLine();
                echo();
                break;
            case "2":
                System.out.println("Enter 0 to return to main menu anytime!");
                printLine();
                list(tasks);
                break;
            case "3":
                System.out.println("Enter 0 to return to main menu anytime!");
                printLine();
                markTask(tasks);
                break;
            default:
                System.out.println("Can read instructions anot? Try again!");
                break;
            }
        }
        exit();
    }

    private static void exit() {
        System.out.println("Bye! I'm going to eat MacDonalds now!");
        printLine();
        System.exit(0);
    }

    private static void echo() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Waiting for Input...");
            String line = in.nextLine().trim();
            if (line.equals("0")) {
                isExit = true;
                System.out.println("Returning to main menu!");
            } else {
                printLine();
                System.out.println(line);
                printLine();
            }
        }
    }

    private static void list(Task[] listItems) {
        boolean isExit = false;
        int index = 0;
        index = getIndex(listItems, index);
        while (!isExit) {
            System.out.println("Enter 'list' to see your list of tasks, 'todo'/'deadline'/'event' to add that respective type of task, '0' to exit");
            String input = in.nextLine().trim();
            String firstWord = input.split(" ")[0];
            String task = input.replace(firstWord, "");

            switch (firstWord) {
            case "list":
                listTasks(listItems);
                break;
            case "todo":
                index = addTodo(listItems, task, index);
                break;
            case "deadline":
                index = addDeadline(listItems, task, index);
                break;
            case "event":
                index = addEvent(listItems, task, index);
                break;
            case "0":
                isExit = true;
                System.out.println("Returning to main menu!");
                break;
            default:
                printLine();
                System.out.println("Can read instructions anot? Try again!");
                printLine();
                break;
            }
        }
    }

    private static void listTasks(Task[] listItems) {
        printLine();
        int count = 1;
        System.out.println("Your task list:");
        for (Task listItem : listItems) {
            if (listItem == null) {
                break;
            }
            System.out.println(count + "." + listItem);
            count += 1;
        }
        System.out.println("Long siah... Shag bro, better faster do!");
        printLine();
    }

    private static int addTodo(Task[] listItems, String task, int index) {
        printLine();
        listItems[index] = new Todo(task.trim());
        index += 1;
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
        index += 1;
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
        index += 1;
        System.out.println("Added this task: " + taskEvent);
        System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");
        printLine();
        return index;
    }

    private static void markTask(Task[] taskList) {
        boolean isExit = false;
        int index = 0;
        index = getIndex(taskList, index);
        while (!isExit) {
            System.out.println("Enter 'list' to see task list, 'mark'/'unmark' with a number to toggle respective task status, '0' to exit");
            String input = in.nextLine().trim();
            String[] splitInput = input.split(" ");
            String firstWord = splitInput[0];

            switch (firstWord) {
            case "list":
                listTasks(taskList);
                break;
            case "mark":
            case "unmark":
                handleMarking(firstWord, splitInput, input, index, taskList);
                break;
            case "0":
                isExit = true;
                System.out.println("Returning to main menu!");
                break;
            default:
                printLine();
                System.out.println("Can read instructions anot? Try again!");
                printLine();
                break;
            }
        }
    }

    private static int getIndex(Task[] listItems, int index) {
        for (int i = 0; i < 101; i++) {
            if (listItems[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static void handleMarking(String firstWord, String[] splitInput, String input, int index, Task[] taskList){
            if (splitInput.length != 2) {
                System.out.println("No task number provided!");
            }
            else {
                printLine();
                int indexNum = Integer.parseInt(splitInput[1]);
                if (indexNum > index || indexNum <= 0) {
                    System.out.println("Task number out of bounds!");
                } else {
                    if (firstWord.equals("mark")) {
                        taskList[indexNum - 1].setDone(true);
                        System.out.println("\"" + taskList[indexNum - 1].description + "\" marked as done!");
                    } else {
                        taskList[indexNum - 1].setDone(false);
                        System.out.println("\"" + taskList[indexNum - 1].description + "\" marked as undone!");
                    }
                }
            }
            printLine();
        }

    public static void main(String[] args) {
        printLine();
        System.out.print("Hello! My name is \n" + LOGO);
        initialise();
    }
}