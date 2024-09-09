public class Todo extends Task{

    public Todo(String taskDescription){
        super(taskDescription, 'T');
    }

    @Override
    public String toString() {
        char maskingChar = isMarkedAsDone ? 'X' : ' ';
        return " [T][" + maskingChar + "] " + taskDescription;
    }
}
