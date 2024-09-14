package CassHelpers.types;

public class Deadline extends Task{
    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        setBy(by);
    }

    public String getBy(){
        return this.by;
    }

    public void setBy(String by){
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    public String toWritableString(){
        return "D" + super.toWritableString() + ","+this.by;
    }
}
