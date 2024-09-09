public class Event extends Deadline{
    protected String to;

    public Event(String description, String from, String to) {
        super(description, from);
        this.to = to;
        this.type = 'E';
    }

   @Override
    public String getTo() {
        return " to: " + to + ") ";
    }
}

