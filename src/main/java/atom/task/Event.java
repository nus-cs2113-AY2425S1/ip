package atom.task;

public class Event extends Task{

    protected String startDate;
    protected String endDate;

    public Event(String item, String startDate, String endDate) {
        super(item);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public String setTaskType() {
        return "E";
    }
}
