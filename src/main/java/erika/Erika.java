package erika;

import erika.exception.*;
import erika.filesystem.FileSystem;
import erika.settings.Settings;
import erika.task.Deadline;
import erika.task.Event;
import erika.task.Task;
import erika.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Erika {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private static int markIndex;
    static FileSystem fileSystem = new FileSystem(Settings.FILENAME, Settings.SEPARATOR);

    public static void main(String[] args) {
        printWelcomeMessage();
        initializeFileSystem();

        while (true) {
            if (mainLoop()) break;
        }
    }

    private static void initializeFileSystem() {
        try{
            tasks = fileSystem.readFromFile();
        } catch (FileNotFoundException e){
            fileSystem.printFileNotFoundMessage();
        } catch (FileFormatErrorException e) {
            reinitFileSystem();
        } catch (IOException e) {
            printMessage("IO Error encountered when setting up fileSystem");
        }
    }

    private static void reinitFileSystem() {
        try{
            printMessage(Settings.FILENAME + " is corrupted, re-creating the file");
            fileSystem.writeToFile("",false);
        } catch (IOException _e) {
            printMessage("IO Error encountered when re-creating " + Settings.FILENAME);
        }
    }

    private static boolean mainLoop() {
        try{
            String line = collectUserInput();
            if (line.equals("bye")) {
                printGoodbyeMessage();
                saveChangesToFileSystem();
                return true;
            } else if (line.equals("list")) {
                printList();
            } else if (line.contains("mark")) {
                indexOperation(line, false);
            } else if (line.contains("todo")) {
                addTodo(line);
            } else if (line.contains("deadline")) {
                addDeadline(line);
            } else if (line.contains("event")) {
                addEvent(line);
            } else if (line.contains("delete")) {
                indexOperation(line, true);
            } else {
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            printMessage("Error! Unknown Command, please try again!");
        } catch (FormatErrorException e) {
            printMessage("Error! Invalid Command Format, please try again!");
        } catch (IndexOutOfBoundsException e) {
            printMessage("Error! Task " + markIndex + " is out of bounds!");
        } catch (NumberFormatException e) {
            printMessage("Error! Please input a valid number!");
        } catch (EmptyListException e) {
            printMessage("It seems that there are no tasks! Please consider adding some!");
        } catch (EmptyDescriptionException e) {
            printMessage("Error! Description of " + e.taskType + " cannot be empty!");
        }
        return false;
    }

    private static void saveChangesToFileSystem() {
        try{
            fileSystem.updateFileSystemWithLocalTasks(tasks);
        } catch (IOException e) {
            printMessage("IO Error encountered when attempting to save changes to FS");
        }
    }

    private static void addEvent(String line) throws FormatErrorException, EmptyDescriptionException{
        if(!line.contains("event ")) {
            throw new EmptyDescriptionException("Event");
        }
        int indexOfFrom = line.indexOf("/from ");
        if(line.indexOf(" ") == indexOfFrom - Settings.FROM_REAR_OFFSET) {
            throw new FormatErrorException();
        }
        int indexOfTo = line.indexOf("/to ");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new FormatErrorException();
        }
        Event newEvent = addNewEvent(line, indexOfFrom, indexOfTo);
        printAddedMessage(newEvent);
    }

    private static Event addNewEvent(String line, int indexOfFrom, int indexOfTo) throws EmptyDescriptionException {
        int substringStart = line.indexOf(" ") + Settings.SPACE_OFFSET;
        int substringEnd = indexOfFrom - Settings.FROM_REAR_OFFSET;
        String description = line.substring(substringStart, substringEnd);
        String fromText = line.substring(indexOfFrom + Settings.FROM_LENGTH_OFFSET, indexOfTo - Settings.TO_REAR_OFFSET);
        String toText = line.substring(indexOfTo + Settings.TO_LENGTH_OFFSET);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Event");
        }
        Event newEvent = new Event(description, fromText, toText);
        tasks.add(newEvent);
        try {
            fileSystem.appendTaskToFile(newEvent);
        } catch (IOException e) {
            printMessage("Unable to append Event to FS. IO Error occurred");
        }
        return newEvent;
    }

    private static void addDeadline (String line) throws FormatErrorException, EmptyDescriptionException {
        if (!line.contains("deadline ")) {
            throw new EmptyDescriptionException("Deadline");
        }
        int indexOfBy = line.indexOf("/by ");
        if (line.indexOf(" ") == indexOfBy - Settings.BY_REAR_OFFSET) {
            throw new FormatErrorException();
        }
        if (indexOfBy == -1) {
            throw new FormatErrorException();
        }
        Deadline newDeadline = addNewDeadline(line, indexOfBy);
        printAddedMessage(newDeadline);
    }

    private static Deadline addNewDeadline(String line, int indexOfBy) throws EmptyDescriptionException {
        int substringStart = line.indexOf(" ") + Settings.SPACE_OFFSET;
        int substringEnd = indexOfBy - Settings.BY_REAR_OFFSET;
        String description = line.substring(substringStart, substringEnd);
        String byText = line.substring(indexOfBy + Settings.BY_LENGTH_OFFSET);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Deadline");
        }

        Deadline newDeadline = new Deadline(description, byText);
        tasks.add(newDeadline);
        try {
            fileSystem.appendTaskToFile(newDeadline);
        } catch (IOException e) {
            printMessage("Unable to append Deadline to FS. IO Error occurred");
        }
        return newDeadline;
    }

    private static void addTodo(String line) throws EmptyDescriptionException{
        if (!line.contains("todo ")) {
            throw new EmptyDescriptionException("Todo");
        }
        Todo newTodo = new Todo(line.substring(line.indexOf(" ")+Settings.SPACE_OFFSET));
        tasks.add(newTodo);
        try {
            fileSystem.appendTaskToFile(newTodo);
        } catch (IOException e) {
            printMessage("Unable to append Todo to FS. IO Error occurred");
        }
        printAddedMessage(newTodo);
    }

    private static void indexOperation(String line, boolean isDelete) throws IndexOutOfBoundsException, EmptyDescriptionException{
        if(!isDelete) {
            markEntry(line);
        } else {
            deleteEntry(line);
        }
    }

    private static void deleteEntry(String line) throws EmptyDescriptionException {
        if (!line.contains("delete ")) {
            throw new EmptyDescriptionException("delete");
        }
        markIndex = extractTaskIndex(line);
        if (markIndex <= 0 || markIndex > Task.getTaskArraySize()) {
            throw new IndexOutOfBoundsException();
        }
        printDeletedMessage();
        tasks.remove(markIndex-1);
        Task.decrementTaskArraySize();
        FileSystem.setDirtyBit();
    }

    private static void markEntry(String line) throws EmptyDescriptionException {
        if (!line.contains("mark ")) {
            throw new EmptyDescriptionException("mark");
        }
        markIndex = extractTaskIndex(line);
        if (markIndex <= 0 || markIndex > Task.getTaskArraySize()) {
            throw new IndexOutOfBoundsException();
        }
        if (line.contains("unmark ")) {
            tasks.get(markIndex - 1).setMark(false);
            printUnmarkedMessage();
        } else {
            tasks.get(markIndex - 1).setMark(true);
            printMarkedMessage();
        }
        FileSystem.setDirtyBit();
    }

    private static int extractTaskIndex(String line) throws NumberFormatException{
        String digitString = line.replaceAll("[^0-9-]", "");
        if (!digitString.isEmpty()) {
            return Integer.parseInt(digitString);
        } else {
            throw new NumberFormatException();
        }
    }

    private static void printMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }

    private static void printAddedMessage(Task newTask) {
        String message = "Got it. I've added this task:\n" + "\t  " + newTask + "\n" +
                String.format("\tNow you have %d task%s in the list.", Task.getTaskArraySize(),
                (Task.getTaskArraySize() > 1) ? "s" : "");
        printMessage(message);
    }

    private static void printDeletedMessage() {
        String message = "Nice! I've deleted this task:\n" + "\t" + tasks.get(markIndex-1);
        printMessage(message);
    }
    private static void printMarkedMessage() {
        String message = "Nice! I've marked this task as done:\n" + "\t\t" + tasks.get(markIndex-1);
        printMessage(message);
    }

    private static void printUnmarkedMessage() {
        String message = "Nice! I've marked this task as not done yet:\n" + "\t\t" + tasks.get(markIndex-1);
        printMessage(message);
    }

    private static void printList() throws EmptyListException {
        String message = "";
        if (Task.getTaskArraySize() == 0) {
            throw new EmptyListException();
        }
        message += "Here are the tasks in your list:\n";
        for (int i = 0; i < Task.getTaskArraySize(); i++) {
            message += "\t" + Integer.toString(i + 1) + "." + tasks.get(i);
            if (i < Task.getTaskArraySize() - 1) {
                message += "\n";
            }
        }
        printMessage(message);
    }

    private static void printGoodbyeMessage() {
        printMessage("Bye! Hope to see you again soon!");
    }

    private static String collectUserInput() {
        String line = in.nextLine();
        line = line.trim();
        return line;
    }

    private static void printWelcomeMessage() {
        String logo =
                " _______   ________  ___  ___  __    ________     \n" +
                "|\\  ___ \\ |\\   __  \\|\\  \\|\\  \\|\\  \\ |\\   __  \\    \n" +
                "\\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\/  /|\\ \\  \\|\\  \\   \n" +
                " \\ \\  \\_|/_\\ \\   _  _\\ \\  \\ \\   ___  \\ \\   __  \\  \n" +
                "  \\ \\  \\_|| \\ \\  \\\\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \n" +
                "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\\n" +
                "    \\|_______|\\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|";
        System.out.println("\tHello from\n" + logo + "\n");
        printMessage("Hello! I'm Erika\n \tWhat can I do for you?");
    }
}
