package TheThinker.Tasks;

/**
 * Represents a to-do type task. A to-do object contains
 * task type , task description.
 */
public class Todo extends Task{

    public Todo(String taskDescription){
        super(taskDescription, 'T');
    }

    @Override
    public String toString() {
        char maskingChar = isMarkedAsDone ? 'X' : ' ';
        return " [T][" + maskingChar + "] " + taskDescription;
    }

    @Override
    public String convertToFileFormat(){
        return "T" + " | " + isMarkedAsDone + " | " + taskDescription;
    }

    @Override
    public String getTaskDate(){
        return "empty";
    }
}
