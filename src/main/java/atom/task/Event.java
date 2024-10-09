package atom.task;

/**
 * Contains attributes and methods specific to an <code>event</code> task.
 */
public class Event extends Task{

    private String startDate;
    private String endDate;

    public Event(String item, String startDate, String endDate) {
        super(item);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartDate() {
        return startDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEndDate() {
        return endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String setTaskType() {
        return "E";
    }
}
