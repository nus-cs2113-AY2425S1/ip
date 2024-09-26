package main.java;

import main.java.Ui;

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
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Ran {
    private static boolean isTerminated = false; 
    private static int listCount = 0;
    private static final int MAX_TASK_LIST_SIZE = 100;
    private static ArrayList<Task> list = new ArrayList<>();
    private static final String LINE = "\t____________________________________________________________";
    private static String filePath = "./data/ran.txt";

    public static void addToDataFile(String input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(input + System.lineSeparator());
        fw.close();
    }

    public static void modifyDataFile(String oldLine, String newLine) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNext()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String dataFileContent = buffer.toString();
        sc.close();
        dataFileContent = dataFileContent.replaceAll(oldLine, newLine);
        FileWriter fw = new FileWriter(filePath);
        fw.write(dataFileContent);
        fw.close();
    }
    
    public static void deleteFromDataFile(String line) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNext()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String dataFileContent = buffer.toString();
        sc.close();
        dataFileContent = dataFileContent.replaceAll(line + System.lineSeparator() , "");
        FileWriter fw = new FileWriter(filePath);
        fw.write(dataFileContent);
        fw.close();
    }

    // Process task based on its type into relevant fields to be added
    public static void processTask(String input, TaskType type) throws MissingArgumentException, IOException {
        String description = input;
        switch (type) {
        case TODO:
            // Take string after "todo" word
            description = input.substring(5);
            //list[listCount] = new Todo(description);
            list.add(new Todo(description));
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
            list.add(new Deadline(description, by));
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
            list.add(new Event(description, from, to));
            break;
        case UNDEFINED:
            // Fallthrough
        default:
            break;
        }
        addToDataFile(list.get(listCount).dataFileInput());
        listCount++;
        String addedTask = list.get(listCount - 1).toString(); 
        Ui.printAddedTask(addedTask, listCount);
    }

    public static void showList() throws EmptyListException {
        if (listCount == 0) {
            throw new EmptyListException();
        }
        Ui.printList(list, listCount);
    }

    public static void markTask(String taskNum) throws OutOfListBoundsException, IOException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        String oldLine = list.get(taskNumber).dataFileInput();
        list.get(taskNumber).setAsDone();
        String newLine = list.get(taskNumber).dataFileInput();
        modifyDataFile(oldLine, newLine);
        String markedTask = list.get(taskNumber).toString();
        Ui.printMarkedTask(markedTask);
    }

    public static void unmarkTask(String taskNum) throws OutOfListBoundsException, IOException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        String oldLine = list.get(taskNumber).dataFileInput();
        list.get(taskNumber).setAsUndone();
        String newLine = list.get(taskNumber).dataFileInput();
        modifyDataFile(oldLine, newLine);
        String unmarkedTask = list.get(taskNumber).toString();
        Ui.printUnmarkedTask(unmarkedTask);
    }
    
    public static void deleteTask(String taskNum) throws OutOfListBoundsException, IOException {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        if (taskNumber >= listCount || taskNumber < 0) {
            throw new OutOfListBoundsException();
        }
        String deletedTask = list.get(taskNumber).toString();
        deleteFromDataFile(list.get(taskNumber).dataFileInput());
        list.remove(taskNumber);
        listCount--;
        Ui.printDeletedTask(deletedTask, listCount);
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

    public static void loadFile() throws IOException {
        // Check for existence of directory
        String directory = "./data";
        File dir = new File(directory);
        // If directory does not yet exist, create it
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        // Check for existence of data file
        File f = new File(filePath);
        // If file does not yet exist, create it
        if (!f.exists()) {
            f.createNewFile();
        }
    }
    
    public static void loadTask(String task) {
        String[] taskInstruction = task.split(", ");
        boolean isDone = taskInstruction[1].equals("1");
        if (taskInstruction[0].equals("T")) {
            list.add(new Todo(isDone, taskInstruction[2]));
            listCount++;
        } else if (taskInstruction[0].equals("D")) {
            list.add(new Deadline(isDone, taskInstruction[2], taskInstruction[3]));
            listCount++;
        } else if (taskInstruction[0].equals("E")) {
            list.add(new Event(isDone, taskInstruction[2], taskInstruction[3], taskInstruction[4]));
            listCount++;
        }
    }

    public static void loadData(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            loadTask(s.nextLine());
        }
    }

    public static void main(String[] args) {
        Ui.greet();
        
        // Check for data file, create directory and data file if necessary
        try {
            loadFile();
        } catch (IOException e) {
            System.out.println("Unfortunately I, Ran, have ran into an issue accessing your data files.");
        } 
        
        File f = new File (filePath);

        // Load data from data file
        try {
            loadData(f);
        } catch (FileNotFoundException e) {
            System.out.println("That is strange, I swear I thought your data file exists...");
        }

        // Take in user input from the terminal
        String input;
        Scanner in = new Scanner(System.in);
        
        // Process user input line by line until terminating command is given
        while(!isTerminated) {
            input = in.nextLine();
            processInput(input);
        }

        Ui.bidFarewell();
    }
}
