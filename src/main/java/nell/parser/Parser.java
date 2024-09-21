package nell.parser;

import nell.TaskList;
import nell.common.Messages;
import nell.tasks.Deadline;
import nell.tasks.Event;
import nell.tasks.ToDo;

/**
 * Handles the parsing and execution of commands entered by the user into the UI
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructs a parser object with a given task list
     *
     * @param tasks The task list to be used by the object
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists out the currently stored tasks in TaskList
     */
    private void listTasks() {
        System.out.println(Messages.LIST_TASKS_MESSAGE);
        int taskCount = tasks.getTaskCount();
        for (int i = 0; i < taskCount; i++) {
            // Prints all tasks in list
            System.out.println(tasks.getTaskStringAtIndex(i));
        }
    }

    /**
     * Adds a todo to the task list
     *
     * @param description The description of the todo
     */
    private void addToDo(String description) {
        ToDo toDoToAdd = new ToDo(description);
        tasks.addTask(toDoToAdd);
    }

    /**
     * Adds a deadline to the task list
     *
     * @param detail The detail of the deadline
     */
    private void addDeadline(String detail) {
        try {
            String[] details = detail.split("/by");
            Deadline deadlineToAdd = new Deadline(details[0].trim(), details[1].trim());
            this.tasks.addTask(deadlineToAdd);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.DEADLINE_ERROR_MESSAGE);
        }
    }

    /**
     * Adds an event command to the task list
     *
     * @param detail The detail of the event
     */
    private void addEvent(String detail) {
        try {
            String[] details = detail.split("/from|/to", 3);
            Event eventToAdd = new Event(details[0].trim(), details[1].trim(), details[2].trim());
            this.tasks.addTask(eventToAdd);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.EVENT_ERROR_MESSAGE);
        }
    }

    /**
     * Unmarks a task as done
     *
     * @param taskNumber The command body
     */
    private void unmarkTask(String taskNumber) {
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            this.tasks.getTaskAtIndex(taskIndex - 1).setDone(false);
            System.out.println(Messages.UNMARK_MESSAGE);
            System.out.println(this.tasks.getTaskStringAtIndex(taskIndex - 1));
        } catch (NumberFormatException e) {
            System.out.print(Messages.UNMARK_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Marks a task as done
     *
     * @param taskNumber The command body
     */
    private void markTask(String taskNumber) {
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            this.tasks.getTaskAtIndex(taskIndex - 1).setDone(true);
            System.out.println(Messages.MARK_MESSAGE);
            System.out.println(this.tasks.getTaskStringAtIndex(taskIndex - 1));
        } catch (NumberFormatException e) {
            System.out.print(Messages.MARK_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Removes a task from the list
     *
     * @param taskNumber The command body
     */
    private void removeTask(String taskNumber) {
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            this.tasks.removeTask(taskIndex - 1);
        } catch (NumberFormatException e) {
            System.out.print(Messages.REMOVE_ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Handles wrong or wrongly formatted commands
     */
    private static void handleIncorrectInput() {
        System.out.print(Messages.INVALID_COMMAND_MESSAGE);
    }

    /**
     * Parses a given command and executes it.
     * If the command is invalid, prints a message that it is so.
     *
     * @param command The command to be parsed and executed
     */
    public void parseCommand(String command) {
        String[] commandWords = command.split(" ", 2);
        switch (commandWords[0]) {
        case "list":
            listTasks();
            break;

        case "mark":
            try {
                markTask(commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.MARK_ERROR_MESSAGE);
            }
            break;

        case "unmark":
            try {
                unmarkTask(commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.UNMARK_ERROR_MESSAGE);
            }
            break;

        case "todo":
            try {
                addToDo(commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.TODO_ERROR_MESSAGE);
            }
            break;

        case "deadline":
            try {
                addDeadline(commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.DEADLINE_ERROR_MESSAGE);
            }
            break;

        case "event":
            try {
                addEvent(commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.EVENT_ERROR_MESSAGE);
            }
            break;

        case "remove":
            try {
                removeTask(commandWords[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(Messages.REMOVE_ERROR_MESSAGE);
            }
            break;

        default:
            handleIncorrectInput();
            break;
        }
    }
}
