package joe;

/**
 * Super class of the Todo, Event and Deadline class
 * Encapsulates basic construction, parsing and modification methods for all Task objects.
 */
public class Task {
    private static final String[] TASK_TYPES = {"todo", "deadline", "event"};
    private String itemDescription;
    private boolean isToDo;

    /**
     * Constructs a new Task object with default todo status being true
     * @param itemDescription String description of the task
     */
    public Task(String itemDescription) {
        this.itemDescription = itemDescription;
        this.isToDo = true;
    }

    /**
     * Constructs new Task object with modifiable todo status
     * @param itemDescription String description of the task
     * @param isToDo boolean todo status of the task
     */
    public Task(String itemDescription, boolean isToDo) {
        this.itemDescription = itemDescription;
        this.isToDo = isToDo;
    }

    /**
     * Getter function for the private itemDescription member
     * @return description of the task
     */
    public String getItemDescription() {
        return this.itemDescription;
    }

    /**
     * Sets the todo status of this object to the provided boolean
     * @param isTodo
     */
    public void setToDo(boolean isTodo) {
        this.isToDo = isTodo;
    }

    /**
     * Getter function fo the private todo status of the task
     * @return boolean whether the task is to be done (true) or not (false)
     */
    public boolean isToDo() {
        return this.isToDo;
    }

    /**
     * Extracts the description of a task following its itemType command
     * @param input String user input
     * @param itemType String signalling the task subtype (possibilities: event, deadline, todo)
     * @return String the task description inputted by the user after the itemType signaller
     */
    public static String extractDescription(String input, String itemType) {
        int indexOfDescription = input.indexOf(itemType) + itemType.length();
        return input.substring(indexOfDescription, input.length()).strip();
    }

    @Override
    public String toString() {
        String checkBox;
        if (this.isToDo) {
            checkBox = " [" + "not done" + "]";
        } else {
            checkBox = " [" + "done" + "]";
        }

        return "[T]" + checkBox + " " +
                this.getItemDescription();
    }

}

