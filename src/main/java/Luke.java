import java.util.Arrays;
import java.util.Scanner;

public class Luke {

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    public static final int MAX_TASK_COUNT = 100;
    private static Task[] tasks = new Task[MAX_TASK_COUNT];
    private static int size = 0;

    private static void printReply(String reply) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(reply);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printDivider() {
        System.out.println(HORIZONTAL_LINE);
    }

    private static void list() {
        printDivider();
        for (int i = 0; i < size; i++) {
            String status = (tasks[i].isDone() ? "X" : " ");
            Class<? extends Task> type = tasks[i].getClass();
            char t = 'U';
            if (type.equals(Deadline.class)) {
                t = 'D';
                System.out.printf("%d.[%c][%s] %s (by: %s)\n",
                        i+1, t, status, tasks[i].getDescription(), tasks[i].getBy());
            } else if (type.equals(ToDo.class)) {
                t = 'T';
                System.out.printf("%d.[%c][%s] %s\n", i+1, t, status, tasks[i].getDescription());
            } else if (type.equals(Event.class)) {
                t = 'E';
                System.out.printf("%d.[%c][%s] %s (from: %s to: %s)\n",
                        i+1, t, status, tasks[i].getDescription(), tasks[i].getFrom(), tasks[i].getTo());
            }
        }
        printDivider();
    }

    private static void executeCommand(CommandType commandType, String[] inputArr) {
        int idx = -1;
        int fromIdx = -1;
        int toIdx = -1;
        String description;
        String deadlineStr;
        String fromStr;
        String toStr;
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        Class<? extends Task> type;
        char t;
        switch (commandType) {
        case BYE:
            printReply("Bye. Hope to see you again soon!");
            System.exit(0);
        case LIST:
            list();
            break;
        case MARK:
            try {
                idx = Integer.parseInt(args[0]) - 1;
            } catch(NumberFormatException e) {
                printReply("Please input a number");
                break;
            }
            if (idx < 0 || idx >= size) {
                printReply("Invalid index");
                break;
            }
            tasks[idx].setAsDone();
            type = tasks[idx].getClass();
            t = 'U';
            if (type.equals(Deadline.class)) {
                t = 'D';
            } else if (type.equals(ToDo.class)) {
                t = 'T';
            } else if (type.equals(Event.class)) {
                t = 'E';
            }
            printReply(String.format("Marked:\n  [%c][X] %s", t, tasks[idx].getDescription()));
            break;
        case UNMARK:
            try {
                idx = Integer.parseInt(args[0]) - 1;
            } catch(NumberFormatException e) {
                printReply("Please input a number");
                break;
            }
            if (idx < 0 || idx >= size) {
                printReply("Invalid index");
                break;
            }
            tasks[idx].setAsUndone();
            type = tasks[idx].getClass();
            t = 'U';
            if (type.equals(Deadline.class)) {
                t = 'D';
            } else if (type.equals(ToDo.class)) {
                t = 'T';
            } else if (type.equals(Event.class)) {
                t = 'E';
            }
            printReply(String.format("Unmarked:\n  [%c][ ] %s", t, tasks[idx].getDescription()));
            break;
        case TODO:
            description = String.join(" ", args);
            tasks[size] = new ToDo(description);
            size++;
            printReply(String.format("Task added:\n  [T][ ] %s\n Now u hv %d %s in the list.",
                        description, size, size > 1 ? "tasks" : "task"));
            break;
        case DEADLINE:
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("/by")) {
                    idx = i;
                }
            }
            if (idx == -1) {
                printReply("Please input deadline");
                break;
            }
            description = String.join(" ", Arrays.copyOf(args, idx));
            deadlineStr = String.join(" ", Arrays.copyOfRange(args, idx + 1, args.length));
            tasks[size] = new Deadline(description, deadlineStr);
            size++;
            printReply(String.format("Added task:\n  [D][ ] %s (by: %s)\nNow u hv %d %s in the list.",
                        description, deadlineStr, size, size > 1 ? "tasks" : "task"));
            break;

        case EVENT:
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("/from")) {
                    fromIdx = i;
                } else if (args[i].startsWith("/to")) {
                    toIdx = i;
                }
            }
            if (fromIdx == -1) {
                printReply("From when???");
                break;
            }
            if (toIdx == -1) {
                printReply("To when???");
                break;
            }
            description = String.join(" ", Arrays.copyOf(args, fromIdx));
            fromStr = String.join(" ", Arrays.copyOfRange(args, fromIdx + 1, toIdx));
            toStr = String.join(" ", Arrays.copyOfRange(args, toIdx + 1, args.length));
            tasks[size] = new Event(description, fromStr, toStr);
            size++;
            printReply(String.format("Added task:\n  [E][ ] %s (from: %s to: %s)\nNow u hv %d %s in the list.",
                    description, fromStr, toStr, size, size > 1 ? "tasks" : "task"));
            break;
        }
    }

    private static void sendMessage(String userInput) {
        String[] inputArr = userInput.split(" ");
        String command = inputArr[0];

        if (command.equalsIgnoreCase("bye")) {
            executeCommand(CommandType.BYE, inputArr);
        } else if (command.equalsIgnoreCase("list")) {
            executeCommand(CommandType.LIST, inputArr);
        } else if (command.equalsIgnoreCase("mark")) {
            executeCommand(CommandType.MARK, inputArr);
        } else if (command.equalsIgnoreCase("unmark")) {
            executeCommand(CommandType.UNMARK, inputArr);
        } else if (command.equalsIgnoreCase("todo")){
            executeCommand(CommandType.TODO, inputArr);
        } else if (command.equalsIgnoreCase("deadline")){
            executeCommand(CommandType.DEADLINE, inputArr);
        } else if (command.equalsIgnoreCase("event")){
            executeCommand(CommandType.EVENT, inputArr);
        }
    }

    public static void main(String[] args) {

        printReply("Hello! I'm Luke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String line;

        while (true) {
            line = in.nextLine();
            sendMessage(line);
        }
    }
}
