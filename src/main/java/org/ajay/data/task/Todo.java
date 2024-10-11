package org.ajay.data.task;

import org.ajay.data.exceptions.EmptyArgumentException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public final static String COMMAND_WORD = "todo";
    public final static String TASK_ID = "T";
    public final static int TASK_LENGTH = 3;

    /**
     * Constructor for the Todo class.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) throws EmptyArgumentException {
        super(description);

    }

    /**
     * Constructor for the Todo class.
     *
     * @param description Description of the todo.
     * @param isDone      Done state of the todo.
     */
    public Todo(String description, boolean isDone) throws EmptyArgumentException {
        super(description);
        super.setDoneState(isDone);
    }

    /**
     * Saves a todo task to a string.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String saveTaskString() {
        return TASK_ID + " | " + (super.getDoneState() ? "1" : "0") + " | " + description;
    }

    /**
     * Loads a todo task from a string.
     *
     * @param isDone      Done state of the todo.
     * @param description Description of the todo.
     * @return Todo task.
     * @throws EmptyArgumentException If the description is empty.
     */
    public static Todo loadTaskString(boolean isDone, String description) throws EmptyArgumentException {
        Todo todo = new Todo(description, isDone);
        return todo;
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + TASK_ID + "]" + super.toString();
    }
}
