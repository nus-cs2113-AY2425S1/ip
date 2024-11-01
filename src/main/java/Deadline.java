/**
 * Derived from the Task abstract class
 * Includes the symbol "D" for deadline
 * Includes a user-inputted String for the due date
 */
public class Deadline extends Task {
    private String dueDate;

    public Deadline() {
        this.name = "";
        this.isDone = false;
        this.symbol = "D";
        this.dueDate = "";
    }

    public Deadline(String name, String dueDate) {
        this.name = name;
        this.isDone = false;
        this.symbol = "D";
        this.dueDate = dueDate;
    }

    /**
     * @return the parent class toString plus the due date
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dueDate + ")";
    }

    /**
     * @return the due date formatted for the save file
     */
    @Override
    public String dataForSave() {
        return " | " + this.dueDate;
    }
}