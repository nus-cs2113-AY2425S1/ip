package bean.parser;

import bean.Main;
import bean.exceptions.EmptyListException;
import bean.exceptions.InsufficientSpaceException;
import bean.exceptions.InvalidInputException;
import bean.exceptions.TaskNumOutOfBoundsException;
import bean.tasklist.TaskList;
import bean.ui.Ui;

import static bean.constants.Constants.INDENT;
import static bean.constants.Constants.MAX_LIST_COUNT;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles user input parsing and command execution.
 * Extracts commands and task numbers from user input.
 * Delegates commands to appropriate handlers in the TaskList class.
 */
public class Parser {

    /**
     * Extracts the first word from the user input and returns it as the command.
     *
     * @param userInput The user's input string.
     * @return The first word of the input (the command).
     */
    public static String extractCommand(String userInput) {
        // Take first word of input as command
        return userInput.split(" ")[0];
    }

    /**
     * Extracts the task number as an integer from the user input for commands like mark, unmark, and delete.
     *
     * @param userInput The user's input string.
     * @return The task number (1-based) extracted from the input.
     * @throws TaskNumOutOfBoundsException if the extracted task number is invalid.
     */
    public static int obtainTaskNum(String userInput) throws TaskNumOutOfBoundsException {
        // Obtain task number by taking second word of input and trim any spaces, then parse as int
        String[] words = userInput.split(" ");
        int taskNum = Integer.parseInt(words[1].trim());
        if (!TaskList.taskNumIsValid(taskNum)) {
            throw new TaskNumOutOfBoundsException();
        }
        return taskNum;
    }

    /**
     * Extracts the keyword as a String from the user input for find command.
     *
     * @param userInput The user's input string.
     * @return The keyword extracted from input.
     */
    public static String obtainKeyword(String userInput) {
        String[] words = userInput.split(" ");
        return words[1].trim();
    }

    /**
     * Processes user input in a loop until the user exits.
     * Handles different commands like "list", "mark", "add", "delete", etc.
     * Catches and displays appropriate error messages for invalid input, empty list, invalid task number, insufficient space, and I/O exceptions.
     */
    public static void processUserInput() {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine();
            String userCommand = extractCommand(userInput);

            try {
                switch (userCommand) {
                case "bye":
                    // To exit
                    return;

                case "list":
                    TaskList.printTasks();
                    break;

                case "mark":
                    TaskList.markTaskAsDone(obtainTaskNum(userInput));
                    break;

                case "unmark":
                    TaskList.unmarkTaskAsDone(obtainTaskNum(userInput));
                    break;

                case "todo":
                    TaskList.addTask(userInput, Main.TaskType.TODO);
                    break;

                case "deadline":
                    TaskList.addTask(userInput, Main.TaskType.DEADLINE);
                    break;

                case "event":
                    TaskList.addTask(userInput, Main.TaskType.EVENT);
                    break;

                case "delete":
                    TaskList.deleteTask(obtainTaskNum(userInput));
                    break;

                case "find":
                    TaskList.getTasksByKeyword(obtainKeyword(userInput));
                    break;

                default:
                    throw new InvalidInputException();
                }

            } catch (InvalidInputException e) {
                Ui.printInvalidInputMessage();

            } catch (EmptyListException e) {
                Ui.printFormattedReply(INDENT + "Nothing in your to do list yet!");

            } catch (TaskNumOutOfBoundsException e) {
                Ui.printFormattedReply(INDENT + "Please enter a valid task number!\n" +
                        INDENT + "You currently have " + TaskList.getTasks().size() + " tasks.");

            } catch (InsufficientSpaceException e) {
                Ui.printFormattedReply(INDENT + "Sorry, you have reached the maximum list size of " + MAX_LIST_COUNT);

            } catch (IOException e) {
                Ui.printFormattedReply("Something went wrong! " + e.getMessage());
            }
        }
    }
}
