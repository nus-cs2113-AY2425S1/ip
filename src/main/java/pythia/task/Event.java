package pythia.task;

import pythia.utility.WriteVisitor;

import java.util.Date;

public class Event extends Task {
    private String startDate;
    private Date startDateDay;
    private String endDate;
    private Date endDateDay;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String name, boolean isDone, String startDate, String endDate) {
        super(name, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + startDate + " to " + endDate + ")";
    }

    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitEvent(this);
    }
}
