package Uranus.Tasks;

public class Task {
    protected String description;
    protected String commandInput;
    protected boolean isDone;
    private String taskTag;
    private static final String COMPLETED_STATUS_ICON = "X";
    private static final String INCOMPLETE_STATUS_ICON = " ";
    private static final String NO_TAG = " ";

    public Task(String description) {
        this.commandInput = description;
        setDescription(description);
        this.isDone = false;
        this.taskTag = NO_TAG; // for no-tag
    }

    // Overloaded function for available tags
    public Task(String description, String taskTag) {
        this.commandInput = description;
        setDescription(description);
        this.isDone = false;
        this.taskTag = taskTag;
    }

    public String getStatusIcon(){
        return (isDone ? COMPLETED_STATUS_ICON : INCOMPLETE_STATUS_ICON);
    }

    public void setDone(){
        isDone = true;
    }

    public void setNotDone(){
        isDone = false;
    }

    public String getTaskTag(){
        return taskTag;
    }

    public void setDescription(String description) {
        int separatorIndex = description.indexOf(' ');
        this.description = description.substring(separatorIndex + 1);
    }

    public String getTaskStatus(){
        return "[" + getTaskTag() + "][" + getStatusIcon() + "] " + description;
    }

    public String getCommandInput(){
        return commandInput;
    }
}
