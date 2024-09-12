package atom.task;

public class Deadline extends Task{

    String by;

    public Deadline(String item, String by) {
        super(item);
        this.by = by;
    }

    @Override
    public String getBy() {
        return by;
    }

    @Override
    public String setTaskType() {
        return "D";
    }
}
