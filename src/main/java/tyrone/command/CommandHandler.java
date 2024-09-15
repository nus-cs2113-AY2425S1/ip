package tyrone.command;

import tyrone.FileReadWriter;
import tyrone.command.exceptions.EmptyFieldException;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.TaskList;
import tyrone.task.Todo;

public class CommandHandler {

    public static boolean isExitCommand (String command) {
        return command.equals("bye");
    }

    public static void handleCommand(String command) {
        String[] dissectedCommand = command.split(" ");
        if (command.equals("list")) {
            handleList();
        } else if (dissectedCommand[0].equals("mark")) {
            handleMark(dissectedCommand);
        } else if (dissectedCommand[0].equals("unmark")) {
            handleUnmark(dissectedCommand);
        } else if (dissectedCommand[0].equals("todo")) {
            handleTodo(command);
        } else if (dissectedCommand[0].equals("deadline")) {
            handleDeadline(command);
        } else if (dissectedCommand[0].equals("event")) {
            handleEvent(command);
        } else {
            handleUnknown();
        }
        FileReadWriter.updateSaveFile();
    }

    private static void handleList() {
        System.out.println("Here are the tasks in your list:");
        TaskList.printList();
    }

    private static void handleUnknown() {
        System.out.println("Unrecognized command.");
    }

    private static void handleEvent(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from"));
            String start = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
            String end = command.substring(command.indexOf("/to") + 4);
            if (hasOnlyWhitespaceChar(description) || hasOnlyWhitespaceChar(start) || hasOnlyWhitespaceChar(end)) {
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
            String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
            String deadline = command.substring(command.indexOf("/by") + 4);
            if (hasOnlyWhitespaceChar(description) || hasOnlyWhitespaceChar(deadline)) {
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
                throw new EmptyFieldException();
            }
            Todo newTodo = new Todo(command.substring(command.indexOf(" ") + 1));
            TaskList.addTask(newTodo);
            System.out.println("added: " + newTodo.getNameWithStatus());
        } catch (EmptyFieldException e) {
            System.out.println("Description cannot be empty.");
        }
    }

    private static void handleUnmark(String[] dissectedCommand) {
        try {
            int taskId = Integer.parseInt(dissectedCommand[1]) - 1;
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

    private static void handleMark(String[] dissectedCommand) {
        try {
            int taskId = Integer.parseInt(dissectedCommand[1]) - 1;
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

    private static boolean hasOnlyWhitespaceChar(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isWhitespace(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
