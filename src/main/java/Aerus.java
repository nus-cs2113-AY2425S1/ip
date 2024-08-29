import java.util.Scanner;

public class Aerus {
    public static void printContent(String content) {
        String dividerLine = "____________________________________________________________";
        System.out.println(dividerLine);
        System.out.println(content);
        System.out.println(dividerLine);
    }

    public static void main(String[] args) {
        String dividerLine = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        String userInput;
        String tasks[] = new String[100];
        int taskCount = 0;

        printContent("Hello! I'm Aerus!\nWhat can I do for you?");

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                if(taskCount == 0) {
                    printContent("You don't have any tasks!");
                    continue;
                }

                System.out.println(dividerLine);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
                System.out.println(dividerLine);
                continue;
            }

            tasks[taskCount] = userInput;
            taskCount++;
            printContent("added: " + userInput);
        }

        printContent("See you next time!");
    }
}
