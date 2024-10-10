package tommi;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void printIntroMessage() throws FileNotFoundException {
        String introFirstSection = """
                 ______                  \s
                /_  __/__  __ _  __ _  (_)
                 / / / _ \\/  ' \\/  ' \\/ /\s
                /_/  \\___/_/_/_/_/_/_/_/ \s
                ____________________________________________________________
                Hello! I'm Tommi!
                Here's your last-saved task list:
                """;

        System.out.println(introFirstSection);

        Storage.loadTaskData();
        String introSecondSection = """
                ____________________________________________________________
                How can I help you?
                ____________________________________________________________
                """;
        System.out.println(introSecondSection);
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
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

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
