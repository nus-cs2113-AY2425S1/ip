package main;

import java.util.Scanner;
import java.lang.Integer;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import task.*;
import exception.*;

public class Sirius {
    // some commands
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";

    // some regexes
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String SLASH = "/";
    public static final String STATUS_DELIMINATOR = "|";
    public static final String SEPARATOR = "-----------------------------";

    // data members
    private static boolean isExit = true;
    private static boolean isValidToProcess = true;
    private static final ArrayList<Task> list = new ArrayList<>();

    // some methods
    public static void sayHello(){
        System.out.println("""
                -----------------------
                Hello! I'm Sirius!
                What can I do for you?
                -----------------------
                """);
    }
    public static void sayGoodbye(){
        System.out.println("""
                -----------------------
                Bye! Hope to see you soon.
                -----------------------
                """);
        isExit = false;
    }
    public static void markTask(String[] commandPieces, ArrayList<Task> list, boolean isMarked) {
        System.out.println(SEPARATOR);
        try {
            int taskNumber = Integer.parseInt(commandPieces[1]);
            if (isMarked) {
                list.get(taskNumber - 1).setMarked(true);
                System.out.println("Nice! I've marked this task as done:");
            } else {
                list.get(taskNumber - 1).setMarked(false);
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(list.get(taskNumber - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task index is out of bounds! Please enter a valid task index");
        } catch (NumberFormatException e) {
            System.out.println("The task index must be a number! Please enter a valid index number!");
        }
        System.out.println(SEPARATOR);

    }
    public static void addTask(String[] commandPieces, ArrayList<Task> list){
        System.out.println(SEPARATOR);
        String commandPrefix = commandPieces[0];
        String taskName = commandPieces[1];
        switch (commandPrefix) {
            case DEADLINE:
                list.add(new Deadline(taskName, false, commandPieces[2]));
                break;
            case EVENT:
                list.add(new Event(taskName, false, commandPieces[2], commandPieces[3]));
                break;
            case TODO:
                list.add(new Todo(taskName, false));
            }
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(list.size()-1).toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }
    public static void deleteTask(String[] commandPieces, ArrayList<Task> list){
        System.out.println(SEPARATOR);
        try {
            int taskNumber = Integer.parseInt(commandPieces[1]);
            if (taskNumber <= list.size()) {
                System.out.println("Got it. I've removed this task:");
            }
            System.out.println(list.get(taskNumber - 1).toString());
            list.remove(taskNumber-1);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task index is out of bounds! Please enter a valid task index");
        } catch (NumberFormatException e) {
            System.out.println("The task index must be a number! Please enter a valid index number!");
        }
        System.out.println(SEPARATOR);
    }
    public static void listTasks(ArrayList<Task> list){
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(list.get(i).toString());
        }
        System.out.println(SEPARATOR);
    }
    public static String[] splitCommand(String userInput) throws InvalidTaskContentException, IncompleteCommandException{
        // It returns a commandPiece, which contents prefix, taskName, by time for deadline, and from/to time for event.
        String[] slashCommand = userInput.split(SLASH);
        String commandContent = slashCommand[0]; //commandContent = commandPrefix + taskName
        String commandPrefix = commandContent.split(SPACE)[0];
        String taskName = commandContent.replace(commandPrefix, EMPTY).trim();

        String[] commandPieces = {commandPrefix, taskName, EMPTY, EMPTY};
        try {
            switch (commandPrefix) {
                case MARK:
                case UNMARK:
                case DELETE:
                    if (taskName.isEmpty()){
                        isValidToProcess = false;
                        throw new IncompleteCommandException("The task index");
                    }
                    break;
                case TODO:
                     if (taskName.isEmpty()) {
                         isValidToProcess = false;
                         throw new IncompleteCommandException(commandPrefix);
                     }
                     break;
                case DEADLINE:
                     int indexOfBy = userInput.indexOf("/by");
                     if (taskName.isEmpty()) { //list or mark/task empty
                         isValidToProcess = false;
                         throw new IncompleteCommandException(commandPrefix);
                     }
                     else if (indexOfBy == -1) {
                         isValidToProcess = false;
                         throw new InvalidTaskContentException("You should declare '/by' for deadline");
                     }
                     else {
                         commandPieces[2] = slashCommand[1].replace("by", EMPTY).trim();
                         return commandPieces;
                     }
                case EVENT:
                    int indexOfFrom = userInput.indexOf("/from");
                    int indexOfTo = userInput.indexOf("/to");
                    if (taskName.isEmpty()) { //list or mark/task empty
                        isValidToProcess = false;
                        throw new IncompleteCommandException(commandPrefix);
                    }
                    else if (indexOfFrom == -1 || indexOfTo == -1) {
                        isValidToProcess = false;
                        throw new InvalidTaskContentException("You should declare '/from' and '/to' for event");
                    }
                    else {
                        commandPieces[2] = slashCommand[1].replace("from", EMPTY).trim();
                        commandPieces[3] = slashCommand[2].replace("to", EMPTY).trim();
                        return commandPieces;
                    }
                }
        } catch (InvalidTaskContentException | IncompleteCommandException e) {
            System.out.println(SEPARATOR);
            System.out.println(e.getMessage());
            System.out.println(SEPARATOR);
        }
        return commandPieces;
    }
    public static void saveTaskList(ArrayList<Task> list) {
        try {
            // If the directory DNE, create.
            File directory = new File("./data");
            if (!directory.exists()) {
                if (directory.mkdirs()){
                    System.out.println("Directory called \"data\" is created!");
                }
            }
            // If the file DNE, create and write.
            File file = new File(directory, "Sirius.txt");
            if (!file.exists()) {
                if (file.createNewFile()){
                    System.out.println("File created to store your task list!");
                }
            }
            FileWriter writer = new FileWriter(file);  // override the previous contents in txt file.
            for (Task task : list) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks.");
        }
    }

    public static void main(String[] args) {
        sayHello();
        Scanner scanner = new Scanner(System.in);
        while (isExit) {
            isValidToProcess = true; // reset
            String userInput = scanner.nextLine();
            String[] commandPieces = splitCommand(userInput);
            String commandPrefix = commandPieces[0];
            try{
                switch (commandPrefix) {
                    case BYE:
                        sayGoodbye();
                        break;
                    case LIST:
                        listTasks(list);
                        break;
                    case MARK:
                        if (isValidToProcess){
                            markTask(commandPieces, list, true);
                        }
                        saveTaskList(list);
                        break;
                    case UNMARK:
                        if (isValidToProcess) {
                            markTask(commandPieces, list, false);
                        }
                        saveTaskList(list);
                        break;
                    case DELETE:
                        if (isValidToProcess) {
                            deleteTask(commandPieces, list);
                        }
                        saveTaskList(list);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        if (isValidToProcess) {
                            addTask(commandPieces, list);
                        }
                        saveTaskList(list);
                        break;
                    default:
                        throw new IllegalCommandException("I don't understand it. Please enter an illegal command!");
                }
            } catch(IllegalCommandException e){
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }
}
