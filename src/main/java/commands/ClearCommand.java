package commands;

import taskmanager.taskManager;

/**
 * The ClearCommand class handles clearing all tasks from the task list.
 * It calls the appropriate method in Storage to remove all tasks and prints a confirmation message.
 */

public class ClearCommand extends Command {

    /**
     * Executes the clear command by clearing all tasks from storage.
     * After clearing the tasks, it prints a message indicating that the task list is empty.
     *
     * @param storage The storage object that manages the task list.
     */

    @Override
    public void execute(taskManager storage) {
        storage.storageClear();
        System.out.println("Your Task list is empty");
    }
}

