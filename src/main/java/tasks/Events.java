package tasks;

import tasks.Task;

public class Events extends Task {
    protected String from;
    public Events (String description, String from){
        super(description);
        this.from =from;
    }
    @Override
    public String taskType(){
        return "[E] ";
    }

    @Override
    public String toString(){
        return (this.taskType()+ "["+ super.getStatusIcon()+ "] " + description + "from:" +from +" ");
    }
}
