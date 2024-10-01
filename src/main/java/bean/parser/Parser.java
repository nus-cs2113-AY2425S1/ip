package bean.parser;

import bean.Main;
import bean.exceptions.EmptyListException;
import bean.exceptions.InsufficientSpaceException;
import bean.exceptions.InvalidInputException;
import bean.exceptions.TaskNumOutOfBoundsException;
import bean.storage.Storage;
import bean.task.Task;
import bean.tasklist.TaskList;
import bean.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static bean.constants.Constants.INDENT;
import static bean.constants.Constants.MAX_LIST_COUNT;

public class Parser {

    // Return command (taken as first word) from given user input
    public static String extractCommand(String userInput) {
        // Take first word of input as command
        return userInput.split(" ")[0];
    }

    // Extract task number as int from user input for mark, unmark and delete commands
    public static int obtainTaskNum(String userInput) throws TaskNumOutOfBoundsException {
        // Obtain task number by taking second word of input and trim any spaces, then parse as int
        String[] words = userInput.split(" ");
        int taskNum = Integer.parseInt(words[1].trim());
        if (!TaskList.taskNumIsValid(taskNum)) {
            throw new TaskNumOutOfBoundsException();
        }
        return taskNum;
    }

    public static void processUserInput() {
        String userInput;
        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while (!isExit) {
            userInput = in.nextLine();
            String userCommand = extractCommand(userInput);

            try {
                switch (userCommand) {
                case "bye":
                    // To exit
                    isExit = true;
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
