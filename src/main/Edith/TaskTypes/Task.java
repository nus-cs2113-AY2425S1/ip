package TaskTypes;

public class Task {
    private String description;
    private boolean isDone;
    public TypeOfTask typeOfTask;

    public Task(String description, TypeOfTask typeOfTask) {
        this.description = description;
        this.isDone = false;
        this.typeOfTask = typeOfTask; //has potential to create error
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return getIsDoneString() + description;
    }

    public String getIsDoneString() {
        if (isDone) return "[X]";
        else return "[ ]";
    }

    /*
    public String printTask(Task task) {

        return getTaskTypeString()+getIsDoneString() + task.getDescription();
    }
    */



    /*

    public String getTaskTypeString() {
        return switch (typeOfTask) {
            case Deadlines -> "[D]";
            case ToDos -> "[T]";
            case Events -> "[E]";
        } ;
    }

    */

}
