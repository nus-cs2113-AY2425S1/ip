package main;

import task.*;

import java.util.ArrayList;
import java.util.Arrays;

public class List {

    private int numItems;
    private Task[] itemList = new Task[0];
    ArrayList<Task> itemArrayList = new ArrayList<>(Arrays.asList(itemList));

    public List() {
        this.numItems = 0;
    }

    public int getNumItems() {
        return itemArrayList.size();
    }

    public void addItem(String line) {
        if (Parser.isValidEvent(line)) {
            TaskList.addEvent(itemArrayList, line);
        } else if (Parser.isValidDeadline(line)) {
            TaskList.addDeadline(itemArrayList, line);
        } else if (Parser.isTodo(line)) {
            TaskList.addTodo(itemArrayList, line);
        } else {
            Ui.printInvalidTaskMessage();
        }
    }

    public void markItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(5));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                Ui.printInputIndexOutOfRangeMessage();
            } else {
                TaskList.markListItemAsDone(itemArrayList, itemNum);
                Ui.printTaskMarkedMessage(itemArrayList, itemNum);
            }
        } catch (NumberFormatException  e) {
            Ui.printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            Ui.printUnknownErrorMessage();
        }
    }

    public void unmarkItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                Ui.printInputIndexOutOfRangeMessage();
            } else {
                TaskList.markListItemAsUnDone(itemArrayList, itemNum);
                Ui.printTaskUnmarkedMessage(itemArrayList, itemNum);
            }
        } catch (NumberFormatException  e) {
            Ui.printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            Ui.printUnknownErrorMessage();
        }
    }

    public void deleteItem(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));

            if (itemNum > this.getNumItems() || itemNum <= 0) {
                Ui.printInputIndexOutOfRangeMessage();
            } else {
                Task deletedTask = itemArrayList.get(itemNum - 1);
                TaskList.deleteListItem(itemArrayList, itemNum);
                Ui.printTaskDeletedMessage(itemArrayList, deletedTask);
            }
        } catch (NumberFormatException  e) {
            Ui.printInputIndexNotAnIntegerMessage();
        } catch (Exception e) {
            Ui.printUnknownErrorMessage();
        }
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        int i = 0;
        for (Task a: itemArrayList) {
            System.out.println("\t" + (i + 1) + "." + a);
            i += 1;
        }
    }

    public String getFormattedTasks() {
        String outputString = "";
        for (Task a: itemArrayList) {
            outputString += a.formattedTask() + System.lineSeparator();
        }
        return outputString;
    }
}
