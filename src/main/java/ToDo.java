import exception.InvalidCreateToDoException;

/**
 * Represents a To-Do task with a description.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new ToDo object based on user input.
     *
     * @param userInput The user input for creating the to-do task.
     * @throws InvalidCreateToDoException If the input is empty (no description provided).
     */
    public static void createNewToDo(String userInput) throws InvalidCreateToDoException {
        if (!userInput.isEmpty()) {
            TaskList.tasks.add(new ToDo(userInput));
            UI.printContent("Added ToDo: " + userInput);
        } else {
            throw new InvalidCreateToDoException();
        }
    }

    /**
     * Returns a string representation of the to-do.
     *
     * @return A formatted string representing the to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Generates a string for saving the to-do to the Data Manager.
     *
     * @return A formatted string for saving the to-do.
     */
    @Override
    public String toSaveString() {
        return "T" + this.getStatusIcon() + "//" + this.description;
    }

    /**
     * Prints a message indicating that the to-do has been marked as done.
     */
    @Override
    public void printMark() {
        UI.printContent("Nice! You have done this ToDo:\n\t" + this.toString());
    }

    /**
     * Prints a message indicating that the to-do has been unmarked.
     */
    @Override
    public void printUnmark() {
        UI.printContent("I have unmarked this ToDo:\n\t" + this.toString());
    }
}
