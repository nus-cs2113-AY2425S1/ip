package org.ajay.parser;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.ajay.commands.Command;
import org.ajay.commands.DeadlineCommand;
import org.ajay.commands.DeleteCommand;
import org.ajay.commands.EventCommand;
import org.ajay.commands.ExitCommand;
import org.ajay.commands.HelpCommand;
import org.ajay.commands.ListCommand;
import org.ajay.commands.MarkCommand;
import org.ajay.commands.TodoCommand;
import org.ajay.commands.UnmarkCommand;
import org.ajay.common.Constants;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.Error;
import org.ajay.ui.TextUi;

public class Parser {

    public static String command; // Variable to store the command
    public static String task; // Variable to store the task

    // public static String lineBufferString = ""; // Buffer to store the input from
    // the user

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
    public static void readInput(Scanner in) {

        try {
            TextUi.printPrompt(); // Print the prompt to the console
            String lineBufferString = in.nextLine();
            splitCommandAndTask(lineBufferString);

            // switch (command) {
            // case Ui.EXIT_STRING: // Exit the program
            // in.close();
            // Ui.printGoodbyeMsgs();
            // break;
            // case Ui.EXIT_STRING_ALT: // Habit of typing exit to exit the program
            // in.close();
            // Ui.printGoodbyeMsgs();
            // break;
            // case Task.LIST_COMMAND_STRING: // List all the tasks
            // Ui.printBreakLine();
            // TaskList.printAllTasks();
            // Ui.printBreakLine();
            // break;
            // case Todo.COMMAND_STRING: // Add a todo task
            // // taskList[Task.getNumberOfTasks()] = new Todo(task);
            // TaskList.addTask(new Todo(task));
            // Ui.printBreakLine();

            // Storage.saveTaskList(TaskList.taskList);
            // break;
            // case Deadline.COMMAND_STRING: // Add a deadline task
            // // taskList[Task.getNumberOfTasks()] = new Deadline(task);
            // TaskList.addTask(new Deadline(task));
            // Ui.printBreakLine();

            // Storage.saveTaskList(TaskList.taskList);
            // break;
            // case Event.COMMAND_STRING: // Add an event task
            // // taskList[Task.getNumberOfTasks()] = new Event(task);
            // TaskList.addTask(new Event(task));
            // Ui.printBreakLine();

            // Storage.saveTaskList(TaskList.taskList);
            // break;
            // case Task.MARK_COMMAND_STRING: // Mark the task as done
            // TaskList.markAsDone(task);

            // Storage.saveTaskList(TaskList.taskList);
            // break;
            // case Task.UNMARK_COMMAND_STRING: // Mark the task as undone
            // TaskList.markAsUndone(task);

            // Storage.saveTaskList(TaskList.taskList);
            // break;
            // case Task.DELETE_COMMAND_STRING: // Delete the task
            // int taskNumberDelete = Integer.parseInt(task); // Get the task number
            // TaskList.deleteTask(TaskList.taskList, taskNumberDelete); // Delete the task

            // Storage.saveTaskList(TaskList.taskList);
            // break;
            // default:
            // throw new IllegalCommandException(Error.ILLEGAL_COMMAND.toString());
            // }

            // readInput(); // Recursively call the function to read the next input
        } catch (EmptyArgumentException e) {
            TextUi.printBreakLine();
            TextUi.printExceptions(e.getMessage());
            TextUi.printBreakLine();
        }
        // catch (InvalidCommandFormatException e) {
        // // TODO: Update me
        // } catch (IllegalCommandException e) {
        // // TODO: Update me
        // }
        catch (NoSuchElementException e) {
            // FIXME: Facing unexpected NoSuchElementException error
        }

    }

    public static Command parseCommand(String command, String task) {
        switch (command) {
            case ExitCommand.COMMAND_WORD: // Exit the program
                return new ExitCommand();
            case Constants.EXIT_COMMAND_ALT: // Habit of typing exit to exit the program
                return new ExitCommand();
            case ListCommand.COMMAND_WORD: // List all the tasks
                return new ListCommand();
            case TodoCommand.COMMAND_WORD: // Add a todo task
                return new TodoCommand();
            case DeadlineCommand.COMMAND_WORD: // Add a deadline task
                return new DeadlineCommand();
            case EventCommand.COMMAND_WORD: // Add an event task
                return new EventCommand();
            case MarkCommand.COMMAND_WORD: // Mark the task as done
                return new MarkCommand();
            case UnmarkCommand.COMMAND_WORD: // Mark the task as undone
                return new UnmarkCommand();
            case DeleteCommand.COMMAND_WORD: // Delete the task
                return new DeleteCommand();
            default:
                return new HelpCommand();
        }

    }
}
