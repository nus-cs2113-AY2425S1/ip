package hsien.ui;

import hsien.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void printLogo() {
        System.out.println(" _   _         _____        _ _   _ ");
        System.out.println("| | | | ##### |_   _|##### |   \\ | |");
        System.out.println("| |_| |#        | |  #     | |\\ \\| |");
        System.out.println("|  _  | #####   | |  ##### | | \\ \\ |");
        System.out.println("| | | |      # _| |_ #     | |  \\  |");
        System.out.println("|_| |_| ##### |_____|##### |_|   \\_|\n");
    }

    public void printLine() {
        System.out.println("\n" + "-".repeat(50) + "\n");
    }

    public void welcomeMessage() {
        printLine();
        printLogo();
        // Greet
        System.out.println("Hello! I am Hsien, your personal chatbot...");
        printLine();
    }

    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("List is currently empty. Please add a task!");
            return;
        }
        int counter = 1;
        System.out.println("Here are the tasks in your list!");
        for (Task t : tasks) {
            System.out.printf("%s. %s%n", counter, t.getStatusDescription());
            counter += 1;
        }
    }

    public void printCommands(List<String> validCommands) {
        System.out.println("These are the possible commands:");
        for (int i=1; i<= validCommands.size(); i+=1) {
            System.out.printf("%d. %s\n", i, validCommands.get(i - 1));
        }
        System.out.print("Please enter a command/add task (type 'bye' to exit): ");
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void close() {
        in.close();
    }
}
