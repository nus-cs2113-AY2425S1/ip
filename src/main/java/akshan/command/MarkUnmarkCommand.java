package akshan.command;

import akshan.task.TaskList;

import java.io.IOException;

public final class MarkUnmarkCommand extends Command {
    private final boolean mark;
    /**
     * Constructor for MarkUnmarkCommand.
     *
     * @param commandType The command from the user.
     * @param taskString The string appended to the command to be executed.
     * @param taskList The list of tasks.
     */
    public MarkUnmarkCommand(CommandType commandType, String taskString, TaskList taskList) {
        super(commandType, taskString, taskList);
        this.mark = commandType.equals(CommandType.MARK);
    }

    /**
     * Processes the mark or unmark command. Marks task if this.mark is true, else unmark task.
     *
     * @throws IllegalArgumentException If the command format is invalid or the task number is out of range.
     */
    @Override
    public void execute() throws IllegalArgumentException {
        String[] parts = super.taskString.split(" ");
        if (parts.length != 1) {
            throw new IllegalArgumentException("Invalid mark/unmark command format");
        }
        try {
            int index = Integer.parseInt(parts[0]);
            super.taskList.setItemStatus(index, mark);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number: " + parts[0]);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Task number out of range: " + parts[0]);
        }
    }
}
