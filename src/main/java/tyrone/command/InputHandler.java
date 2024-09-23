package tyrone.command;

import tyrone.savemanager.FileReadWriter;
import tyrone.command.exceptions.EmptyFieldException;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.TaskList;
import tyrone.task.Todo;

public class InputHandler {

    public static final int START_INDEX_OFFSET_DESC = 1;
    public static final int START_INDEX_OFFSET_START = 6;
    public static final int START_INDEX_OFFSET_END = 4;
    public static final int START_INDEX_OFFSET_DEADLINE = 4;

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
        default:
            handleUnknown();
            break;
        }

        FileReadWriter.updateSaveFile();
    }

    private static void handleList(String[] dissectedInput) {
        if (dissectedInput.length > 1) {
            System.out.println("Unrecognized command.");
        } else {
            System.out.println("Here are the tasks in your list:");
            TaskList.printList();
        }
    }

    private static void handleUnknown() {
        System.out.println("Unrecognized command.");
    }

    private static void handleEvent(String command) {
        try {
            if (hasOnlyWhitespaceChar(description) || hasOnlyWhitespaceChar(start) || hasOnlyWhitespaceChar(end)) {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC, 
                    command.indexOf(" /from"));
            String start = command.substring(command.indexOf("/from") + START_INDEX_OFFSET_START,
                    command.indexOf(" /to"));
            String end = command.substring(command.indexOf("/to") + START_INDEX_OFFSET_END);
                throw new EmptyFieldException();
            }
            Event newEvent = new Event(description, start, end);
            TaskList.addTask(newEvent);
            System.out.println("added: " + newEvent.getNameWithStatus());
        } catch (EmptyFieldException e) {
            System.out.println("Description/Start time/End time cannot be empty.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please input Event tasks using the following format:\n" +
                    "event <description> /from <start> /to <end>");
        }
    }

    private static void handleDeadline(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC,
                    command.indexOf(" /by"));
            String deadline = command.substring(command.indexOf("/by") + START_INDEX_OFFSET_DEADLINE);
                throw new EmptyFieldException();
            }
            Deadline newDeadline = new Deadline(description, deadline);
            TaskList.addTask(newDeadline);
            System.out.println("added: " + newDeadline.getNameWithStatus());
        } catch (EmptyFieldException e) {
            System.out.println("Description/Deadline cannot be empty.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please input Deadline tasks using the following format:\n" +
                    "deadline <description> /by <deadline>");
        }
    }

    private static void handleTodo(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + 1);
            if (!command.contains(" ") || hasOnlyWhitespaceChar(description)) {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC);
                throw new EmptyFieldException();
            }
            Todo newTodo = new Todo(description);
            TaskList.addTask(newTodo);
            System.out.println("added: " + newTodo.getNameWithStatus());
        } catch (EmptyFieldException e) {
            System.out.println("Description cannot be empty.");
        }
    }

    private static void handleUnmark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsUndone(taskId);
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("  " + TaskList.getSingleTaskDetails(taskId));
            } else {
                System.out.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the index of the task you wish to unmark.");
        } catch (NumberFormatException e) {
            System.out.println("Index to unmark should be a number.");
        }
    }

    private static void handleMark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsDone(taskId);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + TaskList.getSingleTaskDetails(taskId));
            } else {
                System.out.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the index of the task you wish to mark.");
        } catch (NumberFormatException e) {
            System.out.println("Index to mark should be a number.");
        }
    }

    private static void handleDelete (String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                System.out.println("Ok, I've removed this task:");
                System.out.println("  " + TaskList.getSingleTaskDetails(taskId));
                TaskList.deleteTask(taskId);
            } else {
                System.out.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input the index of the task you wish to unmark.");
        } catch (NumberFormatException e) {
            System.out.println("Index to unmark should be a number.");
        }
    }

    private static boolean hasOnlyWhitespaceChar(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
