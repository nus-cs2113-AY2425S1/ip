package Uranus.Tasks;

public class Events extends Task{

    protected String from;
    protected String to;
    private static final String EVENT_TAG = "E";
    private static final String SEPARATOR = "/";
    private static final String START_TIME = "from";
    private static final String END_TIME = "to";

    public Events(String description){
        super(description, EVENT_TAG);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void setDescription(String description){
        int separatorIndex = description.indexOf(' ');
        String[] str = description.split(SEPARATOR);
        setFrom(str[1].substring(START_TIME.length()));
        setTo(str[2].substring(END_TIME.length()));
        this.description = description.substring(separatorIndex + 1, description.indexOf(SEPARATOR))
                + "(" + START_TIME + ":" + from + END_TIME + ":" + to + ")";
    }
}
