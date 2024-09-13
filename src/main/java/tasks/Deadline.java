package tasks;

import tasks.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String taskType() {
        return "[D] ";
    }
    public String getBy() { return by; }

    @Override
    public String toString(){
        return this.taskType()+ "[" + super.getStatusIcon()+ "] " + description  + " by: " + by ;
    }
}
