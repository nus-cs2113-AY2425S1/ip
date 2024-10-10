package tommi;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void printIntroMessage() throws FileNotFoundException {
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
        Storage.loadTaskData();
        String introSecond = """
                ____________________________________________________________
                How can I help you?
                ____________________________________________________________
                """;
        System.out.println(introSecond);
    }

    public static void readInputStrings(Scanner scanner) {
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            Parser.processInputCases(input);
            input = scanner.nextLine();
        }
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printSearchResults(ArrayList<String> foundResults) {
        if (foundResults.isEmpty()) {
            System.out.println("No results found!");
            return;
        }

        System.out.println("Here are the matching results: ");
        int index = 1;
        for (String result : foundResults) {
            System.out.println(index + ": " + result);
            index++;
        }

    }
}
