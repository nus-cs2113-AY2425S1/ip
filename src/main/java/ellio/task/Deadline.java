package ellio.task;

public class Deadline extends Task{

    protected String deadlineDate;

    public Deadline(String description, String isDone, String deadlineDate) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    public String getTaskInfo(){
        return "[D]" + super.getTaskInfo() + " (" + deadlineDate + ")";
    }

    public String getSaveFileTask(){
        return " d " + super.getSaveFileTask() + " | " + deadlineDate;
    }
}
