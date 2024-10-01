package Uranus.Utility;

import java.util.Scanner;
import java.util.ArrayList;
import Uranus.Tasks.Deadlines;
import Uranus.Tasks.Events;
import Uranus.Tasks.Task;
import Uranus.Tasks.ToDos;
import UranusExceptions.*;

public abstract class Functions {

    protected static final Scanner in = new Scanner(System.in);
    protected static final String SEPARATOR = "_________________________________________________________";
    protected static ArrayList<Task> taskList = new ArrayList<>();

    public Functions() {}

    public static void printFunctions(){
        print(
                "Currently, I am able to execute the following functions:",
                "1. Add tasks: I can add tasks to your task list.",
                "   - Type command: <task>",
                "   - Type command: todo <task>",
                "   - Type command: deadline <task> /by <date>",
                "   - Type command: event <task> /from <day> <start time> /to <end time>",
                "2. Mark tasks as done:",
                "   - Type command: mark <task number>",
                "3. Mark tasks as not done:",
                "   - Type command: unmark <task number>",
                "4. List all tasks.",
                "   - Type command: list",
                "5. Be a parrot!",
                "   - Type command: echo",
                "6. Shut myself down. Use this if you no longer need me :(",
                "   - Type command: bye"
        );
    }

    public static void print(String... messages){
        System.out.println(SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SEPARATOR);
    }

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
                Chatbot.printByeMessage();
                System.exit(0);
                // Fallthrough

            case "list":
                TaskList.listTasks();
                break;

            case "echo":
                echo();
                break;

            default:
                TaskList.addTask(input);
                break;
            }
        }
    }

    public static void echo(){
        while(true){
            System.out.println("Say anything! If you are no longer bored, type exit !");
            String input = in.nextLine();
            if(input.equals("exit")){
                System.out.println(SEPARATOR);
                printFunctions();
                break;
            }
            print(input);
        }
    }
}
