import java.util.Scanner;

public class Aly {

    private static void printLine() {
        System.out.println("-----------------------------------------------------------------------------");
    }

    private static void initialise() {
        Task[] tasks = new Task[100];
        boolean isExit = false;
        while (!isExit) {
            printLine();
            System.out.println("I'm hungry, what do you want?");
            System.out.println("Faster lah! Enter 1 for echo function, 2 for list function, 3 for task marking function, 0 to exit");
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
        initialise();
    }

    private static Task[] list(Task[] listItems) {
        boolean isExit = false;
        int index = 0;
        for (int i = 0; i < 101; i++) {
            if (listItems[i] == null) {
                index = i;
                break;
            }
        }
        while (!isExit) {
            System.out.println("Enter 'list' to see your list of tasks, 'add' to add another task");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            switch (input) {
            case "list":
                printLine();
                int count = 1;
                for (Task listItem : listItems) {
                    if (listItem == null) {
                        break;
                    }
                    System.out.println(count + ". " + listItem.description);
                    count += 1;
                }
                printLine();
                break;
            case "add":
                printLine();
                System.out.println("Enter your task:");
                Scanner addItem = new Scanner(System.in);
                String task = addItem.nextLine();
                listItems[index] = (new Task(task));
                index += 1;
                printLine();
                System.out.println("added task: " + task);
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

    private static Task[] markTask(Task[] taskList) {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Enter 'list' to see task list, 'mark' or 'unmark' with a number to toggle task status");
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
                printLine();
            }
            else if (splitInput.length == 2 && input.startsWith("mark")) {
                printLine();
                int indexNum = Integer.parseInt(splitInput[1]);
                if (indexNum > taskList.length || indexNum < 0) {
                    System.out.println("Task number out of bounds!");
                    printLine();
                }
                else {
                    taskList[indexNum-1].setStatus(true);
                    System.out.println("\"" + taskList[indexNum-1].description + "\" marked as done!");
                    printLine();
                }
            }
            else if (splitInput.length == 2 && input.startsWith("unmark")) {
                printLine();
                int indexNum = Integer.parseInt(splitInput[1]);
                if (indexNum > taskList.length || indexNum < 0) {
                    System.out.println("Task number out of bounds!");
                    printLine();
                }
                else {
                    taskList[indexNum-1].setStatus(false);
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
        String logo = "    _      _     _   _\n"
                + "   / \\    | |   \\ \\ / /\n"
                + "  / _ \\   | |    \\ V / \n"
                + " / ___ \\  | |__   | |  \n"
                + "/_/   \\_\\ |____|  |_|  \n";
        System.out.print("Hello! My name is \n" + logo);
        initialise();
    }
}