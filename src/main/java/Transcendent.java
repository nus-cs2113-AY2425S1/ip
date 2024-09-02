import java.util.Scanner;

public class Transcendent {

    public static void main(String[] args) {
        Printer.printEntry();
        List.init();
        InputHandler.takeInput();
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

        private static void printAddConfirm(String task) {
            Printer.printSeparator();
            System.out.println("Added: " + task);
            Printer.printSeparator();
        }

    }

    private static class InputHandler {

        private static void takeInput() {
            String command;
            while (true) {
                Scanner input = new Scanner(System.in);
                command = input.nextLine();
                if (command.equals("bye")) {
                    break;
                }
                if (command.equals("list")) {
                    List.list();
                } else {
                    List.add(command);
                }
            }
        }

    }

    private static class List {

        private static int listCount = 0;

        private static String[] tasks;

        private static void init() {
            tasks = new String[100];
        }

        private static void add(String task) {
            tasks[listCount] = task;
            listCount += 1;
            Printer.printAddConfirm(task);
        }

        private static void list() {
            Printer.printSeparator();
            for (int i = 0; i < listCount; i += 1) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
            Printer.printSeparator();
        }

    }

}