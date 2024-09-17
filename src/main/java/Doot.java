import java.util.Scanner;
import java.util.ArrayList;

public class Doot {
    private static final String DIVIDER = "____________________________________________________________\n\n";
    private static final int DEFAULT_TASKS = 100;
    private static ArrayList<Task> taskList = new ArrayList<>(DEFAULT_TASKS);

    public static void main(String[] args) {
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
        taskList.add(new Deadline(description, by));
        System.out
                .print(DIVIDER + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
    }

    public static void makeEvent(String description, String to, String from) {
        taskList.add(new Event(description, to, from));
        System.out
                .print(DIVIDER + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
    }

    public static void makeToDo(String description) throws DootException {
        if (description.equals("")) {
            throw new DootException("The description of a todo cannot be empty.");
        }
        taskList.add(new ToDo(description));
        System.out
                .print(DIVIDER + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
    }

    public static void markTask(int idx) {
        taskList.get(taskList.size() - 1).markDone();
        System.out.println(DIVIDER + "Nice! I've marked this task as done: "
                + taskList.get(taskList.size() - 1).getDescription() + "\n"
                + DIVIDER);
    }

    public static void unmarkTask(int idx) {
        taskList.get(-1).markUnDone();
        System.out.println(DIVIDER + "OK, I've marked this task as not done yet: "
                + taskList.get(taskList.size() - 1).getDescription()
                + "\n" + DIVIDER);
    }

    public static void addToList(String toAdd) {
        taskList.add(new Task(toAdd));
        System.out.print(DIVIDER + "added: " + toAdd + "\n" + DIVIDER);
    }

    public static void printList() {
        System.out.print(DIVIDER);
        int curIdx = 0;
        System.out.println("Here are the tasks in your list:");
        while (curIdx != taskList.size()) {
            Task curTask = taskList.get(curIdx);
            int oneIndexedIdx = curIdx + 1;
            System.out.println(oneIndexedIdx + ". " + curTask.toString());
            curIdx++;
        }
        System.out.print(DIVIDER);

    }

    public static void deleteTask(int idx) {
        Task toDelete = taskList.get(idx);
        taskList.remove(idx);
        System.out.println(DIVIDER + "Noted. I've removed this task: \n" + toDelete.toString() + "\n Now you have "
                + taskList.size() + "tasks in this list." + DIVIDER);
    }

}
