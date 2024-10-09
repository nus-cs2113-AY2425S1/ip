package atom.task;

/**
 * Contains attributes and methods specific to a <code>todo</code> task.
 */
public class Todo extends Task{

    public Todo(String item) {
        super(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setTaskType() {
        return "T";
    }
}
