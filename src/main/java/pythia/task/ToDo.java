package pythia.task;

import pythia.utility.WriteVisitor;

/**
 * Represents a to-do task that is part of the task management system.
 * A to-do task can be marked as done or not done and has a name associated with it.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns a string representation of the to-do task, including its status.
     *
     * @return A string indicating the type of task and its status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Accepts a {@link WriteVisitor} to perform a write operation on the to-do task.
     *
     * @param visitor The visitor that handles writing the task's data to a storage format.
     * @return A string representation of the to-do task's data formatted for saving.
     */
    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitToDo(this);
    }
}