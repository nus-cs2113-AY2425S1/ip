package tasks;

public class ToDo extends Task {
    public ToDo (String description){
        super(description);
    }

    @Override
    public String taskType() {
        return "[T] ";
    }

    @Override
    public String toString(){
        return this.taskType()  + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
