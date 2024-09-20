package TaskChildren; // Package for Task-related classes

import CustomExceptions.DeadlineConstructorException; // Import custom exception for Deadline tasks

// Deadline class, a child class of Task, represents a task with a specific deadline
public class Deadline extends Task {

    // Variable to store the deadline date/time string
    private String deadlineString;


    // Constructor for creating a Deadline task
    public Deadline(String inputString, boolean constructorMessage) throws DeadlineConstructorException {
        // Call the parent class (Task) constructor with the task description (before the "/by" keyword)
        super(inputString.replace("deadline ", "").split(" /by ")[0]);
        this.inputString = inputString;
        // Check if the "/by" keyword is missing or if the task description is empty
        if (!(inputString.contains(" /by ")) || this.taskString.isEmpty()) {
            throw new DeadlineConstructorException(inputString); // Throw custom exception if validation fails
        }

        // Extract the deadline portion of the input string (after the "/by" keyword)
        this.deadlineString = inputString.replace("deadline ", "").split(" /by ")[1];

        // Display a confirmation message when a Deadline task is successfully created
        if (constructorMessage) {
            constructorMessage();
        }
    }

    // Override the checkboxString method to include the "[D]" tag and the deadline information
    @Override
    public String checkboxString() {
        // Call the parent method and add the "[D]" tag and deadline string to the checkbox format
        return "[D]" + super.checkboxString() + " (by: " + deadlineString + ")";
    }
}
