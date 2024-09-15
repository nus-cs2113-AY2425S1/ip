package medea.task;

public class Event  extends Task{
    private String start;
    private String end;

    public Event (String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }
    @Override
    public String toCSV(){
        return String.format("E,%s,%s,%s", super.toCSV(), start, end);
    }
}
