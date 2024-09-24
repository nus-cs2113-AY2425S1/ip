package org.ajay.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.ajay.exceptions.EmptyArgumentException;
import org.ajay.exceptions.Error;
import org.ajay.exceptions.IllegalCommandException;
import org.ajay.exceptions.InvalidCommandFormatException;
import org.ajay.task.Deadline;
import org.ajay.task.Event;
import org.ajay.task.Task;
import org.ajay.task.TaskList;
import org.ajay.task.Todo;
import org.ajay.ui.Ui;

public class Parser {

    public static String command; // Variable to store the command
    public static String task; // Variable to store the task

    /**
     * Splits the command and task from the input.
     *
     * @param lineBufferString
     */
    public static void splitCommandAndTask(String lineBufferString) throws EmptyArgumentException {
        if (lineBufferString.isEmpty()) {
            throw new EmptyArgumentException(Error.EMPTY_ARG.toString());
        }

        if (lineBufferString.contains(" ")) {
            command = lineBufferString.split(" ")[0];
            task = lineBufferString.substring(command.length() + 1);
        } else {
            command = lineBufferString;
            task = null;
        }
    }


        /**
     * Reads the input from the user and processes it.
     *
     * @param in
     * @param lineBufferString
     */
    public static void readInput(Scanner in, String lineBufferString)  {
        try {
            Ui.printPrompt(); // Print the prompt to the console
            lineBufferString = in.nextLine();
            splitCommandAndTask(lineBufferString);

            switch (command) {
            case Ui.EXIT_STRING: // Exit the program
                in.close();
                Ui.printGoodbyeMsgs();
                break;
            case Ui.EXIT_STRING_ALT: // Habit of typing exit to exit the program
                in.close();
                Ui.printGoodbyeMsgs();
                break;
            case Task.LIST_COMMAND_STRING: // List all the tasks
                Ui.printBreakLine();
                TaskList.printAllTasks();
                Ui.printBreakLine();
                break;
            case Todo.COMMAND_STRING: // Add a todo task
                // taskList[Task.getNumberOfTasks()] = new Todo(task);
                TaskList.addTask(new Todo(task));
                Ui.printBreakLine();

                Storage.saveTaskList(TaskList.taskList);
                break;
            case Deadline.COMMAND_STRING: // Add a deadline task
                // taskList[Task.getNumberOfTasks()] = new Deadline(task);
                TaskList.addTask(new Deadline(task));
                Ui.printBreakLine();

                Storage.saveTaskList(TaskList.taskList);
                break;
            case Event.COMMAND_STRING: // Add an event task
                // taskList[Task.getNumberOfTasks()] = new Event(task);
                TaskList.addTask(new Event(task));
                Ui.printBreakLine();

                Storage.saveTaskList(TaskList.taskList);
                break;
            case Task.MARK_COMMAND_STRING: // Mark the task as done
                TaskList.markAsDone(task);

                Storage.saveTaskList(TaskList.taskList);
                break;
            case Task.UNMARK_COMMAND_STRING: // Mark the task as undone
                TaskList.markAsUndone(task);

                Storage.saveTaskList(TaskList.taskList);
                break;
            case Task.DELETE_COMMAND_STRING: // Delete the task
                int taskNumberDelete = Integer.parseInt(task); // Get the task number
                TaskList.deleteTask(TaskList.taskList, taskNumberDelete); // Delete the task

                Storage.saveTaskList(TaskList.taskList);
                break;
            default:
                throw new IllegalCommandException(Error.ILLEGAL_COMMAND.toString());
            }

            readInput(in, lineBufferString); // Recursively call the function to read the next input
        } catch (EmptyArgumentException | IllegalCommandException | InvalidCommandFormatException e) {
            Ui.printBreakLine();
            Ui.printExceptions(e.getMessage());
            Ui.printBreakLine();
            readInput(in, lineBufferString);
        } catch (NoSuchElementException e) {
            // FIXME: Facing unexpected NoSuchElementException error
        }

    }
}
