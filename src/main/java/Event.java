public class Event extends Task{
    private String start;
    private String end;
    //private String isDone;

    public Event(String contents, String start, String end) {
        super(contents);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStatusIcon(){
        return "[E]" + (getIsDone() ? "[X]" : "[ ]");
    }

    public String getStart(){
        return this.start;
    }

    public String getEnd(){
        return this.end;
    }

}
