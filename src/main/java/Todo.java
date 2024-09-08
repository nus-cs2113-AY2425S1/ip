public class Todo extends Task{

    public Todo(String taskDescription, char taskType){
        super(taskDescription,taskType);
    }

    @Override
    public String toString() {
        char maskingChar = isMarkedAsDone ? 'X' : ' ';
        return " [T][" + maskingChar + "] " + taskDescription;
    }
}
