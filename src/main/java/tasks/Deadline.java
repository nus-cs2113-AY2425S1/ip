package tasks;

public class Deadline extends Task{
    private String deadline;

    public Deadline(String contents, String deadline) {
        super(contents);
        this.deadline = deadline;
    }

    @Override
    public String getStatusIcon(){
        return "[D]" + (getIsDone() ? "[X]" : "[ ]");
    }

    @Override
    public String fileFormat(){
        return ("D | " + (getIsDone() ? "+" : "-") + " | " + getTask() + " | by " + deadline);
    }

    public String getDeadline(){
        return deadline;
    }
}
