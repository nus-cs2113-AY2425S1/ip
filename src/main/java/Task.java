public class Task {
    private final String description;
    private boolean isDone;
    public TypeOfTask typeOfTask;

    public Task(String description, TypeOfTask typeOfTask) {
        this.description = description;
        this.isDone = false;
        this.typeOfTask = typeOfTask; //has potential to create error
    }

    protected String getDescription() {
        return description;
    }

    protected boolean getIsDone() {
        return isDone;
    }
    protected void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return getIsDoneString() + description;
    }

    private String getIsDoneString() {
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
