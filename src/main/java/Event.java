public class Event extends Task{
    public String startTime;
    public String endTime;

    public Event(String taskDescription , String startTime, String endTime){
        super(taskDescription , 'E');
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        char maskingChar = isMarkedAsDone ? 'X' : ' ';
        return " [" + taskType + "][" + maskingChar + "] " + taskDescription + " (from: " + startTime +" to: " + endTime + ")";
    }
}
