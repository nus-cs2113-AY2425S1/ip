package main.java;

import main.java.Ui;
import main.java.Parser;
import main.java.TaskList;
import main.java.Storage;

import ran.task.Deadline;
import ran.task.Event;
import ran.task.Task;
import ran.task.TaskType;
import ran.task.Todo;
import ran.exception.CommandType;
import ran.exception.MissingArgumentException;
import ran.exception.MissingCommandException;
import ran.exception.MissingDescriptionException;
import ran.exception.EmptyListException;
import ran.exception.OutOfListBoundsException;
import ran.exception.RanException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ran {
    private static boolean isTerminated = false; 
    private static int listCount = 0;
    private static final String LINE = "\t____________________________________________________________";
    private static String directory = "./data";
    
    private static Storage storage; 
    private static Ui ui;
    private static TaskList tasks;

    // Process task based on its type into relevant fields to be added
    public static void processTask(String input, TaskType type) throws MissingArgumentException, 
           IOException {
        String description = input;
        switch (type) {
        case TODO:
            // Take string after "todo" word
            description = input.substring(5);
            //list[listCount] = new Todo(description);
            tasks.addTask(new Todo(description));
            break;
        case DEADLINE:
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new MissingArgumentException(CommandType.DEADLINE);
            }
            // Take string between "deadline" and "/by"
            description = input.substring(9, byIndex - 1);
            // Take string after "/by"
            String by = input.substring(byIndex + 4);
            //list[listCount] = new Deadline(description, by);
            tasks.addTask(new Deadline(description, by));
            break;
        case EVENT:
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new MissingArgumentException(CommandType.EVENT);
            }
            // Take string between "event" and "/from"
            description = input.substring(6, fromIndex - 1);
            // Take string between "/from" and "/to"
            String from = input.substring(fromIndex + 6, toIndex - 1);
            // Take string after "/to"
            String to = input.substring(toIndex + 4);
            //list[listCount] = new Event(description, from, to);
            tasks.addTask(new Event(description, from, to));
            break;
        case UNDEFINED:
            // Fallthrough
        default:
            break;
        }
        int taskCount = tasks.getTaskCount();
        Task addedTask = tasks.getTask(taskCount - 1); 
        storage.addToDataFile(addedTask.dataFileInput());
        ui.printAddedTask(addedTask.toString(), taskCount);
    }

    public static void showList() throws EmptyListException {
        int taskCount = tasks.getTaskCount(); 
        if (taskCount == 0) {
            throw new EmptyListException();
        }
        ui.printList(tasks.getAllTasks(), taskCount);
    }

    public static void markTask(String taskNum) throws OutOfListBoundsException, IOException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        Task targetTask = tasks.getTask(taskNumber);
        String oldLine = targetTask.dataFileInput();
        targetTask.setAsDone();
        String newLine = targetTask.dataFileInput();
        storage.modifyDataFile(oldLine, newLine);
        String markedTask = targetTask.toString();
        ui.printMarkedTask(markedTask);
    }

    public static void unmarkTask(String taskNum) throws OutOfListBoundsException, IOException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        Task targetTask = tasks.getTask(taskNumber);
        String oldLine = targetTask.dataFileInput();
        targetTask.setAsUndone();
        String newLine = targetTask.dataFileInput();
        storage.modifyDataFile(oldLine, newLine);
        String unmarkedTask = targetTask.toString();
        ui.printUnmarkedTask(unmarkedTask);
    }
    
    public static void deleteTask(String taskNum) throws OutOfListBoundsException, IOException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        Task deletedTask = tasks.removeTask(taskNumber);
        storage.deleteFromDataFile(deletedTask.dataFileInput());
        ui.printDeletedTask(deletedTask.toString(), tasks.getTaskCount());
    }

    // Read user input for command, throw exception for invalid commands
    public static void executeCommand(String input, String[] instruction) 
            throws MissingCommandException, MissingDescriptionException, EmptyListException,
            OutOfListBoundsException, MissingArgumentException, IOException {
        if (input.equals("bye")) {
            isTerminated = true;
        } else if (input.equals("list")) {
            showList();
        } else if (instruction[0].equals("todo")) {
            if (instruction.length > 1) {
                processTask(input, TaskType.TODO);
            } else {
                throw new MissingDescriptionException(TaskType.TODO);
            }
        } else if (instruction[0].equals("deadline")) {
            if (instruction.length > 1) {
                processTask(input, TaskType.DEADLINE);
            } else {
                throw new MissingDescriptionException(TaskType.DEADLINE);
            }
        } else if (instruction[0].equals("event")) {
            if (instruction.length > 1) {
                processTask(input, TaskType.EVENT);
            } else {
                throw new MissingDescriptionException(TaskType.EVENT);
            }
        } else if (instruction[0].equals("mark")) {
            try {
                markTask(instruction[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingArgumentException(CommandType.MARK);
            }
        } else if (instruction[0].equals("unmark")) {
            try {
                unmarkTask(instruction[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingArgumentException(CommandType.UNMARK);
            }
        } else if (instruction[0].equals("delete")) {
            try {
                deleteTask(instruction[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingArgumentException(CommandType.DELETE);
            }
        } else {
            throw new MissingCommandException();
        }	
    }

    // Method chooses appropriate response for user input based on set pattern
    public static void processInput(String input) {
        String[] instruction = input.split(" ");
        try {
            executeCommand(input, instruction);
        } catch (MissingCommandException e) {
            System.out.println(LINE);
            System.out.println("\tHmmmm, it appears that you didn't give an appropriate command.");
            System.out.println("\tHere's some you can consider:");
            System.out.println("\ttodo, deadline, event, mark, unmark, bye");
            System.out.println(LINE);
        } catch (MissingDescriptionException e) {
            System.out.println(LINE);
            System.out.println("\tPlease provide a description for your " 
                    + e.getTypeString() + " command.");
            System.out.println(LINE);
        } catch (EmptyListException e) {
            System.out.println(LINE);
            System.out.println("\tAh, it seems your list is empty. Why not give it some substance?");
            System.out.println(LINE);
        } catch (OutOfListBoundsException e) {
            System.out.println(LINE);
            System.out.println("\tWoah, that index is out of the bounds of your list.");
            System.out.println("\tAccessing it would have torn a gap in this computer simulation,");
            System.out.println("\tcausing a premature termination, resulting in an incident.");
            System.out.println("\tThat, I cannot allow.");
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("\tPlease input your index as a valid integer.");
            System.out.println(LINE);
        } catch (MissingArgumentException e) {
            System.out.println(LINE);
            System.out.println("\tThere appears to be something wrong with command's arguments.");
            System.out.println(LINE);
        } catch (IOException e) {
            System.out.println(LINE);
            System.out.println("\tMethink something wrong happened to your datafile.");
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        tasks = new TaskList();
        ui = new Ui();
        ui.greet();

        try {
            storage = new Storage(directory);
        } catch (IOException e) {
            System.out.println("Unfortunately I, Ran, have ran into an issue accessing your data files.");
            return;
        } 
        
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("That is strange, I swear I thought your data file exists...");
            return;
        }

        // Take in user input from the terminal
        String input;
        Scanner in = new Scanner(System.in);
        
        // Process user input line by line until terminating command is given
        while(!isTerminated) {
            input = in.nextLine();
            processInput(input);
        }

        ui.bidFarewell();
    }
}
