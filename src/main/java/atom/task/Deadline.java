package atom.task;

public class Deadline extends Task{

    protected String dueDate;

    public Deadline(String item, String dueDate) {
        super(item);
        this.dueDate = dueDate;
    }

    @Override
    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String setTaskType() {
        return "D";
    }
}
