package Uranus.Tasks;

import UranusExceptions.UranusExceptions;
import UranusExceptions.EmptyDescriptionException;

public class Task {
    protected String description;
    protected String commandInput;
    protected boolean isDone;
    private String taskTag;
    private static final String COMPLETED_STATUS_ICON = "X";
    private static final String INCOMPLETE_STATUS_ICON = " ";
    private static final String NO_TAG = " ";

    /**
     * Constructs a new Task with the given description.
     * This constructor is for tasks without a specific tag.
     *
     * @param description The task description.
     * @throws UranusExceptions If the description is empty or invalid.
     */
    public Task(String description) throws UranusExceptions {
        this.commandInput = description;
        removeCommand(description);
        setDescription(this.description);
        this.isDone = false;
        this.taskTag = NO_TAG; // for no-tag
    }

    /**
     * Constructs a new Task with the provided description and task tag.
     *
     * @param description The task description.
     *                    The description is set based on the task type.
     * @param taskTag The tag representing the task type.
     * @throws UranusExceptions If the description is empty or invalid.
     */
    public Task(String description, String taskTag) throws UranusExceptions {
        this.commandInput = description;
        removeCommand(description);
        setDescription(this.description);
        this.isDone = false;
        this.taskTag = taskTag;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise an empty space.
     */
    public String getStatusIcon(){
        return (isDone ? COMPLETED_STATUS_ICON : INCOMPLETE_STATUS_ICON);
    }

    /**
     * Marks the task as done.
     */
    public void setDone(){
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone(){
        isDone = false;
    }

    /**
     * Get the task tag.
     *
     * @return The task tag as a string.
     */
    public String getTaskTag(){
        return taskTag;
    }

    /**
     * Sets the description of the task.
     * Description is set based on the task type.
     *
     * @param description The new task description.
     * @throws UranusExceptions If the description is invalid.
     */
    public void setDescription(String description) throws UranusExceptions {
        this.description = description;
    }

    /**
     * Removes the command substring from the input string to extract the task description.
     *
     * @param description The full command input including the task description and command.
     * @throws UranusExceptions If the resulting description is empty or invalid.
     */
    public void removeCommand(String description) throws UranusExceptions {
        int separatorIndex = description.indexOf(' ');
        this.description = description.substring(separatorIndex + 1).trim();
        if (this.description.equals(description) || this.description.isEmpty()){
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Get the description of the task.
     *
     * @return The task description as a string.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Get the task status, including the tag, status icon, and description.
     *
     * @return A formatted string representing the task's status.
     */
    public String getTaskStatus(){
        return "[" + getTaskTag() + "][" + getStatusIcon() + "] " + description;
    }

    /**
     * Get the full command input that was originally passed to create the task.
     *
     * @return The command input as a string.
     */
    public String getCommandInput(){
        return commandInput;
    }
}
