public class Deadline extends Task{

    protected String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public String getTask(){
        return "[D]" + super.getTask() + " (" + deadlineDate + ")";
    }
}
