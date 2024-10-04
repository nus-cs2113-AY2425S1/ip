import Commands.Command;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;

import java.util.Scanner;

public class Doot {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        new Doot().run();
    }

    public Doot(){
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());

    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String currentInput = ui.readCommand();
            Command c = Parser.findCommand(currentInput);
            isExit = c.isExit();
        }
        ui.showExit();
    }


    private void handleWordDigit(String command, String args) {
        int digit = Integer.parseInt(args);
        switch (command) {
            case "mark":
                markTask(digit);
                break;
            case "unmark":
                unmarkTask(digit);
                break;
            case "delete":
                tasks.deleteTask(digit);
                break;
            default:
                tasks.addToList(command);
                break;
        }
    }

    private void handleDeadline(String command, String args) {
        String[] parts = args.split(" /by ");
        String wordOne = parts[0];
        String wordTwo = parts[1];
        switch (command) {
            case "deadline":
                makeDeadline(wordOne, wordTwo);
                break;
            default:
                tasks.addToList(command + args);
                break;
        }
    }

    private static void handleEvent(String command, String args) {
        String[] parts = args.split(" /from ");
        String wordOne = parts[0];
        String wordTwo = parts[1];
        parts = wordTwo.split(" /to ");
        wordTwo = parts[0];
        String wordThree = parts[1];
        switch (command) {
            case "event":
                makeEvent(wordOne, wordTwo, wordThree);
                break;
            default:
                addToList(command + args);
                break;
        }
    }

    private static void handleDefault(String command, String args) {
        switch (command) {
            case "todo":
                try {
                    makeToDo(args);
                } catch (DootException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "list":
                printList();
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    public static void makeDeadline(String description, String by) {
        taskList.add(new Deadline(description, by));
        System.out
                .print(DIVIDER + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
    }

    public static void makeEvent(String description, String to, String from) {
        taskList.add(new Event(description, to, from));
        System.out
                .print(DIVIDER + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
    }

    public static void makeToDo(String description) throws DootException {
        if (description.equals("")) {
            throw new DootException("The description of a todo cannot be empty.");
        }
        taskList.add(new ToDo(description));
        System.out
                .print(DIVIDER + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.\n" + DIVIDER);
    }

    public static void markTask(int idx) {
        taskList.get(taskList.size() - 1).markDone();
        System.out.println(DIVIDER + "Nice! I've marked this task as done: "
                + taskList.get(taskList.size() - 1).getDescription() + "\n"
                + DIVIDER);
    }

    public static void unmarkTask(int idx) {
        taskList.get(-1).markUnDone();
        System.out.println(DIVIDER + "OK, I've marked this task as not done yet: "
                + taskList.get(taskList.size() - 1).getDescription()
                + "\n" + DIVIDER);
    }

}
