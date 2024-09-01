import java.util.Scanner;
import java.util.regex.*;

public class Doot {
    private static final String DIVIDER = "____________________________________________________________\n\n";
    private static Task[] taskList = new Task[100];
    private static int taskIdx = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(DIVIDER + "Hello! I'm  Doot\nWhat can I do for you?\n" + DIVIDER);
        String currentInput = scanner.nextLine();
        while (!currentInput.equals("bye")){
            findCommand(currentInput);
            currentInput = scanner.nextLine();
        }
        System.out.print(DIVIDER + "Bye. Hope to see you again soon!" + "\n" + DIVIDER);
        scanner.close();
    }

    public static void findCommand(String command) {
        String wordDigit = "\\w+ \\d{1,3}$";
        String wordSlashOnce = "\\w+ / \\w+";
        String wordSlashTwice = "\\w+ / \\w+ / \\w+";

        if (command.matches(wordDigit)) {
            handleWordDigit(command);
        } else if (command.matches(wordSlashTwice)) {
            handleWordSlashTwice(command);
        } else if (command.matches(wordSlashOnce)) {
            handleWordSlashOnce(command);
        } else {
            handleDefault(command);
        }
    }

    private static void handleWordDigit(String command) {
        int digit = Integer.parseInt(command.substring(command.indexOf(" ") + 1));
        command = command.substring(0, command.indexOf(" "));
        switch (command) {
            case "list":
                printList();
                break;
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

    private static void handleWordSlashOnce(String command) {
        String[] parts = command.split(" / ");
        String wordOne = parts[0];
        String wordTwo = parts[1];
        switch (wordOne) {
            case "todo":
                makeToDo(wordOne);
                break;
            case "deadline":
                makeDeadline(wordOne, wordTwo);
                break;
            default:
                addToList(command);
                break;
        }
    }

    private static void handleWordSlashTwice(String command) {
        String[] parts = command.split(" / ");
        String wordOne = parts[0];
        String wordTwo = parts[1];
        String wordThree = parts[2];
        switch (wordOne) {
            case "event":
                makeEvent(wordOne, wordTwo, wordThree);
                break;
            default:
                addToList(command);
                break;
        }
    }

    private static void handleDefault(String command) {
        addToList(command);
    }

    public static void makeDeadline(String description, String by){
        taskList[taskIdx] = new Deadline(description, by);
        taskIdx++;
        System.out.print(DIVIDER + "added: " + taskList[taskIdx-1].getDescription() + "\n" + DIVIDER);
    }

    public static void makeEvent(String description, String to, String from){
        taskList[taskIdx] = new Event(description, to, from);
        taskIdx++;
        System.out.print(DIVIDER + "added: " + taskList[taskIdx-1].getDescription() + "\n" + DIVIDER);

    }

    public static void makeToDo(String description){
        taskList[taskIdx] = new ToDo(description);
        taskIdx++;
        System.out.print(DIVIDER + "added: " + description + "\n" + DIVIDER);
    }

    public static void markTask(int idx){
        taskList[idx-1].markDone();
        System.out.println(DIVIDER + "Nice! I've marked this task as done: " + taskList[idx-1].getDescription() + "\n" + DIVIDER);
    }

    public static void unmarkTask(int idx){
        taskList[idx-1].markUnDone();
        System.out.println(DIVIDER + "OK, I've marked this task as not done yet: " + taskList[idx-1].getDescription() + "\n" + DIVIDER);
    }

    public static void addToList(String toAdd){
        taskList[taskIdx] = new Task(toAdd);
        taskIdx++;
        System.out.print(DIVIDER + "added: " + toAdd + "\n" + DIVIDER);
    }

    public static void printList(){
        System.out.print(DIVIDER);
        int curIdx = 1;
        while (curIdx != taskIdx + 1){
            Task curTask = taskList[curIdx-1];
            System.out.println(curIdx + ". " + curTask.toString());
            curIdx++;
        }
        System.out.print(DIVIDER);

    }
}
