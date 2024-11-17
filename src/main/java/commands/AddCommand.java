package commands;

import data.TaskList;
import exceptions.DootException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final ArrayList<String> COMMAND_WORDS = new ArrayList<>(Arrays.asList(new String[]{"deadline", "todo", "event"}));

    public AddCommand(String cmd, String args) {
        super(cmd, args);
    }

    /**
     * @param tasks TaskList object that stores all the tasks currently stored
     * @param ui Ui object used for interacting with the user
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui) {
        switch(cmd){
            case "deadline":
                handleDeadline(tasks);
                break;
            case "todo":
                handleToDo(tasks);
                break;
            case "event":
                handleEvent(tasks);
                break;
        }
    }

    private void handleDeadline(TaskList tasks) {
        try {
            String[] parts = args.split(" /by ");
            String wordOne = parts[0];
            String wordTwo = parts[1];
            makeDeadline(wordOne, wordTwo, tasks);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Invalid deadline format");
        }
    }

    private void handleEvent(TaskList tasks){
        try {
            String[] parts = args.split(" /from ");
            String wordOne = parts[0];
            String wordTwo = parts[1];
            parts = wordTwo.split(" /to ");
            wordTwo = parts[0];
            String wordThree = parts[1];
            makeEvent(wordOne, wordTwo, wordThree, tasks);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Invalid event format");
        }
    }

    private void handleToDo(TaskList tasks) {
        try {
            makeToDo(args, tasks);
        } catch (DootException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void makeDeadline(String description, String by, TaskList tasks) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.addToList(newDeadline);
        System.out
                .print(Ui.DIVIDER + "Got it. I've added this task:\n" + newDeadline + "\n"
                        + "Now you have " + tasks.getTaskCount() + " tasks in the list.\n" + Ui.DIVIDER);
    }

    private void makeEvent(String description, String to, String from, TaskList tasks) {
        Event newEvent = new Event(description, to, from);
        tasks.addToList(newEvent);
        System.out
                .print(Ui.DIVIDER + "Got it. I've added this task:\n" + newEvent + "\n"
                        + "Now you have " + tasks.getTaskCount() + " tasks in the list.\n" + Ui.DIVIDER);
    }

    private void makeToDo(String description, TaskList tasks) throws DootException {
        if (description.isEmpty()) {
            throw new DootException("The description of a todo cannot be empty.");
        }
        ToDo newToDo = new ToDo(description);
        tasks.addToList(newToDo);
        System.out
                .print(Ui.DIVIDER + "Got it. I've added this task:\n" + newToDo + "\n"
                        + "Now you have " + tasks.getTaskCount() + " tasks in the list.\n" + Ui.DIVIDER);
    }
}
