package Uranus.Tasks;

public class Task {
    protected String description;
    protected String commandInput;
    protected boolean isDone;
    private String tag;

    public Task(String description) {
        this.commandInput = description;
        setDescription(description);
        this.isDone = false;
        this.tag = " "; // for no-tag
    }

    // Overloaded function for available tags
    public Task(String description, String tag) {
        this.commandInput = description;
        setDescription(description);
        this.isDone = false;
        this.tag = tag;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public void setDone(){
        isDone = true;
    }

    public void setNotDone(){
        isDone = false;
    }

    public String getTag(){
        return tag;
    }

    public void setDescription(String description) {
        int separatorIndex = description.indexOf(' ');
        this.description = description.substring(separatorIndex + 1);
    }

    public String getTaskStatus(){
        return "[" + getTag() + "][" + getStatusIcon() + "] " + description;
    }

    public String getCommandInput(){
        return commandInput;
    }
}
