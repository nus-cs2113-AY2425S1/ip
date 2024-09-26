package tyrone.command;

import tyrone.Ui;
import tyrone.command.exceptions.EmptyFieldException;
import tyrone.storage.Storage;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.TaskList;
import tyrone.task.Todo;

public class Parser {

    public static final int START_INDEX_OFFSET_DESC = 1;
    public static final int START_INDEX_OFFSET_KEYWORD = 1;
    public static final int START_INDEX_OFFSET_START = 6;
    public static final int START_INDEX_OFFSET_END = 4;
    public static final int START_INDEX_OFFSET_DEADLINE = 4;
    public static final String TWO_SPACE_INDENT = "  ";

    public static boolean isExitCommand (String input) {
        return input.equals("bye");
    }

    public static void handleInput(String input) {
        String[] dissectedInput = input.split(" ");
        String command = dissectedInput[0];

        switch (command) {
        case "list":
            handleList(dissectedInput);
            break;
        case "mark":
            handleMark(dissectedInput);
            break;
        case "unmark":
            handleUnmark(dissectedInput);
            break;
        case "todo":
            handleTodo(input);
            break;
        case "deadline":
            handleDeadline(input);
            break;
        case "event":
            handleEvent(input);
            break;
        case "delete":
            handleDelete(dissectedInput);
            break;
        case "find":
            handleFind(input);
            break;
        default:
            handleUnknown();
            break;
        }

        Storage.updateSaveFile();
    }

    private static void handleList(String[] dissectedInput) {
        if (dissectedInput.length > 1) {
            Ui.println("Unrecognized command.");
        } else {
            Ui.println("Here are the tasks in your list:");
            Ui.println(TaskList.getAllTaskDetails());
        }
    }

    private static void handleUnknown() {
        Ui.println("Unrecognized command.");
    }

    private static void handleEvent(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC, 
                    command.indexOf(" /from"));
            String start = command.substring(command.indexOf("/from") + START_INDEX_OFFSET_START,
                    command.indexOf(" /to"));
            String end = command.substring(command.indexOf("/to") + START_INDEX_OFFSET_END);
            if (description.isBlank() || start.isBlank() || end.isBlank()) {
                throw new EmptyFieldException();
            }
            Event newEvent = new Event(description, start, end);
            TaskList.addTask(newEvent);
            Ui.println("added: " + newEvent.getNameWithStatus());
        } catch (EmptyFieldException e) {
            Ui.println("Description/Start time/End time cannot be empty.");
        } catch (StringIndexOutOfBoundsException e) {
            Ui.println("Please input Event tasks using the following format:\n" +
                    "event <description> /from <start> /to <end>");
        }
    }

    private static void handleDeadline(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC,
                    command.indexOf(" /by"));
            String deadline = command.substring(command.indexOf("/by") + START_INDEX_OFFSET_DEADLINE);
            if (description.isBlank() || deadline.isBlank()) {
                throw new EmptyFieldException();
            }
            Deadline newDeadline = new Deadline(description, deadline);
            TaskList.addTask(newDeadline);
            Ui.println("added: " + newDeadline.getNameWithStatus());
        } catch (EmptyFieldException e) {
            Ui.println("Description/Deadline cannot be empty.");
        } catch (StringIndexOutOfBoundsException e) {
            Ui.println("Please input Deadline tasks using the following format:\n" +
                    "deadline <description> /by <deadline>");
        }
    }

    private static void handleTodo(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC);
            if (!command.contains(" ") || description.isBlank()) {
                throw new EmptyFieldException();
            }
            Todo newTodo = new Todo(description);
            TaskList.addTask(newTodo);
            Ui.println("added: " + newTodo.getNameWithStatus());
        } catch (EmptyFieldException e) {
            Ui.println("Description cannot be empty.");
        }
    }

    private static void handleUnmark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsUndone(taskId);
                Ui.println("Ok, I've marked this task as not done yet:");
                Ui.println(TWO_SPACE_INDENT + TaskList.getSingleTaskDetails(taskId));
            } else {
                Ui.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.println("Please input the index of the task you wish to unmark.");
        } catch (NumberFormatException e) {
            Ui.println("Index to unmark should be a number.");
        }
    }

    private static void handleMark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsDone(taskId);
                Ui.println("Nice! I've marked this task as done:");
                Ui.println(TWO_SPACE_INDENT + TaskList.getSingleTaskDetails(taskId));
            } else {
                Ui.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.println("Please input the index of the task you wish to mark.");
        } catch (NumberFormatException e) {
            Ui.println("Index to mark should be a number.");
        }
    }

    private static void handleDelete (String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                Ui.println("Ok, I've removed this task:");
                Ui.println(TWO_SPACE_INDENT + TaskList.getSingleTaskDetails(taskId));
                TaskList.deleteTask(taskId);
            } else {
                Ui.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.println("Please input the index of the task you wish to delete.");
        } catch (NumberFormatException e) {
            Ui.println("Index to unmark should be a number.");
        }
    }

    private static void handleFind(String input){
        String keyword = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_KEYWORD);
        Ui.println("Here are the matching tasks in your list:");
        Ui.println(TaskList.listTasksWithKeyword(keyword));
    }
}
