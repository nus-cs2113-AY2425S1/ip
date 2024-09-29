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