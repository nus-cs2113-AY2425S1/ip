public class Event extends Task {

    private String from;
    private String to;

    @Override
    public String toString() {
        return "[E]" + super.toString() + "from: " + from + " to: " + to;
    }
}
