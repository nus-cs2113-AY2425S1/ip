import java.util.Scanner;

public class Aly {

    private static final int MAX_TASKS = 100;
    private static final String LINE_SEPARATOR = "-----------------------------------------------------------------------------";

    private static void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    private static void initialise() {
        Task[] tasks = new Task[MAX_TASKS];
        boolean isExit = false;
        while (!isExit) {
            printLine();
            System.out.println("I'm hungry, what do you want?");
            System.out.println("Faster lah! Enter 1 for echo function, 2 for task creation function, 3 for task marking function, 0 to exit");
            printLine();
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
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
                tasks = list(tasks);
                break;
            case "3":
                System.out.println("Enter 0 to return to main menu anytime!");
                printLine();
                tasks = markTask(tasks);
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
            String line;
            Scanner in = new Scanner(System.in);
            System.out.println("Waiting for Input...");
            line = in.nextLine();
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

    private static Task[] list(Task[] listItems) {
        boolean isExit = false;
        int index = 0;
        index = getIndex(listItems, index);
        while (!isExit) {
            System.out.println("Enter 'list' to see your list of tasks, 'todo'/'deadline'/'event' to add that respective type of task, '0' to exit");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String firstWord = input.split(" ")[0];
            String task = input.replace(firstWord, "");
            String[] taskParts;

            switch (firstWord) {
            case "list":
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
                System.out.println("Shag bro, your list long siah");
                printLine();
                break;
            case "todo":
                printLine();
                listItems[index] = new Todo(task.trim());
                index += 1;
                System.out.println("Added this task: " + task.trim());
                System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");
                printLine();
                break;
            case "deadline":
                printLine();
                taskParts = task.split("/by");
                if (taskParts.length != 2) {
                    System.out.println("Wrong format!");
                    printLine();
                    break;
                }
                String taskDeadline = taskParts[0].trim();
                String taskBy = taskParts[1].trim();
                listItems[index] = new Deadline(taskDeadline, taskBy);
                index += 1;
                System.out.println("Added this task: " + taskDeadline);
                System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");
                printLine();
                break;
            case "event":
                printLine();
                taskParts = task.split("/from|/to");
                if (taskParts.length != 3) {
                    System.out.println("Wrong format!");
                    printLine();
                    break;
                }
                String taskEvent = taskParts[0].trim();
                String taskFrom = taskParts[1].trim();
                String taskTo = taskParts[2].trim();
                listItems[index] = new Event(taskEvent, taskFrom, taskTo);
                index += 1;
                System.out.println("Added this task: " + taskEvent);
                System.out.println("You have " + Task.getTaskCounter() + " tasks in your list now.");
                printLine();
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
        return listItems;
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

    private static Task[] markTask(Task[] taskList) {
        boolean isExit = false;
        int index = 0;
        index = getIndex(taskList, index);
        while (!isExit) {
            System.out.println("Enter 'list' to see task list, 'mark'/'unmark' with a number to toggle respective task status, '0' to exit");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] splitInput = input.split(" ");
            if (input.equals("list")) {
                printLine();
                System.out.println("Here is your task list:");
                int count = 1;
                for (Task task : taskList) {
                    if (task == null) {
                        break;
                    }
                    System.out.println(count + "." + task);
                    count += 1;
                }
                System.out.println("Better faster do!");
                printLine();
            }
            else if (splitInput.length == 2 && input.startsWith("mark")) {
                printLine();
                int indexNum = Integer.parseInt(splitInput[1]);
                if (indexNum > index || indexNum <= 0) {
                    System.out.println("Task number out of bounds!");
                    printLine();
                }
                else {
                    taskList[indexNum-1].setDone(true);
                    System.out.println("\"" + taskList[indexNum-1].description + "\" marked as done!");
                    printLine();
                }
            }
            else if (splitInput.length == 2 && input.startsWith("unmark")) {
                printLine();
                int indexNum = Integer.parseInt(splitInput[1]);
                if (indexNum > index || indexNum <= 0) {
                    System.out.println("Task number out of bounds!");
                    printLine();
                }
                else {
                    taskList[indexNum-1].setDone(false);
                    System.out.println("\"" + taskList[indexNum-1].description + "\" marked as undone!");
                    printLine();
                }
            }
            else if (input.equals("0")) {
                isExit = true;
                System.out.println("Returning to main menu!");
            }
            else {
                printLine();
                System.out.println("Can read instructions anot? Try again!");
                printLine();
            }
        }
        return taskList;
    }

    public static void main(String[] args) {
        printLine();
        String LOGO = "    _      _     _   _\n"
                + "   / \\    | |   \\ \\ / /\n"
                + "  / _ \\   | |    \\ V / \n"
                + " / ___ \\  | |__   | |  \n"
                + "/_/   \\_\\ |____|  |_|  \n";
        System.out.print("Hello! My name is \n" + LOGO);
        initialise();
    }
}