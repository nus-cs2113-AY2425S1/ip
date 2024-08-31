public class Task {
    protected String description;
    protected boolean isDone;
    private String tag;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "N"; // for no-tag
    }

    public Task(String description, String tag) {
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

    public void setDescription(String description){
        this.description = description;
    }

    public String getTaskStatus(){
        return "[" + getTag() + "][" + getStatusIcon() + "] " + description;
    }

}
