package Uranus.Utility;

import java.util.Scanner;
import java.util.ArrayList;
import Uranus.Tasks.Deadlines;
import Uranus.Tasks.Events;
import Uranus.Tasks.Task;
import Uranus.Tasks.ToDos;
import UranusExceptions.*;

public class Functions {

    protected static final Scanner in = new Scanner(System.in);
    private static final String SEPARATOR = "_________________________________________________________";
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

    public void manageTasks() {
        FileManagement.load();
        while (true) {
            String input = in.nextLine();
            processCommand(input);
            FileManagement.saveFile();
        }
    }

    protected static void processCommand(String input) {
        if (input.startsWith("mark") || input.startsWith("unmark")) {
            handleMarking(input);
        } else if (input.startsWith("delete")) {
            handleDelete(input);
        } else {
            switch (input) {
            case "bye":
                Chatbot.printByeMessage();
                System.exit(0);
                // Fallthrough

            case "list":
                listTasks();
                break;

            case "echo":
                echo();
                break;

            default:
                addTask(input);
                break;
            }
        }
    }

    private static void handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (index >= 0 && index < taskList.size()) {
                print("Got it. I've removed this task:",
                        "  " + taskStatus(index),
                        "Now you have %d task(s) in the list".formatted(taskList.size() - 1));
                taskList.remove(index);
            } else {
                print("No such task exists. Please try again.");
            }
        } catch (NumberFormatException e) {
            print("Invalid task input. Please try again.", "Correct format: delete <int>");
        } catch (IllegalArgumentException e) {
            print("Task Number cannot be empty!");
        }
    }

    private static void handleMarking(String input) {
        try {
            int taskNumIndex = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (taskNumIndex >= 0 && taskNumIndex < taskList.size()) {
                if (input.startsWith("mark")) {
                    taskList.get(taskNumIndex).setDone();
                    print("Nice! I've marked this task as done:", taskStatus(taskNumIndex));
                } else {
                    taskList.get(taskNumIndex).setNotDone();
                    print("OK! I've marked this task as not done yet:", taskStatus(taskNumIndex));
                }
            } else {
                print("No such task exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            print("Invalid task input. Please try again.", "Correct format: mark <int> / unmark <int>");
        } catch (IllegalArgumentException e) {
            print("Task number cannot be empty!");
        }
    }

    public static String taskStatus(int index){
        return taskList.get(index).getTaskStatus();
    }

    private static void listTasks() {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskStatus(i));
        }
        System.out.println(SEPARATOR);
    }

    private static void addTask(String input) {

        try {
            if (input == null || input.trim().isEmpty()) {
                throw new EmptyInputExceptions();
            } else if (input.trim().equals("todo") ||
                    input.trim().equals("deadline") ||
                    input.trim().equals("task") ||
                    input.trim().equals("event")){
                throw new EmptyCommandException();
            } else if (input.startsWith("todo ")){
                taskList.add(new ToDos(input));
            } else if (input.startsWith("deadline ")){
                taskList.add(new Deadlines(input));
            } else if (input.startsWith("event ")){
                taskList.add(new Events(input));
            } else if (input.startsWith("task ")){
                taskList.add(new Task(input));
            } else{
                throw new IllegalCommandException();
            }
            print("Got it. I've added this task:",
                    "  " + taskStatus(taskList.size() - 1),
                    "Now you have %d task(s) in the list".formatted(taskList.size()));
        } catch (UranusExceptions e){
            print(e.getMessage());
        }
    }

    private static void echo(){
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
