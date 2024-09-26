package tasks;

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

    public String getFrom() { return from; }

    @Override
    public String toString(){
        return (this.taskType()+ "["+ super.getStatusIcon()+ "] " + description + " from: " + from );
    }
}
