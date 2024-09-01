import java.util.Scanner;

public class Luke {

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static Task[] tasks = new Task[100];
    private static int size = 0;

    private static void printReply(String reply) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(reply);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void list() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < size; i++) {
            String status = (tasks[i].isDone() ? "X" : " ");
            System.out.print(String.format("%d.[%s] %s\n", i+1, status, tasks[i].getDescription()));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void sendMessage(String userInput) {
        if (userInput.equalsIgnoreCase("Bye")) {
            printReply("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        if (userInput.equalsIgnoreCase("list")) {
            list();
            return;
        }
        String[] inputArr = userInput.split(" ");
        if (inputArr[0].equalsIgnoreCase("mark")) {
            int idx = -1;
            try {
                idx = Integer.parseInt(inputArr[1]) - 1;
            } catch(NumberFormatException e) {
                printReply("Please input a number");
            }
            if (idx < 0 || idx >= size) {
                printReply("Invalid index");
            }
            tasks[idx].setAsDone();
            printReply(String.format("Marked as done:\n  [X] %s", tasks[idx].getDescription()));
        } else if (inputArr[0].equalsIgnoreCase("unmark")) {
            int idx = -1;
            try {
                idx = Integer.parseInt(inputArr[1]) - 1;
            } catch(NumberFormatException e) {
                printReply("Please input a number");
            }
            if (idx < 0 || idx >= size) {
                printReply("Invalid index");
            }
            tasks[idx].setAsUndone();
            printReply(String.format("Unmarked:\n  [ ] %s", tasks[idx].getDescription()));
        } else {
            printReply(String.format("Added task: %s", userInput));
            tasks[size] = new Task(userInput);
            size++;
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
