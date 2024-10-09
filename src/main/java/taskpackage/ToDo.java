package taskpackage; // Package for Task-related classes

import customexceptions.ToDoConstructorException; // Import custom exception for ToDo tasks

// ToDo class, a child class of Task, represents a simple to-do task
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
        // Call the parent class (Task) constructor, removing the "todo" prefix from the input
        super(inputString, tasks);
        this.inputString = inputString;

        this.storageString = TODO_COMMAND + inputString;

        // Check if the task description is empty and throw an exception if true
        if (this.taskString.isEmpty()) {
            throw new ToDoConstructorException(inputString); // Handle invalid ToDo input
        }

        // Display a confirmation message when a ToDo task is successfully created
        if (constructorMessage) {
            constructorMessage();
        }
    }

    // Override the checkboxString method to prefix "[T]" to indicate that the task is a ToDo
    @Override
    public String checkboxString() {
        return "[T]" + super.checkboxString(); // Call the parent method and add the "[T]" tag
    }
}
