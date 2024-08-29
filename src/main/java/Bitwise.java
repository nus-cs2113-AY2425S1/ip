import java.util.Scanner;
import java.util.Arrays;

public class Bitwise {
    // ASCII art from https://ascii-generator.site/t/
    private static final String logo = " ____   _  _              _            \n"
            + "|  _ \\ (_)| |            (_)           \n"
            + "| |_) | _ | |_ __      __ _  ___   ___ \n"
            + "|  _ < | || __|\\ \\ / /| |/ __| / _ \\ \n"
            + "| |_) || || |_  \\ V  V / | |\\__ \\|  __/\n"
            + "|____/ |_| \\__|  \\_/\\_/  |_||___/ \\___|\n"
            + "                                       \n";
    private static final String sectionBreak = "==================================================\n";
    private static final String lineBreak = "--------------------------------------------------\n";
    private static String[] taskList = new String[100];
    private static int numberOfTasks = 0;

    public static void main(String[] args) {
        System.out.println(sectionBreak + "Hello from\n" + logo);
        System.out.print("How may I help you today?\n" + lineBreak);
        mainManager();
        //echoUserInput();
        System.out.println(lineBreak + "Bye, see you soon!\n" + sectionBreak);
    }
    public static void mainManager() {
        String userInput;
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                return;
            }
            System.out.print(lineBreak);
            if (userInput.equalsIgnoreCase("list")) {
                printTaskList();
            }
            else {
                addToList(userInput);
                System.out.println("Added: " + userInput);
            }
            System.out.print(lineBreak);
        }
    }
    public static void echoUserInput() {
        String userInput;
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                return;
            }
            System.out.println(userInput);
        }
    }
    public static void addToList(String userInput) {
        taskList[numberOfTasks] = userInput;
        numberOfTasks++;
    }
    public static void printTaskList() {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(Integer.toString(i + 1) + ". " + taskList[i]);
        }
    }
}
