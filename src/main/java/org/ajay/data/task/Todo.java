package org.ajay.data.task;


import org.ajay.data.exceptions.EmptyArgumentException;

public class Todo extends Task {
    public final static String COMMAND_STRING = "todo"; // Command string for the Todo class
    public final static String TASK_STRING = "T";

    /**
     * Constructor for the Todo class.
     *
     * @param description
     */
    public Todo(String description) throws EmptyArgumentException {
        super(description);

    }

    public Todo(String description, boolean isDone) throws EmptyArgumentException {
        super(description);
        super.setDoneState(isDone);
    }


    @Override
    public String saveTaskString() {
        return TASK_STRING + " | " + (super.getDoneState() ? "1" : "0") + " | " + description;
    }

    public static Todo loadTaskString(boolean isDone, String description) throws EmptyArgumentException {
        Todo todo = new Todo(description, isDone);
        return todo;
    }

    @Override
    public String toString() {
        return "[" + TASK_STRING + "]" + super.toString();
    }
}
