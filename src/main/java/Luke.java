import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Luke {

    private static final String LOGO =
            "                              \n" +
            ",--.           ,--.           \n" +
            "|  |   ,--.,--.|  |,-. ,---.  \n" +
            "|  |   |  ||  ||     /| .-. : \n" +
            "|  '--.'  ''  '|  \\  \\\\   --. \n" +
            "`-----' `----' `--'`--'`----' \n" +
            "                              ";

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

    private static String numberOfTasksMessage() {
        return String.format("Now you have %d %s in the list.", size, size > 1 ? "tasks" : "task");
    }

    private static void list() {
        printDivider();
        for (int i = 0; i < size; i++) {
            System.out.printf("%d. ", i + 1);
            System.out.println(tasks[i].toString());
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
            } catch (NumberFormatException e) {
                throw new IncorrectInput("Please input an integer");
            }
            if (idx < 0 || idx >= size) {
                throw new IncorrectInput("Invalid index");
            }
            tasks[idx].setAsDone();
            printReply(String.format("Marked:\n  %s", tasks[idx].toString()));
            break;
        case UNMARK:
            try {
                idx = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                throw new IncorrectInput("Please input an integer");
            }
            if (idx < 0 || idx >= size) {
                throw new IncorrectInput("Invalid index");
            }
            tasks[idx].setAsUndone();
            printReply(String.format("Unmarked:\n  %s", tasks[idx].toString()));
            break;
        case TODO:
            if (args.length == 0) {
                throw new InsufficientArguments("todo command needs at least 1 argument.");
            }
            description = String.join(" ", args);
            tasks[size] = new ToDo(description);
            size++;
            printReply(String.format("Task added: %s\n  %s",
                    tasks[size - 1].toString(), numberOfTasksMessage()));
            break;
        case DEADLINE:
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("/by")) {
                    idx = i;
                }
            }
            if (idx == -1) {
                throw new InsufficientArguments("Deadline needs to be specified");
            }
            description = String.join(" ", Arrays.copyOf(args, idx));
            deadlineStr = String.join(" ", Arrays.copyOfRange(args, idx + 1, args.length));
            tasks[size] = new Deadline(description, deadlineStr);
            size++;
            printReply(String.format("Added deadline: %s\n  %s",
                    tasks[size - 1].toString(), numberOfTasksMessage()));
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
                throw new InsufficientArguments("From when???");
            }
            if (toIdx == -1) {
                throw new InsufficientArguments("To when???");
            }
            description = String.join(" ", Arrays.copyOf(args, fromIdx));
            fromStr = String.join(" ", Arrays.copyOfRange(args, fromIdx + 1, toIdx));
            toStr = String.join(" ", Arrays.copyOfRange(args, toIdx + 1, args.length));
            tasks[size] = new Event(description, fromStr, toStr);
            size++;
            printReply(String.format("Added event: %s\n %s",
                    tasks[size - 1].toString(), numberOfTasksMessage()));
            break;
        default:
            throw new InvalidCommand("Invalid command");
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
            try {
                executeCommand(CommandType.MARK, inputArr);
            } catch (InsufficientArguments e) {
                printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("unmark")) {
            try {
                executeCommand(CommandType.UNMARK, inputArr);
            } catch (InsufficientArguments e) {
                printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("todo")){
            try {
                executeCommand(CommandType.TODO, inputArr);
            } catch (InsufficientArguments e) {
                printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("deadline")){
            try {
                executeCommand(CommandType.DEADLINE, inputArr);
            } catch (InsufficientArguments e) {
                printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("event")){
            try {
                executeCommand(CommandType.EVENT, inputArr);
            } catch (InsufficientArguments e) {
                printReply(e.getMessage());
            }
        } else {
            throw new InvalidCommand("Invalid command");
        }
    }

    private static void printGreeting() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("Hi im Luke!\nWhat can I do for you? :)");
        printDivider();
    }

    public static void main(String[] args) {
        printGreeting();
        Scanner in = new Scanner(System.in);
        String line;

        while (true) {
            line = in.nextLine();
            try {
                sendMessage(line);
            } catch (InvalidCommand e) {
                printReply("Sorry, I don't understand you :(");
            }
        }
    }
}
