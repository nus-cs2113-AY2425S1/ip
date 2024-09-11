package org.ajay.task;

import org.ajay.exceptions.EmptyArgumentException;

public class Todo extends Task {
    public final static String COMMAND_STRING = "todo"; // Command string for the Todo class

    /**
     * Constructor for the Todo class.
     *
     * @param description
     */
    public Todo(String description) throws EmptyArgumentException {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        Task.printNumberOfTasks();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
