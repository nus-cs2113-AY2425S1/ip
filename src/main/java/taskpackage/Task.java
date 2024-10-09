package taskpackage; // Package for Task-related classes

/**
 * Represents a general task that can be extended to specific types of tasks such as ToDo, Deadline, and Event.
 */
public class Task {

    public String inputString; // Input string for the task
    public String taskString;  // Description of the task
    public boolean isDone = false; // Boolean to track if the task is marked as done

    /**
     * Constructor for creating a general task.
     *
     * @param inputString The description of the task.
     * @param tasks       The task list to which the task is added.
     */
    public Task(String inputString, TaskList tasks) {
        this.taskString = inputString; // Set the task description
        tasks.addTask(this);           // Add the task to the task list
    }

    /**
     * Displays a message confirming the task was successfully created.
     */
    public void constructorMessage() {
        System.out.println("Muy Bien, work hard compadre!"); // Success message in Spanish
        System.out.println("I've Added the Task:");
        System.out.println(checkboxString()); // Display the task in checkbox format
    }

    /**
     * Returns the task's string with a checkbox indicating whether it's done.
     *
     * @return The formatted task string.
     */
    public String checkboxString() {
        String returnString = "["; // Start of the checkbox string
        if (this.isDone) {
            returnString += "X"; // Mark the checkbox as done if the task is completed
        } else {
            returnString += " "; // Leave the checkbox empty if the task is not completed
        }
        returnString += "] " + this.taskString; // Append the task description
        return returnString; // Return the formatted string
    }
}
