public class Deadline extends Task{
    protected String by;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline() {
        super();
    }

    public String getBy() {
        return by;
    }
}
