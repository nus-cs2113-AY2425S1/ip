package hsien;

import hsien.task.*;
import hsien.exception.*;
import hsien.ui.*;
import hsien.storage.*;
import hsien.parser.*;
import hsien.tasklist.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.regex.*;

public class Hsien {
    public static void addTask(String command, String desc, ArrayList<Task> messages) {
        // Empty task
        try {
            if (desc.isEmpty()) {
                throw new HsienException();
            }
        } catch (HsienException e) {
            System.out.println("Description cannot be left empty");
            ui.printLine();
            return;
        }

        Task newTask = null;
        String tempDesc;

        // Create Task object based on action
        if (command.equals("todo")) {
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            tempDesc = desc.split("/by")[0].trim();
            String byDate = desc.split("/by")[1].trim();
            newTask = new Deadline(tempDesc, byDate);
        } else {
            tempDesc = desc.split("/from")[0].trim();
            String[] dates = desc.split("/from")[1].split("/to");
            String fromDate = dates[0].trim();
            String toDate = dates[1].trim();
            newTask = new Event(tempDesc, fromDate, toDate);
        }

        messages.add(newTask);
        System.out.println("Added task: " + newTask.getDescription());
        System.out.printf("Now you have [%d] tasks in the list.%n", messages.size());
    }

    public static void handleMark(String command, String desc, ArrayList<Task> messages) {
        // Get the task index
        int index = Integer.parseInt(desc);
        boolean isMarking = command.equals("mark");

        // Perform marking or unmarking
        if (isMarking) {
            messages.get(index - 1).mark();
            System.out.println("You marked " + index + " as marked");
        } else {
            messages.get(index - 1).unmark();
            System.out.println("You marked " + index + " as unmarked");
        }

        // Print status of the task
        System.out.println(messages.get(index - 1).getStatusDescription());
    }

    public static void deleteTask(ArrayList<Task> messages, int index) {
        System.out.println("Noted I have removed this task");
        System.out.println(messages.get(index).getStatusDescription());
        messages.remove(index);
        System.out.printf("Now you have %d tasks in the list\n", messages.size());
    }

    private static Ui ui;

    public static void main(String[] args) {
        // Initialise variables
        ui = new Ui();
        Storage storage = new Storage("tasks.txt");
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        List<String> validCommands = Arrays.asList("bye", "list", "mark", "unmark", "delete", "todo", "deadline", "event");
        boolean isRunning = true;

        ui.welcomeMessage();
        storage.readFile(taskList.getTasks());
        ui.printLine();

        while (isRunning) {
            ui.printCommands(validCommands);
            String input = ui.readCommand();

            parser.processCommand(input);

            try {
                if (!validCommands.contains(parser.getCommand())) {
                    throw new HsienException();
                }
            } catch (HsienException e) {
                System.out.println("Please enter a valid command from the list! ");
                ui.printLine();
                continue;
            }

            switch (parser.getCommand()) {
            case "bye":
                System.out.println("Have a good day! Bye!");
                isRunning = false;
                break;
            case "list":
                ui.printList(taskList.getTasks());
                break;
            case "mark":
            case "unmark":
                try {
                    taskList.handleMark(parser.getCommand(), parser.getDesc());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds");
                }
                break;
            case "delete":
                try {
                    taskList.deleteTask(Integer.parseInt(parser.getDesc()) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format");
                }
                break;
            default:
                try {
                    taskList.addTask(parser.getCommand(), parser.getDesc());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter in a proper format");
                }
                break;
            }
            ui.printLine();
        }
        ui.close();
        storage.writeFile(taskList.getTasks());
    }
}
