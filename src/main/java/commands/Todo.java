package commands;

public class Todo extends Task {

    /**
     * Todo constructor
     * @param description A string containing the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo's status of completion.
     * [T] represents deadline
     * @return A string containing the todo symbol with the status icon
     */
    public String getStatusIcon() {
        return ("[T]" + super.getStatusIcon());
    }

    /**
     * Returns a formatted todo for display in task list
     * @return A string containing the task status icon and description,
     * for the task list.
     */
    @Override
    public String createTaskList() {
        return (getStatusIcon()+ " " +  description);
    }

    /**
     * Returns a formatted todo for saving in the txt file
     * @return A string containing the task status icon, description
     * formatted for saving
     */
    @Override
    public String createTaskTxt() {
        return ("T | " + super.getStatus() + " | " + description);
    }
}
