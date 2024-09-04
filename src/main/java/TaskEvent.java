public class TaskEvent extends Task {
    protected String startDate;
    protected String endDate;
    public TaskEvent(String taskDesc, String startDate, String endDate) {
        super(taskDesc);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String taskToString() {
        return "[E]" + super.taskToString() + ", from " + startDate + " to " + endDate;
    }
}
