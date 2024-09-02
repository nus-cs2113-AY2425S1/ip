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

        private static void printMarkConfirm(int marked) {
            Printer.printSeparator();
            System.out.println("I have marked the following task.");
            System.out.println((marked + 1) + "." + List.tasks[marked].getStatusIcon() + " " + List.tasks[marked].description);
            Printer.printSeparator();
        }

        private static void printUnmarkConfirm(int unmarked) {
            Printer.printSeparator();
            System.out.println("I have unmarked the following task.");
            System.out.println((unmarked + 1) + "." + List.tasks[unmarked].getStatusIcon() + " " + List.tasks[unmarked].description);
            Printer.printSeparator();
        }

        private static void printInvalidCommand() {
            Printer.printSeparator();
            System.out.println("This is an invalid instruction.");
            Printer.printSeparator();
        }

        private static void printAlreadyMarked() {
            Printer.printSeparator();
            System.out.println("This task is already marked.");
            Printer.printSeparator();
        }

        private static void printAlreadyUnmarked() {
            Printer.printSeparator();
            System.out.println("This task is already unmarked.");
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
                } else if (command.startsWith("mark ")) {
                    String numberString = command.split(" ")[1];
                    int indexToBeMarked;
                    try {
                        indexToBeMarked = Integer.parseInt(numberString) - 1;
                        if (indexToBeMarked >= List.listCount) {
                            Printer.printInvalidCommand();
                        } else {
                            List.mark(indexToBeMarked);
                        }
                    } catch (NumberFormatException e) {
                        Printer.printInvalidCommand();
                    }
                } else if (command.startsWith("unmark ")) {
                    String numberString = command.split(" ")[1];
                    int indexToBeUnmarked;
                    try {
                        indexToBeUnmarked = Integer.parseInt(numberString) - 1;
                        if (indexToBeUnmarked >= List.listCount) {
                            Printer.printInvalidCommand();
                        } else {
                            List.unmark(indexToBeUnmarked);
                        }
                    } catch (NumberFormatException e) {
                        Printer.printInvalidCommand();
                    }
                } else {
                    List.add(command);
                }
            }
        }

    }

    private static class List {

        private static class Task {
            protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X]" : "[ ]");
            }
        }

        private static int listCount = 0;

        private static Task[] tasks;

        private static void init() {
            tasks = new Task[100];
        }

        private static void add(String taskDesc) {
            Task newTask = new Task(taskDesc);
            tasks[listCount] = newTask;
            listCount += 1;
            Printer.printAddConfirm(taskDesc);
        }

        private static void list() {
            Printer.printSeparator();
            for (int i = 0; i < listCount; i += 1) {
                System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
            }
            Printer.printSeparator();
        }

        private static void mark(int toBeMarked){
            if (tasks[toBeMarked].isDone) {
                Printer.printAlreadyMarked();
            } else {
                tasks[toBeMarked].isDone = true;
                Printer.printMarkConfirm(toBeMarked);
            }
        }

        private static void unmark(int toBeUnmarked) {
            if (!tasks[toBeUnmarked].isDone) {
                Printer.printAlreadyUnmarked();
            } else {
                tasks[toBeUnmarked].isDone = false;
                Printer.printUnmarkConfirm(toBeUnmarked);
            }
        }

    }

}