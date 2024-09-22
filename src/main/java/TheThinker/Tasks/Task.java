package TheThinker.Tasks;

public class Task {
    public String taskDescription;
    public boolean isMarkedAsDone;
    public char taskType;

    public Task(String taskDescription, char taskType) {
        this.taskDescription = taskDescription;
        this.isMarkedAsDone = false;
        this.taskType = taskType;
    }

    public void setMarkedAsDone(boolean markedAsDone) {
        isMarkedAsDone = markedAsDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public char getTaskType() {
        return taskType;
    }

    public String toString() {
        char maskingChar = isMarkedAsDone ? 'X' : ' ';
        return " [" + taskType + "][" + maskingChar + "] " + taskDescription;
    }

    public String convertToFileFormat(){
        return "NIL" + " | " + isMarkedAsDone + " | " + taskDescription;
    }
}
