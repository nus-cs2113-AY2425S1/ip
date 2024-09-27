package Commands;

import taskmanager.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(Storage storage) {
        storage.storageList();
    }
}
