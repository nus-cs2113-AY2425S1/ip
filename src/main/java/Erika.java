import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Erika {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String line;
    public static Scanner in = new Scanner(System.in);
    public static int markIndex;
    
    public static void main(String[] args) {
        printWelcomeMessage();
        while (true) {
            collectUserInput();
            if (line.equals("bye")) {
                printGoodbyeMessage();
                break;
            } else if (line.equals("list")) {
                printList();
            } else if (line.contains("mark ")) {
                handleMark();
            } else if (line.contains("todo ")) {
                addTodo();
            } else if (line.contains("deadline ")) {
                addDeadline();
            } else if (line.contains("event ")) {
                addEvent();
            } else {
                printUnknownCommand();
            }
        }
    }

    private static void addEvent() {
        int indexOfFrom = line.indexOf("/from ");
        int indexOfTo = line.indexOf("/to ");
        if (indexOfFrom != -1 && indexOfTo != -1) {
            Event newEvent = new Event(line.substring(line.indexOf(" ")+1, indexOfFrom-1),
                    line.substring(indexOfFrom+6, indexOfTo-1), line.substring(indexOfTo+4));
            tasks.add(newEvent);
            printAddedMessage(newEvent);
        } else {
            printFormatError();
        }
    }

    private static void addDeadline() {
        int indexOfBy = line.indexOf("/by ");
        if (indexOfBy != -1) {
            Deadline newDeadline = new Deadline(line.substring(line.indexOf(" ")+1, indexOfBy-1),
                    line.substring(indexOfBy + 4));
            tasks.add(newDeadline);
            printAddedMessage(newDeadline);
        } else {
            printFormatError();
        }
    }

    private static void addTodo() {
        Todo newTodo = new Todo(line.substring(line.indexOf(" ")+1));
        tasks.add(newTodo);
        printAddedMessage(newTodo);
    }

    private static void handleMark() {
        markIndex = -1;
        String digitString = line.replaceAll("[^0-9]", "");
        parseDigits(digitString);
        if (markIndex > Task.getTaskArraySize()) {
            printOutOfBounds();
            return;
        }
        if (line.contains("unmark ")) {
            tasks.get(markIndex-1).setMark(false);
            printUnmarkedMessage();
        } else {
            tasks.get(markIndex-1).setMark(true);
            printMarkedMessage();
        }
    }

    private static void printAddedMessage(Task newTask) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + newTask.printString());
        System.out.printf("\tNow you have %d tasks in the list.%n", Task.getTaskArraySize());
        System.out.println("\t____________________________________________________________");
    }

    private static void printMarkedMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t[X] " + tasks.get(markIndex-1).getDescription());
        System.out.println("\t____________________________________________________________");
    }

    private static void printUnmarkedMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as not done yet:");
        System.out.println("\t[ ] " + tasks.get(markIndex-1).getDescription());
        System.out.println("\t____________________________________________________________");
    }

    private static void printUnknownCommand() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tError! Unknown Command, please try again!");
        System.out.println("\t____________________________________________________________");
    }

    private static void printFormatError() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tError! Invalid Command Format, please try again!");
        System.out.println("\t____________________________________________________________");
    }

    private static void printOutOfBounds() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tError! Task " + markIndex + " is out of bounds!");
        System.out.println("\t____________________________________________________________");
    }

    private static void parseDigits(String digitString) {
        if (!digitString.isEmpty()) {
            markIndex = Integer.parseInt(digitString);
        }
    }

    private static void printList() {
        System.out.println("\t____________________________________________________________");
        if (Task.getTaskArraySize() == 0) {
            System.out.println("\tIt seems that there are no tasks! Please consider adding some!");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < Task.getTaskArraySize(); i++) {
            System.out.println("\t" + Integer.toString(i+1) + "." + tasks.get(i).printString());
        }
        System.out.println("\t____________________________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye! Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    private static void collectUserInput() {
        line = in.nextLine();
        line = line.trim();
    }

    private static void printWelcomeMessage() {
        String logo =
                " _______   ________  ___  ___  __    ________     \n" +
                "|\\  ___ \\ |\\   __  \\|\\  \\|\\  \\|\\  \\ |\\   __  \\    \n" +
                "\\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\/  /|\\ \\  \\|\\  \\   \n" +
                " \\ \\  \\_|/_\\ \\   _  _\\ \\  \\ \\   ___  \\ \\   __  \\  \n" +
                "  \\ \\  \\_|| \\ \\  \\\\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \n" +
                "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\\n" +
                "    \\|_______|\\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|";
        System.out.println("\tHello from\n" + logo + "\n");
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Erika\n \tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
}
