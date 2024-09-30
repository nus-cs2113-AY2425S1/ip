public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isMark;

    public MarkCommand(String index, boolean isMark) {
        this.taskIndex = Integer.parseInt(index) - 1; // Convert from user input (1-based index) to 0-based index.
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        try {
            if (isMark) {
                tasks.markTask(taskIndex); // Mark the task as done
                ui.show("Nice! I've marked this task as done:\n  " + tasks.getTasks().get(taskIndex));
            } else {
                tasks.unmarkTask(taskIndex); // Unmark the task
                ui.show("OK, I've marked this task as not done yet:\n  " + tasks.getTasks().get(taskIndex));
            }
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BebeException("Task does not exist.");
        }
    }
}