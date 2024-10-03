package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;

/**
 * Subclass of <code>Task</code> for tasks without deadline or duration.
 */
public class ToDo extends Task {

    /**
     * Constructor for <code>ToDo</code>.
     *
     * @param input User input excluding command.
     * @throws Exception If the input is empty.
     */
    public ToDo(String input) throws Exception {
        super(input.trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
        } catch (DescriptionEmpty e) {
            ui.printEmptyDescriptionError();
            throw new Exception("Description is empty");
        }
    }

    /**
     * Constructor for <code>ToDo</code>.
     *
     * @param description task description.
     * @param isDone status of task (done/ not done).
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * @inheritDoc
     * Includes task type symbol.
     *
     * @return <code>String</code> with <code>ToDo</code> details.
     */
    public String generateTaskLine() {
        return "[T]" + super.generateTaskLine();
    }

    /**
     * @inheritDoc
     * Includes task type symbol.
     *
     * @return <code>String</code> with <code>ToDo</code> details.
     */
    public String generateStorageLine() {
        return "T | " + super.generateStorageLine() + "\n";
    }
}
