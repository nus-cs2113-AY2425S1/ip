package taskpackage; // Package for Task-related classes

import customexceptions.ToDoConstructorException; // Import custom exception for ToDo tasks

/**
 * Represents a simple to-do task, extending the Task class.
 */
public class ToDo extends Task {


    private static final String TODO_COMMAND = "todo ";

    /**
     * Constructor for creating a ToDo task.
     *
     * @param inputString        The description of the to-do task.
     * @param tasks              The task list to which the task is added.
     * @param constructorMessage A flag to indicate whether to display a constructor message.
     * @throws ToDoConstructorException if the task description is empty.
     */
    public ToDo(String inputString, TaskList tasks, boolean constructorMessage) throws ToDoConstructorException {
        super(inputString, tasks);
        this.inputString = inputString;

        this.storageString = TODO_COMMAND + inputString;

        // Check if the task description is empty and throw an exception if true
        if (this.taskString.isEmpty()) {
            throw new ToDoConstructorException(inputString);
        }

        // Display a confirmation message if the constructorMessage flag is true
        if (constructorMessage) {
            constructorMessage();
        }
    }

    /**
     * Returns the task's string with a checkbox, prefixed with "[T]" to indicate it is a ToDo.
     *
     * @return The formatted task string.
     */
    @Override
    public String checkboxString() {
        return "[T]" + super.checkboxString();
    }
}
