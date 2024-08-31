import java.util.Scanner;

public class Transcendent {

    public static void main(String[] args) {
        Printer.printEntry();
        echo();
        Printer.printExit();
    }

    private static class Printer {

        private static void printEntry() {
            Printer.printSeparator();
            System.out.println("Greetings.");
            System.out.println("I am Transcendent.");
            System.out.println("What do you need assistance with?");
            Printer.printSeparator();
        }

        private static void printSeparator() {
            System.out.println("____________________________________________________________");
        }

        private static void printExit() {
            Printer.printSeparator();
            System.out.println("Let me know should you need assistance again.");
            System.out.println("Farewell.");
            Printer.printSeparator();
        }

    }

    private static void echo() {
        String line;
        while (true) {
            Scanner input = new Scanner(System.in);
            line = input.nextLine();
            if (line.equals("bye")) {
                break;
            }
            Printer.printSeparator();
            System.out.println(line);
            Printer.printSeparator();
        }
    }

}