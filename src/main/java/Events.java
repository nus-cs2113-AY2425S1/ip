public class Events extends Task{

    protected String from;
    protected String to;

    public Events(String description){
        super(description, "E");
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
        String[] str = description.split("/");
        setFrom(str[1].substring("from".length()));
        setTo(str[2].substring("to".length()));
        this.description = description.substring(separatorIndex + 1, description.indexOf('/'))
                         + "(from:" + from + "to:" + to + ")";
    }
}
