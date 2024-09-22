package TheThinker.Tasks;

public class Deadline extends Task{
    public String deadline;

    public Deadline(String taskDescription , String deadline){
        super(taskDescription , 'D')  ;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        char maskingChar = isMarkedAsDone ? 'X' : ' ';
        return " [" + taskType + "][" + maskingChar + "] " + taskDescription +" (by: " + deadline + ")";
    }

    @Override
    public String convertToFileFormat(){
        return "D" + " | " + isMarkedAsDone + " | " + taskDescription + " | " + deadline;
    }
}
