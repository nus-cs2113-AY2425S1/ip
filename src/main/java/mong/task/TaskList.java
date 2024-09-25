package mong.task;

import mong.exception.IllegalTaskFormatException;

import java.util.ArrayList;

import static mong.Mong.ui;

public class TaskList {
    public static ArrayList<Task> list;
    public TaskList() {
        list = new ArrayList<Task>();
    }
    /**
     * Marks item in index as completed.
     */
    public static void mark(String input) {
        try {
            // the itemIndex is -1 than the input from the user
            int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (itemIndex < 0 || itemIndex >= list.size()) {
                throw new NullPointerException();
            }
            list.get(itemIndex).setCompleted(true);
            System.out.println("Mong >_<!!I have marked it as completed:");
            System.out.println(list.get(itemIndex));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Mong?!@ Item not in list.");
        } catch (NumberFormatException e) {
            System.out.println("Mong?!@ That's no-no number.");
        }
    }

    /**
     * Marks completed item in index as incompleted.
     */
    public static void unmark(String input) {
        try {
            // the itemIndex is -1 than the input from the user
            int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (itemIndex < 0 || itemIndex >= list.size()) {
                throw new NullPointerException();
            }
            list.get(itemIndex).setCompleted(false);
            System.out.println("Mong-mong :<! The task has been unmarked:");
            System.out.println(list.get(itemIndex));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Mong?!@ Item not in list.");
        } catch (NumberFormatException e) {
            System.out.println("Mong?!@ That's no-no number.");
        }
    }

    /**
     * Delete a Task of any valid type.
     */
    public static void deleteTask(String input) {
        try {
            int indexValue = Integer.parseInt(input.split(" ")[1]) - 1;
            if (indexValue < 0 || indexValue >= list.size()) {
                throw new NullPointerException();
            }
            Task deletedTask = list.get(indexValue);
            list.remove(indexValue);
            ui.printItemDeletedMessage();
            System.out.println(deletedTask);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Mong?!@ Item not in list.");
        } catch (NumberFormatException e) {
            System.out.println("Mong?!@ That's no-no number.");
        }
    }

    /**
     * Prints the indexed list.
     * If the task format is incorrect, error is handled.
     */
    public static void handleListCommand(String input) {
        try {
            ui.printIndexedList(input);
        } catch (IllegalTaskFormatException e) {
            System.out.println("Oi oi MONG! Task format is incorrect...");
        }
    }

    /**
     * Add a Task of type Deadline from txt file.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addDeadline(String isCompleted, String description, String by) {
        list.add(new Deadline(description, by));
        if (isCompleted.contentEquals("0")) {
            list.get(list.size() - 1).setCompleted(false);
        } else if (isCompleted.contentEquals("1")) {
            list.get(list.size() - 1).setCompleted(true);
        }
    }

    /**
     * Add a Task of type Deadline.
     * A deadline consists of the task description and deadline in String format.
     */
    public static void addDeadline(String input) {
        int endOfCommand = input.indexOf("deadline") + ui.LENGTH_DEADLINE;
        int endOfBy = input.lastIndexOf("/by") + ui.LENGTH_BY;
        int startOfBy = input.indexOf("/by");
        try {
            String description = input.substring(endOfCommand + 1, startOfBy);
            String deadline = input.substring(endOfBy + 1);
            list.add(new Deadline(description, deadline));
            ui.printItemAddedMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oi oi MONG! No description or deadline?@#!");
        }
    }

    /**
     * Add a Task of type Todo from txt file.
     * A todo consists of the task description only.
     */
    public static void addTodo(String isCompleted, String description) {
        list.add(new Todo(description));
        if (isCompleted.contentEquals("0")) {
            list.get(list.size() - 1).setCompleted(false);
        } else if (isCompleted.contentEquals("1")) {
            list.get(list.size() - 1).setCompleted(true);
        }
    }

    /**
     * Add a Task of type Todo.
     * A todo consists of the task description only.
     */
    public static void addTodo(String input) {
        int endOfCommand = input.indexOf("todo") + ui.LENGTH_TODO;
        try {
            String description = input.substring(endOfCommand + 1);
            list.add(new Todo(description));
            ui.printItemAddedMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oi oi MONG! No description?@#!");
        }
    }

    /**
     * Add a Task of type Event.
     * An event consists of the task description and from/to period in String format.
     */
    public static void addEvent(String isCompleted, String description, String from, String to) {
        list.add(new Event(description, from, to));
        if (isCompleted.contentEquals("0")) {
            list.get(list.size() - 1).setCompleted(false);
        } else if (isCompleted.contentEquals("1")) {
            list.get(list.size() - 1).setCompleted(true);
        }
    }

    /**
     * Add a Task of type Event.
     * An event consists of the task description and from/to period in String format.
     */
    public static void addEvent(String input) {
        int endOfCommand = input.indexOf("event") + ui.LENGTH_EVENT;
        int endOfFrom = input.lastIndexOf("/from") + ui.LENGTH_FROM;
        int startOfFrom = input.indexOf("/from");
        int endOfTo = input.lastIndexOf("/to") + ui.LENGTH_TO;
        int startOfTo = input.indexOf("/to");
        try {
            String description = input.substring(endOfCommand + 1, startOfFrom - 1);
            String from = input.substring(endOfFrom + 2, startOfTo - 1);
            String to = input.substring(endOfTo + 1);
            list.add(new Event(description, from, to));
            ui.printItemAddedMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oi oi MONG! Missing description, /from or /to commands...");
        }
    }
}
