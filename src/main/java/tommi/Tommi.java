package tommi;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tommi {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        printIntroMessage();
        readInputStrings(scanner);
        printExitMessage();
    }

    private static void readInputStrings(Scanner scanner) {
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            InputHandler.processInputCases(input);
            input = scanner.nextLine();
        }
    }

    private static void printIntroMessage() throws FileNotFoundException {
        String introFirst = """
                 ______                  \s
                /_  __/__  __ _  __ _  (_)
                 / / / _ \\/  ' \\/  ' \\/ /\s
                /_/  \\___/_/_/_/_/_/_/_/ \s
                ____________________________________________________________
                Hello! I'm Tommi!
                Here's your current task list:
                """;
        System.out.println(introFirst);
        TaskData.loadTaskData();
        String introSecond = """
                ____________________________________________________________
                How can I help you?
                ____________________________________________________________
                """;
        System.out.println(introSecond);
    }

    private static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
