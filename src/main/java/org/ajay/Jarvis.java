package org.ajay;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.ajay.Ui.Prompt;
import org.ajay.exceptions.EmptyArgumentException;
import org.ajay.exceptions.Error;
import org.ajay.exceptions.IllegalArgumentException;
import org.ajay.exceptions.IllegalCommandException;
import org.ajay.exceptions.InvalidCommandFormatException;
import org.ajay.task.Deadline;
import org.ajay.task.Event;
import org.ajay.task.Task;
import org.ajay.task.Todo;
import org.ajay.utils.Storage;

public class Jarvis {
    // Constants
    public static final String chatBotName = "Jarvis"; // Name of the chatbot

    private static ArrayList<Task> taskList = new ArrayList<>(); // ArrayList to store tasks


    /**
     * Reads the input from the user and processes it.
     *
     * @param in
     * @param lineBufferString
     */
    public static void readInput(Scanner in, String lineBufferString) {
        try {
            Prompt.printPrompt(); // Print the prompt to the console
            lineBufferString = in.nextLine();
            Prompt.splitCommandAndTask(lineBufferString);

            switch (Prompt.command) {
            case Prompt.EXIT_STRING: // Exit the program
                in.close();
                Prompt.printGoodbyeMsgs();
                break;
            case Prompt.EXIT_STRING_ALT: // Habit of typing exit to exit the program
                in.close();
                Prompt.printGoodbyeMsgs();
                break;
            case Task.LIST_COMMAND_STRING: // List all the tasks
                Prompt.printBreakLine();
                Task.printAllTasks(taskList);
                Prompt.printBreakLine();
                break;
            case Todo.COMMAND_STRING: // Add a todo task
                // taskList[Task.getNumberOfTasks()] = new Todo(task);
                taskList.add(new Todo(Prompt.task));
                Prompt.printBreakLine();

                Storage.saveTaskList(taskList);
                break;
            case Deadline.COMMAND_STRING: // Add a deadline task
                // taskList[Task.getNumberOfTasks()] = new Deadline(task);
                taskList.add(new Deadline(Prompt.task));
                Prompt.printBreakLine();

                Storage.saveTaskList(taskList);
                break;
            case Event.COMMAND_STRING: // Add an event task
                // taskList[Task.getNumberOfTasks()] = new Event(task);
                taskList.add(new Event(Prompt.task));
                Prompt.printBreakLine();

                Storage.saveTaskList(taskList);
                break;
            case Task.MARK_COMMAND_STRING: // Mark the task as done
                int taskNumberMark = Integer.parseInt(Prompt.task); // Get the task number
                Task.markAsDone(taskList, taskNumberMark); // Mark the task as done

                Storage.saveTaskList(taskList);
                break;
            case Task.UNMARK_COMMAND_STRING: // Mark the task as undone
                int taskNumberUnmark = Integer.parseInt(Prompt.task); // Get the task number
                Task.markAsUndone(taskList, taskNumberUnmark); // Mark the task as undone

                Storage.saveTaskList(taskList);
                break;
            case Task.DELETE_COMMAND_STRING: // Delete the task
                int taskNumberDelete = Integer.parseInt(Prompt.task); // Get the task number
                Task.deleteTask(taskList, taskNumberDelete); // Delete the task

                Storage.saveTaskList(taskList);
                break;
            default:
                throw new IllegalCommandException(Error.ILLEGAL_COMMAND.toString());
            }

            readInput(in, lineBufferString); // Recursively call the function to read the next input
        } catch (EmptyArgumentException | IllegalCommandException | InvalidCommandFormatException |
                 IllegalArgumentException e) {
            Prompt.printBreakLine();
            System.out.println(e);
            Prompt.printBreakLine();
            readInput(in, lineBufferString);
        } catch (NoSuchElementException e) {
            // FIXME: Facing unexpected NoSuchElementException error
        }

    }

    public static void main(String[] args) {
        String lineBufferString = ""; // Buffer to store the input from the user
        Scanner in = new Scanner(System.in); // Scanner object to read input from the user

        Prompt.printLogo();
        Prompt.printGreetingMsgs();

        try {
            Storage.loadTaskList(taskList);
        } catch (EmptyArgumentException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        readInput(in, lineBufferString); // Read the input from the user
    }
}
