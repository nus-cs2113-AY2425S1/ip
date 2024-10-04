package ui;

import tasks.Task;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String DIVIDER = "____________________________________________________________\n\n";
    private final Scanner in;
    private final PrintStream out;


    public Ui(){
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    public void showWelcome(){
        out.print(DIVIDER + "Hello! I'm  Doot\nWhat can I do for you?\n" + DIVIDER);
    }

    public void showExit(){
        out.print(DIVIDER + "Bye. Hope to see you again soon!" + "\n" + DIVIDER);
    }

    public String readCommand() {
        return in.nextLine();
    }


    public void printUnknownCommand(String command) {
        out.println("Unknown command: " + command);
    }

    public void printList(ArrayList<Task> tasks) {
        out.print(DIVIDER);
        for (Task task : tasks) {
            out.println(task);
        }
        out.print(DIVIDER);
    }
}
