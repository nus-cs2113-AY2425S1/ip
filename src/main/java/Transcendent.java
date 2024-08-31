import java.util.Scanner;

public class Transcendent {

    public static void main(String[] args) {
        printEntry();
        echo();
        printExit();
    }

    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    private static void printEntry() {
        printSeparator();
        System.out.println("Greetings.");
        System.out.println("I am Transcendent.");
        System.out.println("What do you need assistance with?");
        printSeparator();
    }

    private static void echo() {
        String line;
        while (true) {
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            if (line.equals("bye")) {
                break;
            }
            printSeparator();
            System.out.println(line);
            printSeparator();
        }
    }

    private static void printExit() {
        printSeparator();
        System.out.println("Let me know should you need assistance again.");
        System.out.println("Farewell.");
        printSeparator();
    }
}