package bitwise.utils;

import bitwise.chat.Bitwise;
import bitwise.chat.Status;
import bitwise.constants.Commands;
import bitwise.constants.Constants;
import bitwise.constants.Messages;
import bitwise.exceptions.InvalidCommandException;
import bitwise.exceptions.InvalidFormatException;
import bitwise.tasks.Deadline;
import bitwise.tasks.Event;
import bitwise.tasks.Task;
import bitwise.tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    public static Status handleInput(String userInput, ArrayList<Task> tasksList, int numberOfTasks) {
        if (userInput.equalsIgnoreCase(Commands.COMMAND_END)) {
            return Status.EXIT;
        }
        OutputManager.printLineBreak();
        if (userInput.equalsIgnoreCase(Commands.COMMAND_LIST)) {
            OutputManager.printTasksList(tasksList, numberOfTasks);
        } else if (userInput.startsWith(Commands.COMMAND_UNMARK)) {
            try {
                int taskNumber = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
                markCompletionStatus(taskNumber, false, tasksList, numberOfTasks);
            } catch (NumberFormatException e) {
                throw new InvalidFormatException(userInput + "\n" + Constants.DESCRIPTION_COMMAND_UNMARK);
            }
        } else if (userInput.startsWith(Commands.COMMAND_MARK)) {
            try {
                int taskNumber = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
                markCompletionStatus(taskNumber, true, tasksList, numberOfTasks);
            } catch (NumberFormatException e) {
                throw new InvalidFormatException(userInput + "\n" + Constants.DESCRIPTION_COMMAND_MARK);
            }
        } else if (userInput.startsWith(Commands.COMMAND_DELETE)) {
            try {
                int taskNumber = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
                deleteTask(taskNumber, tasksList, numberOfTasks);
            } catch (IndexOutOfBoundsException e) {
                OutputManager.printMessage("Invalid task number: Current list size is " + numberOfTasks);
                return Status.RUNNING;
            }
        }else {
            addToList(userInput, tasksList, numberOfTasks);
        }
        OutputManager.printLineBreak();
        return Status.RUNNING;
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void addToList(String userInput, ArrayList<Task> tasksList, int numberOfTasks) {
        Task newTask;
        String description;
        if (userInput.startsWith(Commands.COMMAND_TODO)) {
            description = userInput.substring(userInput.indexOf(" ") + 1);
            if (description.isBlank() || !userInput.contains(" ")) {
                throw new InvalidFormatException(userInput + "\n" + Constants.DESCRIPTION_COMMAND_TODO);
            }
            newTask = new Todo(description);
        } else if (userInput.startsWith(Commands.COMMAND_DEADLINE)) {
            try {
                description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(Commands.COMMAND_INFIX_BY));
                String deadline = userInput.substring(userInput.indexOf(Commands.COMMAND_INFIX_BY) + 4);
                if (description.isBlank() || deadline.isBlank() || !userInput.contains(Commands.COMMAND_INFIX_BY)) {
                    throw new InvalidFormatException(userInput + "\n" + Constants.DESCRIPTION_COMMAND_DEADLINE);
                }
                newTask = new Deadline(description, deadline);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidFormatException(userInput + "\n" + Constants.DESCRIPTION_COMMAND_DEADLINE);
            }
        } else if (userInput.startsWith(Commands.COMMAND_EVENT)) {
            try {
                description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(Commands.COMMAND_INFIX_FROM));
                String eventFrom = userInput.substring(userInput.indexOf(Commands.COMMAND_INFIX_FROM) + 6, userInput.indexOf(Commands.COMMAND_INFIX_TO));
                String eventTo = userInput.substring(userInput.indexOf(Commands.COMMAND_INFIX_TO) + 4);
                if (description.isBlank() || eventFrom.isBlank() || eventTo.isBlank() || !userInput.contains(Commands.COMMAND_INFIX_FROM) || !userInput.contains(Commands.COMMAND_INFIX_TO)) {
                    throw new InvalidFormatException(userInput + "\n" + Constants.DESCRIPTION_COMMAND_DEADLINE);
                }
                newTask = new Event(description, eventFrom, eventTo);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidFormatException(userInput + "\n" + Constants.DESCRIPTION_COMMAND_EVENT);
            }
        } else {
            throw new InvalidCommandException(userInput);
        }
        tasksList.add(newTask);
        numberOfTasks++;
        Bitwise.setNumberOfTasks(numberOfTasks);
        try {
            FileManager.saveTask(newTask);
        } catch (IOException e) {
            System.out.println("Error saving task list");
            return;
        }
        OutputManager.printMessageAddedTask(newTask.toString());
        OutputManager.printNumberOfTasks(numberOfTasks);
    }

    public static void markCompletionStatus(int taskNumber, boolean isCompleted, ArrayList<Task> tasksList, int numberOfTasks) {
        int taskIndex = taskNumber - 1;
        try {
            tasksList.get(taskIndex).markCompletionStatus(isCompleted);
        } catch (IndexOutOfBoundsException e) {
            OutputManager.printMessage("Invalid task number: Current list size is " + numberOfTasks);
            return;
        }
        try {
            FileManager.saveTasksList(tasksList, numberOfTasks);
        } catch (IOException e) {
            System.out.println("Error saving task list");
            return;
        }
        String message = isCompleted ? Messages.MESSAGE_MARKED : Messages.MESSAGE_UNMARKED;
        OutputManager.printMessage(message);
        OutputManager.printTasksList(tasksList, numberOfTasks);
    }

    public static void deleteTask(int taskNumber, ArrayList<Task> tasksList, int numberOfTasks) {
        int taskIndex = taskNumber - 1;
        Task deletedTask = tasksList.remove(taskIndex);
        numberOfTasks--;
        Bitwise.setNumberOfTasks(numberOfTasks);
        try {
            FileManager.saveTasksList(tasksList, numberOfTasks);
        } catch (IOException e) {
            System.out.println("Error saving task list");
            return;
        }
        OutputManager.printMessageDeletedTask(deletedTask.toString());
        OutputManager.printNumberOfTasks(numberOfTasks);
    }
}
