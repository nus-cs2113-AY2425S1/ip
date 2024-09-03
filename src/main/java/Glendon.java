import java.util.Scanner;

public class Glendon {
    public static final int maxNumberOfTask = 100;
    public static Task[] list = new Task[maxNumberOfTask];
    public static int taskCounter = 0;

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String response = in.nextLine();

        while (response != null) {
            switch (response.split(" ")[0]) {
            case "bye":
                printBye();
                response = null;
                break;
            case "list":
                printList();
                response = in.nextLine();
                break;
            case "mark":
                markDone(response);
                response = in.nextLine();
                break;
            case "unmark":
                unmarkDone(response);
                response = in.nextLine();
                break;
            case "todo":
                addTodo(response);
                response = in.nextLine();
                break;
            case "deadline":
                addDeadline(response);
                response = in.nextLine();
                break;
            case "event":
                addEvent(response);
                response = in.nextLine();
                break;
            default:
                response = in.nextLine();
                break;
            }
        }
    }

    private static void addEvent(String response) {
        String[] answers = response.split("/");
        list[taskCounter] = new Event(answers[0].substring(6), answers[1].substring(5), answers[2].substring(3));
        printAddedTask();
        taskCounter++;
    }

    private static void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(list[taskCounter]);
        System.out.println("Now you have " + (taskCounter+1) + " tasks in the list.");
    }

    private static void addDeadline(String response) {
        String[] answers = response.split("/");
        list[taskCounter] = new Deadline(answers[0].substring(9), answers[1].substring(3));
        printAddedTask();
        taskCounter++;
    }

    private static void addTodo(String response) {
        list[taskCounter] = new Todo(response.substring(5));
        printAddedTask();
        taskCounter++;
    }

    private static void unmarkDone(String response) {
        int taskValue = Integer.parseInt(response.split(" ")[1]) - 1;
        list[taskValue].setCompleted(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list[taskValue]);
    }

    private static void markDone(String response) {
        int taskValue = Integer.parseInt(response.split(" ")[1]) - 1;
        list[taskValue].setCompleted(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list[taskValue]);
    }

    private static void printList() {
        int taskNumber = 1;
        Task currentTask;
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.length; i++) {
            currentTask = list[i];
            if (currentTask != null) {
                System.out.println(taskNumber + ". " + currentTask.toString());
                taskNumber++;
            }
        }
    }
    private static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printWelcomeMessage() {
        String logo = " _______   _                     _\n"
                + "| ______| | |                   | |\n"
                + "| |  ___  | |  ___   _____   ___| |  _____   _____\n"
                + "| | |__ | | | / _ \\ |  _  | |  _  | |  _  | |  _  |\n"
                + "| |___| | | | | __/ | | | | | |_| | | |_| | | | | |\n"
                + "|_______| |_| \\___| |_| |_| |_____| |_____| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Glendon.\n" + "What can I do for you?\n");
    }
}