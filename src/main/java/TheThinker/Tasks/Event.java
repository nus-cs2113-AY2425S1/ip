package TheThinker.Tasks;

/**
 * Represents an event type task. An event object contains
 * task type , task description ,
 * start time and end time of format dd/MMMM/yyyy h a.
 *
 */
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

    @Override
    public String convertToFileFormat(){
        return "E" + " | " + isMarkedAsDone + " | " + taskDescription + " | " + startTime + " | " + endTime;
    }

    public String getTaskDate(){
        return startTime;
    }
}
