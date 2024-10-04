package ellio.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, String isDone) {
        this.description = description;
        if(isDone.equals("0")){
            this.isDone = false;
        }
        else {
            this.isDone = true;
        }
    }

    /**
     * For visual Demarcation during printing to terminal
     * @return String of either X for completed task and a blank otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public void unmarkTaskAsDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskInfo(){
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Formats the Current Task object into String format
     * that will be saved into the file.
     * @return String format of current task.
     */
    public String getSaveFileTask(){
        String markState;
        if(isDone){
            markState = "1";
        }
        else {
            markState = "0";
        }
        return "| " + markState + " | " + description;
    }
}