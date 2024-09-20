package TaskChildren; // Package for Task-related classes

import CustomExceptions.ToDoConstructorException; // Import custom exception for ToDo tasks

// ToDo class, a child class of Task, represents a simple to-do task
public class ToDo extends Task {


    // Constructor for creating a ToDo task
    public ToDo(String inputString, boolean constructorMessage) throws ToDoConstructorException {
        // Call the parent class (Task) constructor, removing the "todo" prefix from the input
        super(inputString.replace("todo ", ""));
        this.inputString = inputString;
        // If the task description is empty, throw a custom exception
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
