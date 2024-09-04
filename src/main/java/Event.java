import java.util.Date;

public class Event extends Task {
    private Date startDate;
    private Date endDate;

    public Event(String name, boolean status, Date startDate, Date endDate) {
        super(name, status);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
