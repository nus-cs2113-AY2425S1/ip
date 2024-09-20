package TaskChildren; // Package for Task-related classes

import CustomExceptions.EventConstructorException; // Import custom exception for Event tasks

// Event class, a child class of Task, represents a task with a specific start and end time
public class Event extends Task {

    // Variables to store the start and end times of the event
    private String fromString;
    private String toString;

    // Constructor for creating an Event task
    public Event(String inputString, boolean constructorMessage) throws EventConstructorException {
        // Call the parent class (Task) constructor with the task description (before the "/from" keyword)
        super(inputString.replace("event ", "").split(" /from ")[0]);

        this.inputString = inputString;

        // Validate if both "/from" and "/to" keywords are present, and the task description is not empty
        if (!(inputString.contains(" /from ") & inputString.contains(" /to ")) || this.taskString.isEmpty()) {
            throw new EventConstructorException(inputString); // Throw custom exception if validation fails
        }

        // Split the input to extract the start and end times
        String[] fromToStrings = inputString.replace("event ", "").split(" /from ")[1].split(" /to ");

        // Check if both "from" and "to" times are provided
        if (fromToStrings.length != 2) {
            throw new EventConstructorException(inputString); // Throw custom exception if either is missing
        }

        // Set the from and to times for the event
        this.fromString = fromToStrings[0];
        this.toString = fromToStrings[1];

        // Display a confirmation message when an Event task is successfully created
        if (constructorMessage) {
            constructorMessage();
        }
    }

    // Override the checkboxString method to include the "[E]" tag, start time, and end time
    @Override
    public String checkboxString() {
        // Call the parent method and add the "[E]" tag along with the event's start and end times
        return "[E]" + super.checkboxString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
