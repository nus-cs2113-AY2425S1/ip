package atom.task;

/**
 * Contains attributes and methods specific to a <code>deadline</code> task.
 */
public class Deadline extends Task{

    private String dueDate;

    public Deadline(String item, String dueDate) {
        super(item);
        this.dueDate = dueDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDueDate() {
        return dueDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setTaskType() {
        return "D";
    }
}
