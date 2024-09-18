package ellio.task;

public class Deadline extends Task{

    protected String deadlineDate;

    public Deadline(String description, String isDone, String deadlineDate) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    public String getTask(){
        return "[D]" + super.getTask() + " (" + deadlineDate + ")";
    }

    public String getSaveFileTask(){
        return " d " + super.getSaveFileTask() + " | " + deadlineDate;
    }
}
