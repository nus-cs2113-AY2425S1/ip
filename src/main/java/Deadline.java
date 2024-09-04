public class Deadline extends Task{
    public Deadline(String description){
        super(description);
    }

    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        String date = super.getDescription().split("/by")[1];
        String desc = super.getDescription().split("/by")[0];
        return String.format("[D][%s] %s (by:%s)", status, desc, date);
    }
}
