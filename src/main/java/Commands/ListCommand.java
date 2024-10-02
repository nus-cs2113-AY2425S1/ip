package Commands;

import taskmanager.Storage;

/**
 * The ListCommand class is responsible for listing all tasks currently stored.
 * It calls the appropriate method in the Storage class to print the task list.
 */

public class ListCommand extends Command {

    /**
     * Executes the list command by calling the storage's method to display all tasks.
     *
     * @param storage The storage object that manages the task list.
     */

    @Override
    public void execute(Storage storage) {
        storage.storageList();
    }
}
