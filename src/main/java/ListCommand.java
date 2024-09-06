import java.util.List;

public class ListCommand extends TaskCommand {
    public ListCommand(List<Task> tasks) {
        super(tasks);
        setFormat("");
        setWord("list");
        setGuide("list: List all current tasks.");
    }

    @Override
    public String[] convertArguments(String command) {
        if (!command.isEmpty()) {
            return null;
        }
        return new String[0];
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param rawArgumentString should be null.
     */
    @Override
    public void execute(String rawArgumentString) {
        super.execute(rawArgumentString);
        System.out.println(PREFIX + "Here are the tasks in your list:");
        int index = 1;

        for (Task task : tasks) {
            System.out.printf(PREFIX + "%d. %s%n", index++, task.getFullInfo());
        }
    }
}
