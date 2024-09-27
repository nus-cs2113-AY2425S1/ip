package Commands;

import taskmanager.Storage;

public class ClearCommand extends Command {

    @Override
    public void execute(Storage storage) {
        storage.storageClear();
        System.out.println("Your Task list is empty");
    }
}

