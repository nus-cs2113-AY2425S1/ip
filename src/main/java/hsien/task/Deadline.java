package hsien.task;


public class Deadline extends Task{
    protected String byDate;

    public Deadline(String description, String byDate){
        super(description);
        this.byDate = byDate;
    }

    public String getByDate() {
        return byDate;
    }

    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        return String.format("[D] [%s] %s (by: %s)", status, super.getDescription(), getByDate());
    }
}
