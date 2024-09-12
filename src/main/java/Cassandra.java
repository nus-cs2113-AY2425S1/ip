import CassHelpers.Exceptions.*;
import CassHelpers.Tasks.Deadline;
import CassHelpers.Tasks.Event;
import CassHelpers.Tasks.Task;
import CassHelpers.Tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Cassandra {

    private final static ArrayList<Task> taskList = new ArrayList<>();
    public  final static int SEPARATOR_LENGTH = 75;
    private static boolean ifExit = false;

    private static void drawLine(){
        for(int i=0;i<SEPARATOR_LENGTH;i+=1){
            System.out.print("-");
        }
        System.out.println();
    }

    private static void exit() {
        ifExit = true;
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private static void displayIntroductionArt() {
            System.out.println("   ___                                               _                   ");
            System.out.println("  / __|   __ _     ___     ___    __ _    _ _     __| |     _ _   __ _   ");
            System.out.println(" | (__   / _` |   (_-<    (_-<   / _` |  | ' \\   / _` |    | '_| / _` |  ");
            System.out.println("  \\___|  \\__,_|   /__/_   /__/_  \\__,_|  |_||_|  \\__,_|   _|_|_  \\__,_|  ");
            System.out.println("_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| ");
            System.out.println("`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ");
    }

    private static void displayIntroduction(){
        drawLine();
        displayIntroductionArt();
        drawLine();
        System.out.println(" Hello! I'm Cassandra");
        System.out.println(" What can I do for you?");
        drawLine();
    }

    private static void saveTask(Task input){
        taskList.add(input);
        System.out.println("Got it. I've added this task: \n "+ input.toString());
        System.out.println("Now you have "+taskList.size()+" tasks in the list.");
    }

    private static void markTask(int index) throws TaskNotFoundException, TaskAlreadyMarkedException {
        if (index <= 0 || index > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else if (taskList.get(index - 1).getIsCompleted()) {
            throw new TaskAlreadyMarkedException("Task has already been marked complete");
        } else {
            taskList.get(index - 1).setCompleted(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(" " + taskList.get(index - 1).toString());
        }
    }

    private static void unmarkTask(int index) throws TaskNotFoundException, TaskAlreadyUnmarkedException {
        if (index <= 0 || index > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else if (!taskList.get(index - 1).getIsCompleted()) {
            throw new TaskAlreadyUnmarkedException("Task has already been marked incomplete");
        } else {
            taskList.get(index - 1).setCompleted(false);
            System.out.println(" OK, I've marked this task as not done yet: ");
            System.out.println(" " + taskList.get(index - 1).toString());
        }
    }

    private static void printList(){
        if(taskList.isEmpty()){
            System.out.println("List is empty");
        } else {
            System.out.println("Here are the tasks in your list: :");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
        }
    }

    private static void addTodo(String input) {
        int todoOffset = 4;
        String taskName = input.substring(todoOffset).trim();
        saveTask(new Todo(taskName));
    }


    private static void addEvent(String input) throws InvalidEventFormatException {
        int fromIndexOffset = 6;
        int toIndexOffset = 4;

        int fromIndex = input.indexOf("/from") + fromIndexOffset;
        int toIndex = input.indexOf("/to");

        if (fromIndex < fromIndexOffset || toIndex < 0) {
            throw new InvalidEventFormatException("Sorry, event entered has the wrong format");
        }

        String from = input.substring(fromIndex, toIndex).trim();
        String to = input.substring(toIndex + toIndexOffset).trim();
        String eventTaskName = input.substring(0, fromIndex - fromIndexOffset).trim();

        saveTask(new Event(eventTaskName, from, to));
    }

    private static void addDeadline(String input) throws InvalidDeadlineFormatException {
        int byIndexOffset = 4;
        int byIndex = input.indexOf("/by");

        if (byIndex < 0) {
            throw new InvalidDeadlineFormatException("Sorry, deadline entered has the wrong format");
        }

        String by = input.substring(byIndex + byIndexOffset).trim();
        String deadlineTaskName = input.substring(0, byIndex).trim();

        saveTask(new Deadline(deadlineTaskName, by));
    }

    private static void executeCommand(String input, String[] commandArgs) {
        if (commandArgs.length == 0 || commandArgs[0].isEmpty()) {
            System.out.println("Sorry, you haven't entered any command.");
            return;
        }

        try {
            switch (commandArgs[0].toLowerCase()) {
                case "mark":
                    if (commandArgs.length < 2) {
                        throw new NoTaskIndexFoundException("Please provide a task index to mark.");
                    } else {
                        markTask(Integer.parseInt(commandArgs[1]));
                    }
                    break;
                case "unmark":
                    if (commandArgs.length < 2) {
                        throw new NoTaskIndexFoundException("Please provide a task index to unmark.");
                    } else {
                        unmarkTask(Integer.parseInt(commandArgs[1]));
                    }
                    break;
                case "list":
                    printList();
                    break;
                case "bye":
                    exit();
                    break;
                case "todo":
                    addTodo(input);
                    break;
                case "deadline":
                    addDeadline(input);
                    break;
                case "event":
                    addEvent(input);
                    break;
                default:
                    throw new InvalidCommandException("Sorry, unknown command.");
            }
        } catch (TaskNotFoundException | NoTaskIndexFoundException | TaskAlreadyMarkedException |
                 TaskAlreadyUnmarkedException | InvalidEventFormatException | InvalidDeadlineFormatException | InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task index.");
        }
    }

    private  static void readUserCommand() {
        String input = new Scanner(System.in).nextLine().trim();
        String[] commandArgs = input.split(" ");
        drawLine();
        executeCommand(input,commandArgs);
        drawLine();
    }

    public static void main(String[] args) {
        displayIntroduction();
        while(!ifExit) {
            readUserCommand();
        }
    }
}
