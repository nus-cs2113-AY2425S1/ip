package Uranus.Utility;

import java.util.Scanner;
import java.util.ArrayList;
import Uranus.Tasks.Task;

public abstract class Functions {

    protected static final Scanner in = new Scanner(System.in);
    protected static final String SEPARATOR = "_________________________________________________________";
    protected static ArrayList<Task> taskList = new ArrayList<>();

    public Functions() {}

    public static void manageTasks() {
        FileManagement.load();
        while (true) {
            String input = in.nextLine();
            processCommand(input);
            FileManagement.saveFile();
        }
    }

    protected static void processCommand(String input) {
        if (input.startsWith("mark") || input.startsWith("unmark")) {
            TaskList.handleMarking(input);
        } else if (input.startsWith("delete")) {
            TaskList.handleDelete(input);
        } else {
            switch (input) {
            case "bye":
                Ui.printByeMessage();
                System.exit(0);
                // Fallthrough

            case "list":
                TaskList.listTasks();
                break;

            case "echo":
                Ui.echo();
                break;

            default:
                TaskList.addTask(input);
                break;
            }
        }
    }
}
