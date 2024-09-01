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
            System.out.printf("%d.[%s] %s\n", i+1, status, tasks[i].getDescription());
        }
        printDivider();
    }

    private static void executeCommand(CommandType commandType, String[] inputArr) {
        int idx;
        switch (commandType) {
        case BYE:
            printReply("Bye. Hope to see you again soon!");
            System.exit(0);
        case LIST:
            list();
            break;
        case MARK:
            try {
                idx = Integer.parseInt(inputArr[1]) - 1;
            } catch(NumberFormatException e) {
                printReply("Please input a number");
                break;
            }
            if (idx < 0 || idx >= size) {
                printReply("Invalid index");
                break;
            }
            tasks[idx].setAsDone();
            printReply(String.format("Marked as done:\n  [X] %s", tasks[idx].getDescription()));
            break;
        case UNMARK:
            try {
                idx = Integer.parseInt(inputArr[1]) - 1;
            } catch(NumberFormatException e) {
                printReply("Please input a number");
                break;
            }
            if (idx < 0 || idx >= size) {
                printReply("Invalid index");
                break;
            }
            tasks[idx].setAsUndone();
            printReply(String.format("Unmarked:\n  [ ] %s", tasks[idx].getDescription()));
            break;
        case TASK:
            String userInput = String.join(" ", inputArr);
            printReply(String.format("Added task: %s", userInput));
            tasks[size] = new Task(userInput);
            size++;
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
        } else {
            executeCommand(CommandType.TASK, inputArr);
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
