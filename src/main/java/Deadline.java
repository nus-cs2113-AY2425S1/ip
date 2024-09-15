public class Deadline extends Todo{

    protected String from;

    public Deadline(String description, String from) {
        super(description);
        this.from = from;
        this.type = 'D';
    }

    @Override
    public String getFrom() {
        return from;
    }
}