package Uranus.Tasks;

import UranusExceptions.UranusExceptions;

/**
 * Represents a ToDo task with a description.
 * Inherits from the Task class and has a specific tag "T" for ToDos.
 */
public class ToDos extends Task{
    private static final String TODO_TAG = "T";

    /**
     * Constructs a new ToDo task with the provided description.
     *
     * @param description The task description.
     * @throws UranusExceptions If the description is empty or invalid.
     */
    public ToDos(String description) throws UranusExceptions {
        super(description, TODO_TAG);
    }

    /**
     * Sets the description for the ToDo task.
     *
     * @param description The task description.
     * @throws UranusExceptions If the description is invalid.
     */
    @Override
    public void setDescription(String description) throws UranusExceptions{
        super.setDescription(description);
    }
}
