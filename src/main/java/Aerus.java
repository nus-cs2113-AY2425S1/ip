import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Aerus {

    public static final String DIVIDER_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task tasks[] = new Task[100];

        printContent("Hello! I'm Aerus!\nWhat can I do for you?");

        // while block handling Aerus' main functionality
        while (true) {
            userInput = scanner.nextLine();

            // Case: Terminate program
            if (userInput.equals("bye")) {
                break;
            }

            // Case: See list
            if (userInput.equals("list")) {
                if (Task.tasksCount == 0) {
                    printContent("You don't have any tasks!");
                    continue;
                }

                System.out.println(DIVIDER_LINE);
                for (int i = 1; i <= Task.tasksCount; i++) {
                    System.out.println(i + ". " + tasks[i-1].toString());
                }
                System.out.println(DIVIDER_LINE);
                continue;
            }

            // Case: Mark & Unmark
            String[] userInputSplit = userInput.split(" ");

            // Test if the input is formatted like a mark/unmark command
            if (userInputSplit.length == 2 && userInputSplit[1].matches("\\d+(\\.\\d+)?") &&
                    parseInt(userInputSplit[1]) <= Task.tasksCount) {
                int taskNumber = parseInt(userInputSplit[1]) - 1;
                if (userInputSplit[0].equals("mark")) {
                    tasks[taskNumber].isDone = true;
                    printContent("Nice! You have done this task:\n\t" + tasks[taskNumber].toString());
                    continue;
                }
                if (userInputSplit[0].equals("unmark")) {
                    tasks[taskNumber].isDone = false;
                    printContent("I have unmarked this task:\n\t" + tasks[taskNumber].toString());
                    continue;
                }
            }

            // Case: Add task
            if (!userInput.isEmpty()) {
                tasks[Task.tasksCount] = new Task(userInput);
                printContent("Added task: " + userInput);
            }
        }
        printContent("See you next time!");
    }
    public static void printContent(String content) {
        System.out.println(DIVIDER_LINE);
        System.out.println(content);
        System.out.println(DIVIDER_LINE);
    }
}
