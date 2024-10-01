package akshan.command;

import akshan.task.Task;
import akshan.task.TaskList;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class TaskCommand extends Command {
    private static final Map<CommandType, Function<String, String[]>> COMMAND_PARSERS = new HashMap<>();
    private final Task task;

    static {
        COMMAND_PARSERS.put(CommandType.TODO, TaskCommand::parseTodo);
        COMMAND_PARSERS.put(CommandType.DEADLINE, TaskCommand::parseDeadline);
        COMMAND_PARSERS.put(CommandType.EVENT, TaskCommand::parseEvent);
    }

    /**
     * Constructor for TaskCommand. Processes a task command (TODO, DEADLINE, or EVENT).
     *
     * @param commandType The command from the user.
     * @param taskString  The string appended to the command to be executed.
     * @param taskList The list of tasks.
     */
    public TaskCommand(CommandType commandType, String taskString, TaskList taskList) {
        super(commandType, taskString, taskList);

        try {
            String[] params = COMMAND_PARSERS.get(commandType).apply(taskString);
            this.task = Task.createTask(commandType.getCommand(), params);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error processing " + commandType + " task: " + e.getMessage());
        }
    }

    /**
     * Parses a TODO command.
     *
     * @param taskString The string appended to the command to be executed.
     * @return An array containing the task description.
     * @throws IllegalArgumentException If the todo task description is missing.
     */
    private static String[] parseTodo(String taskString) throws IllegalArgumentException {
        String[] parts = taskString.split(" ");
        if (parts.length < 1) {
            throw new IllegalArgumentException("Todo task description is missing");
        }
        return new String[]{parts[0]};
    }

    /**
     * Parses a DEADLINE command.
     *
     * @param taskString The string appended to the command to be executed.
     * @return An array containing the task description and deadline.
     * @throws IllegalArgumentException If the deadline task is missing description or deadline.
     */
    private static String[] parseDeadline(String taskString) throws IllegalArgumentException {
        String[] parts = taskString.split(" /by ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Deadline task is missing description or deadline");
        }
        return new String[]{parts[0], parts[1]};
    }

    /**
     * Parses an EVENT command.
     *
     * @param taskString The string appended to the command to be executed.
     * @return An array containing the event description, start time, and end time.
     * @throws IllegalArgumentException If the event task is missing description, start time, or end time.
     */
    private static String[] parseEvent(String taskString) throws IllegalArgumentException {
        String[] parts = taskString.split(" /", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Event task is missing description, start time, or end time");
        }

        String[] startParts = parts[1].split(" ", 2);
        String[] endParts = parts[2].split(" ", 2);
        if (startParts.length < 2 || endParts.length < 2) {
            throw new IllegalArgumentException("Event task is missing start time or end time");
        }
        return new String[]{parts[0], startParts[1], endParts[1]};
    }

    /**
     * Adds a task to the task list and prints a confirmation message.
     *
     * @throws IllegalArgumentException If the command format is invalid or the task number is out of range.
     */
    @Override
    public void execute() throws IllegalArgumentException {
        super.taskList.addItem(this.task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.task);
        System.out.println("Now you have " + super.taskList.size() + " tasks in the list.");
    }
}
