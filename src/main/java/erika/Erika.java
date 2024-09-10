package erika;

import erika.exception.EmptyDescriptionException;
import erika.exception.EmptyListException;
import erika.exception.FormatErrorException;
import erika.exception.UnknownCommandException;
import erika.task.Deadline;
import erika.task.Event;
import erika.task.Task;
import erika.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Erika {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private static int markIndex;

    public static void main(String[] args) {
        printWelcomeMessage();
        while (true) {
            if (mainLoop()) break;
        }
    }

    private static boolean mainLoop() {
        try{
            String line = collectUserInput();
            if (line.equals("bye")) {
                printGoodbyeMessage();
                return true;
            } else if (line.equals("list")) {
                printList();
            } else if (line.contains("mark")) {
                handleMark(line);
            } else if (line.contains("todo")) {
                addTodo(line);
            } else if (line.contains("deadline")) {
                addDeadline(line);
            } else if (line.contains("event")) {
                addEvent(line);
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

    private static void addEvent(String line) throws FormatErrorException, EmptyDescriptionException{
        if(!line.contains("event ")) {
            throw new EmptyDescriptionException("Event");
        }
        int indexOfFrom = line.indexOf("/from ");
        if(line.indexOf(" ") == indexOfFrom - 1) {
            throw new FormatErrorException();
        }
        int indexOfTo = line.indexOf("/to ");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new FormatErrorException();
        }
        if (line.substring(line.indexOf(" ") + 1, indexOfFrom - 1).trim().isEmpty()) {
            throw new EmptyDescriptionException("Event");
        }
        Event newEvent = new Event(line.substring(line.indexOf(" ") + 1, indexOfFrom - 1),
                line.substring(indexOfFrom + 6, indexOfTo - 1), line.substring(indexOfTo + 4));
        tasks.add(newEvent);
        printAddedMessage(newEvent);
    }

    private static void addDeadline (String line) throws FormatErrorException, EmptyDescriptionException {
        if (!line.contains("deadline ")) {
            throw new EmptyDescriptionException("Deadline");
        }
        int indexOfBy = line.indexOf("/by ");
        if (line.substring(line.indexOf(" ") + 1, indexOfBy - 1).trim().isEmpty()) {
            throw new EmptyDescriptionException("Deadline");
        }
        if (indexOfBy == -1) {
            throw new FormatErrorException();
        }
        if (line.indexOf(" ") == indexOfBy - 1) {
            throw new FormatErrorException();
        }
        Deadline newDeadline = new Deadline(line.substring(line.indexOf(" ") + 1, indexOfBy - 1),
                line.substring(indexOfBy + 4));
        tasks.add(newDeadline);
        printAddedMessage(newDeadline);
    }

    private static void addTodo(String line) throws EmptyDescriptionException{
        if (!line.contains("todo ")) {
            throw new EmptyDescriptionException("Todo");
        }
        Todo newTodo = new Todo(line.substring(line.indexOf(" ")+1));
        tasks.add(newTodo);
        printAddedMessage(newTodo);
    }

    private static void handleMark(String line) throws IndexOutOfBoundsException, EmptyDescriptionException{
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

    private static void printMarkedMessage() {
        String message = "Nice! I've marked this task as done:\n" + "\t[X] " +
                tasks.get(markIndex-1).getDescription();
        printMessage(message);
    }

    private static void printUnmarkedMessage() {
        String message = "Nice! I've marked this task as not done yet:\n" + "\t[ ] " +
                tasks.get(markIndex-1).getDescription();
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
