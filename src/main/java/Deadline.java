public class Deadline  extends Task{
    private final String deadlineDate;

    public Deadline(String description, String deadlineDate){
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineDate);
    }
}
