import java.util.Scanner;

public class Grok {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        printLine();
        System.out.println("Hello! I'm Grok");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon! Keep Grokking :)");
                printLine();
                break;
            } else if (input.equals("list")) {
                printLine();
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printLine();
            } else {
                tasks[taskCount] = input;
                taskCount++;
                printLine();
                System.out.println("added: " + input);
                printLine();
            }
        }

        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
