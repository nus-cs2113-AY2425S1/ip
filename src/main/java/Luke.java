import java.util.Scanner;

public class Luke {

    private static String horizontalLine = "____________________________________________________________";

    private static Task[] tasks = new Task[100];
    private static int size = 0;

    private static void printReply(String reply) {
        System.out.println(horizontalLine);
        System.out.println(reply);
        System.out.println(horizontalLine);
    }

    private static void list() {
        System.out.println(horizontalLine);
        for (int i = 0; i < size; i++) {
            String status = (tasks[i].isDone() ? "X" : " ");
            System.out.print(String.format("%d.[%s] %s\n", i+1, status, tasks[i].getDescription()));
        }
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {

        printReply("Hello! I'm Luke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String line;
        boolean exit = false;

        while (!exit) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("Bye")) {
                printReply("Bye. Hope to see you again soon!");
                exit = true;
                continue;
            }
            if (line.equalsIgnoreCase("list")) {
                list();
                continue;
            }
            String[] lineArr = line.split(" ");
            if (lineArr[0].equalsIgnoreCase("mark")) {
                int idx;
                try {
                    idx = Integer.parseInt(lineArr[1]) - 1;
                } catch(NumberFormatException e) {
                    printReply("Please input a number");
                    continue;
                }
                if (idx < 0 || idx >= size) {
                    printReply("Invalid index");
                    continue;
                }
                tasks[idx].setAsDone();
                printReply(String.format("Marked as done:\n  [X] %s", tasks[idx].getDescription()));
            } else if (lineArr[0].equalsIgnoreCase("unmark")) {
                int idx;
                try {
                    idx = Integer.parseInt(lineArr[1]) - 1;
                } catch(NumberFormatException e) {
                    printReply("Please input a number");
                    continue;
                }
                if (idx < 0 || idx >= size) {
                    printReply("Invalid index");
                    continue;
                }
                tasks[idx].setAsUndone();
                printReply(String.format("Unmarked:\n  [ ] %s", tasks[idx].getDescription()));
            } else {
                printReply(String.format("Added task: %s", line));
                tasks[size] = new Task(line);
                size++;
            }
        }
    }
}
