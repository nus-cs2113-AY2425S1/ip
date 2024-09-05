public class Event extends Task{
    protected String fromDate;
    protected String toDate;

    public Event(String description, String fromDate, String toDate){
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to: %s)", status, super.getDescription(), getFromDate(), getToDate());
    }
}
