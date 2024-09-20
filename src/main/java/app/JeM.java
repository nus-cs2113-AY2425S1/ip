package app;

import filemanager.FileManager;
import taskmanager.Storage;
import exceptions.InvalidCommandException;
import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

import java.io.File;
import java.util.Scanner;

public class JeM {

    public static void main(String[] args) {
        displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("bye")) {
                exitChatBot();
                break;
            }
            try {
                handleInput(line, storage);
            }catch(InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        printBreakLine();
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        String logo = "      _         __  __ \n"
                + "     | |       |  \\/  |\n"
                + "     | |  ___  | |\\/| |\n"
                + " _   | | / _ \\ | |  | |\n"
                + "| |__| ||  __/ | |  | |\n"
                + " \\____/  \\___| |_|  |_|\n";
        System.out.println("Hello from\n" + logo);

        printBreakLine();

        System.out.println(" Hello! I'm JeM, Your e-Assistant");
        System.out.println(" Personal To-Do list bot! ");
        System.out.println(" Just type out your tasks you have to complete and I will make a list of them for you.");
        System.out.println(" For tasks with no deadline, type todo <task name>, for tasks with deadlines, type deadline <task name> /by <date and time>");
        System.out.println(" For events, type event <task name> /from <date and time> /to <date and time>");
        System.out.println(" Type 'list' to see the current list of tasks,'delete <task number>' to delete that task");
        System.out.println(" Finally, type 'bye' to end the chat!");

        printBreakLine();
    }

    private static void handleInput(String line, Storage storage) throws InvalidCommandException {
        if (line.equalsIgnoreCase("list")) {
            storage.storageList();
        } else if (line.toLowerCase().startsWith("delete")) {
            handleDeleteCommand(line, storage);
        } else if (line.toLowerCase().startsWith("unmark")) {
            handleUnmarkCommand(line, storage);
        } else if (line.toLowerCase().startsWith("mark")) {
            handleMarkCommand(line, storage);
        } else if (line.toLowerCase().startsWith("todo")) {
            handleTodoCommand(line, storage);
        } else if (line.toLowerCase().startsWith("deadline")) {
            handleDeadlineCommand(line, storage);
        } else if (line.toLowerCase().startsWith("event")) {
            handleEventCommand(line, storage);
        }else if (line.toLowerCase().startsWith("clear")) {
            handleClearCommand(storage);
        }else {
            System.out.println("Unknown command: " + line);
        }
    }

    private static void handleDeleteCommand(String line, Storage storage) throws InvalidCommandException {
        String[] parts = line.split(" ");
        if (parts.length < 2){
            throw new InvalidCommandException("Provide index of the task to delete");
        }
        int index = Integer.parseInt(parts[1]);
        System.out.println("I have removed this task: ");
        storage.storagePrintTask(index);
        storage.storageDelete(index);
        storage.storageList();
    }

    private static void handleClearCommand(Storage storage) {
        storage.storageClear();
        System.out.println("Your Task list is empty");
    }

    private static void handleUnmarkCommand(String line, Storage storage) throws InvalidCommandException {
        String[] parts = line.split(" ");
        if (parts.length < 2){
            throw new InvalidCommandException("Provide index of the task to unmark");
        }
        int index = Integer.parseInt(line.split(" ")[1]);
        storage.storageUnmark(index);
        storage.storageList();
    }

    private static void handleMarkCommand(String line, Storage storage) throws InvalidCommandException {
        String[] parts = line.split(" ");
        if (parts.length < 2){
            throw new InvalidCommandException("Provide index of the task to mark");
        }
        int index = Integer.parseInt(line.split(" ")[1]);
        storage.storageMark(index);
        storage.storageList();
    }

    private static void handleTodoCommand(String line, Storage storage) throws InvalidCommandException {
        if (line.trim().length() <= 4){
            throw new InvalidCommandException("The description of the todo task cannot be empty");
        }

        String taskContent = line.substring(5).trim();
        Task task = new Todo(taskContent);
        storage.storageInsert(task);
        storage.storageList();
    }

    private static void handleDeadlineCommand(String line, Storage storage) throws InvalidCommandException {
        String[] parts = line.substring(8).split(" /by ");
        if (parts[0].trim().isEmpty() ){
            throw new InvalidCommandException("The description of the deadline task cannot be empty");
        }
        if (parts.length < 2){
            throw new InvalidCommandException("The deadline of the task cannot be empty");
        }
        String taskContent = parts[0].trim();
        String deadline = parts[1].trim();
        Task task = new Deadline(taskContent, deadline);
        storage.storageInsert(task);
        storage.storageList();
    }

    private static void handleEventCommand(String line, Storage storage) throws InvalidCommandException {
        if (!line.contains("/from") || !line.contains("/to")) {
            throw new InvalidCommandException("Missing start or end date and time");
        }

        String[] parts = line.split(" /from ");
        String preCleanedTaskContent = parts[0].trim();

        if (preCleanedTaskContent.length() <= 5) {
            throw new InvalidCommandException("The description of the event task is missing");
        }

        String taskContent = preCleanedTaskContent.substring(5).trim();


        String[] dateTime = parts[1].split(" /to ");

        if (dateTime.length < 2) {
            throw new InvalidCommandException("The start or end date and time are missing");
        }

        String start = dateTime[0].trim();
        String end = dateTime[1].trim();

        Task task = new Event(taskContent, start, end);
        storage.storageInsert(task);
        storage.storageList();
    }

    private static void exitChatBot() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printBreakLine() {
        System.out.println("____________________________________________________________");
    }
}