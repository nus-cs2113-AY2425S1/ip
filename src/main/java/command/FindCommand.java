package command;

import data.Storage;
import exceptions.IrisException;
import task.TaskList;

public class FindCommand extends Command {

    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        System.out.println("Here are the matching tasks in your list:");
        tasks.stream()
                .filter(task -> task.description.contains(description))
                .forEach(System.out::println);
    }
}
