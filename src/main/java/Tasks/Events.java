package Tasks;

public class Events extends Task{
    private String startTime;
    private String endTime;

    public Events(String description, String startTime, String endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public String toString(){
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
