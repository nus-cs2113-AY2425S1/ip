package commands;

public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return ("[D]" + super.getStatusIcon());
    }
}
