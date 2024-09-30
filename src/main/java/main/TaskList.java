package main;

import exception.EmptyDescriptionException;
import task.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages the list of tasks. Provides functionality to add, mark, unmark, delete,
 * and print tasks. Also provides formatted task output.
 */
public class TaskList {

    private int numItems;
    private Ui ui;
    private Task[] itemList = new Task[0];
    ArrayList<Task> itemArrayList = new ArrayList<>(Arrays.asList(itemList));

    /**
     * Constructs an empty task list with no items.
     */
    public TaskList(Ui ui) {
        this.numItems = 0;
        this.ui = ui;
    }


    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumItems() {
        return itemArrayList.size();
    }

    /**
     * Adds a new task to the list based on the user input.
     * It checks whether the task is an event, deadline, or todo,
     * and adds it accordingly. If the task is invalid, an error message is printed.
     *
     * @param line The user input containing task information.
     */
    public void addItem(String line) {
        if (Parser.isValidEvent(line)) {
            List.addEvent(itemArrayList, ui, line);
        } else if (Parser.isValidDeadline(line)) {
            List.addDeadline(itemArrayList, ui, line);
        } else if (Parser.isTodo(line)) {
            List.addTodo(itemArrayList, ui, line);
        } else {
            ui.printInvalidTaskMessage();
        }
    }

    /**
     * Marks a task in the list as done based on the user input.
     * If the task number is invalid or out of range, an error message is printed.
     *
     * @param line The user input containing the task number to mark.
     */
    public void markItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(5));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                ui.printInputIndexOutOfRangeMessage();
            } else {
                List.markListItemAsDone(itemArrayList, itemNum);
                ui.printTaskMarkedMessage(itemArrayList, itemNum);
            }
        } catch (NumberFormatException e) {
            ui.printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            ui.printUnknownErrorMessage();
        }
    }

    /**
     * Unmarks a task in the list as not done based on the user input.
     * If the task number is invalid or out of range, an error message is printed.
     *
     * @param line The user input containing the task number to unmark.
     */
    public void unmarkItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                ui.printInputIndexOutOfRangeMessage();
            } else {
                List.markListItemAsUnDone(itemArrayList, itemNum);
                ui.printTaskUnmarkedMessage(itemArrayList, itemNum);
            }
        } catch (NumberFormatException e) {
            ui.printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            ui.printUnknownErrorMessage();
        }
    }

    /**
     * Deletes a task from the list based on the user input.
     * If the task number is invalid or out of range, an error message is printed.
     *
     * @param line The user input containing the task number to delete.
     */
    public void deleteItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                ui.printInputIndexOutOfRangeMessage();
            } else {
                Task deletedTask = itemArrayList.get(itemNum - 1);
                List.deleteListItem(itemArrayList, itemNum);
                ui.printTaskDeletedMessage(itemArrayList, deletedTask);
            }
        } catch (NumberFormatException e) {
            ui.printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            ui.printUnknownErrorMessage();
        }
    }

    /**
     * Prints the current list of tasks to the console.
     */
    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        int i = 0;
        for (Task a: itemArrayList) {
            System.out.println("\t" + (i + 1) + "." + a);
            i += 1;
        }
    }

    /**
     * Returns the formatted string representation of all tasks in the list.
     * Each task is formatted based on its type and details.
     *
     * @return A string representing the formatted tasks.
     */
    public String getFormattedTasks() {
        String outputString = "";
        for (Task a: itemArrayList) {
            outputString += a.formattedTask() + System.lineSeparator();
        }
        return outputString;
    }

    public void findItem(String line) {
        try {
            String findDescription = Parser.extractFindDescription(line);
            ArrayList<Task> matchedArrayList = new ArrayList<>(itemArrayList); // Safe copy of the original list

            int i = 0;
            while (i < matchedArrayList.size()) {
                Task t = matchedArrayList.get(i);

                if (!t.getDescription().contains(findDescription)) {
                    matchedArrayList.remove(t);
                } else {
                    i += 1;
                }
            }

            System.out.println("\tHere are the matching tasks in your list:");
            int j = 0;
            for (Task a: matchedArrayList) {
                System.out.println("\t" + (j + 1) + "." + a);
                j += 1;
            }
        } catch (EmptyDescriptionException e) {
            ui.printFindDescriptionEmptyMessage();
        }
    }
}
