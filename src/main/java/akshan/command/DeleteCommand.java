package akshan.command;

import akshan.task.Task;
import akshan.task.TaskList;

public final class DeleteCommand extends Command {
    /**
     * Constructor for DeleteCommand.
     *
     * @param commandType The command from the user.
     * @param taskString The string appended to the command to be executed.
     * @param taskList The list of tasks.
     */
    public DeleteCommand(CommandType commandType, String taskString, TaskList taskList) {
        super(commandType, taskString, taskList);
    }

    /**
     * Deletes a task from the task list and prints a confirmation message.
     *
     * @param taskList The list of tasks.
     * @param index The index of the task to be deleted.
     */
    private static void deleteTaskFromList(TaskList taskList, int index) {
        Task deletedTask = taskList.deleteItem(index);
        System.out.println("Got it. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Executes the delete command.
     *
     * @throws IllegalArgumentException If the command format is invalid or the task number is out of range.
     */
    @Override
    public void execute() throws IllegalArgumentException {
        String[] parts = taskString.split(" ");
        if (parts.length != 1) {
            throw new IllegalArgumentException("Invalid delete command format");
        }
        try {
            int index = Integer.parseInt(parts[0]);
            deleteTaskFromList(super.taskList, index - 1);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number: " + parts[0]);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Task number out of range: " + parts[0]);
        }
    }
}
