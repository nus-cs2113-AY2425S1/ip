package poppy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.*;
import exceptions.*;
import static commands.Commands.*;

public class Printer {

    public static String input;

    public static void processCommands(String input, ArrayList<Task> taskList) {
        String[] commandArgs = input.split(" ", 2);
        String command = commandArgs[0];
        try {
            switch (command) {
                case "delete":
                    delete(taskList, Integer.parseInt(commandArgs[1]));
                    break;
                case "mark":
                    markAsDone(taskList, commandArgs);
                    break;
                case "unmark":
                    markAsNotDone(taskList, commandArgs);
                    break;
                case "List":
                    showList(taskList);
                    break;
                case "todo":
                    if (commandArgs.length < 2) {
                        throw new CustomExceptions.MissingArgsException("Description of ToDo cannot be empty");
                    }
                    ToDo task = new ToDo(commandArgs[1]);
                    taskList.add(task);
                    System.out.println(task.toString());
                    System.out.println("You now have " + taskList.size() + " tasks");
                    break;
                case "deadline":
                    if (commandArgs.length < 2) {
                        throw new CustomExceptions.MissingArgsException("Description of Deadline cannot be empty");
                    }
                    String[] deadlinestring = commandArgs[1].split("/by", 2);
                    Deadline deadline = new Deadline(deadlinestring[0], deadlinestring[1]);
                    taskList.add(deadline);
                    System.out.println(deadline.toString());
                    System.out.println("You now have " + taskList.size() + " tasks");
                    break;
                case "event":
                    if (commandArgs.length < 2) {
                        throw new CustomExceptions.MissingArgsException("Description of Event cannot be empty");
                    }
                    String[] eventstring = commandArgs[1].split("/from", 2);
                    Events event = new Events(eventstring[0], eventstring[1]);
                    taskList.add(event);
                    System.out.println(event.toString());
                    System.out.println("You now have " + taskList.size() + " tasks");
                    break;
                case "Bye":
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wait, I don't understand what you are saying??");
        } catch (CustomExceptions.MissingArgsException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You are missing a keyword... read manual please!");
        }
    }

    public static void processFile(String input, ArrayList<Task> taskList) {
        String[] commandArgs = input.split(" ", 3);
        if (commandArgs.length < 3) return; // Ensure there are enough arguments

        String command = commandArgs[0];
        String status = commandArgs[1];
        String description = commandArgs[2];

        switch (command) {
            case "T":
                ToDo task = new ToDo(description);
                taskList.add(task);
                break;
            case "D":
                String[] deadlineParts = description.split(" ", 2);
                Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                taskList.add(deadline);
                break;
            case "E":
                String[] eventParts = description.split(" ", 2);
                Events event = new Events(eventParts[0], eventParts[1]);
                taskList.add(event);
                break;
            default:
                return;
        }

        if (status.equals("1")) {
            taskList.get(taskList.size() - 1).markAsDone();
        } else if (status.equals("0")) {
            taskList.get(taskList.size() - 1).markAsNotDone();
        }
    }

    public static void Print(File file) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());
        }

        Scanner sc = new Scanner(file);
        System.out.println("Hello! I'm Poppy");
        System.out.println("What can I do for you?");
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            processFile(input, taskList);
        }
        sc.close();

        System.out.println("File scan finished, waiting for user input...");

        Scanner userInput = new Scanner(System.in);
        input = userInput.nextLine();
        processCommands(input, taskList);
        while (!input.equals("Bye")) {
            input = userInput.nextLine();
            processCommands(input, taskList);
            writeToFile(file, taskList);
        }
        System.out.println("Bye. Hope to see you again soon!");
        userInput.close();
    }

    private static void writeToFile(File file, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(file, false);

        for (Task task : taskList) {
            if (task != null) {
                String line = getString(task);
                if (task instanceof Deadline deadline) {
                    line += deadline.getBy();
                } else if (task instanceof Events event) {
                    line += event.getFrom();
                }
                fw.write(line + System.lineSeparator());
            }
        }

        fw.close();
    }

    private static String getString(Task task) {
        int statusNumber = (task.getStatusIcon().equals(" ")) ? 0 : 1;
        String type = "";
        if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Events) {
            type = "E";
        } else if (task instanceof ToDo) {
            type = "T";
        }
        return type + " " + statusNumber + " " + task.description + " ";
    }

}
