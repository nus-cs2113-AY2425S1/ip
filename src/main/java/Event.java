public class Event extends Task{
    public Event(String description){
        super(description);
    }

    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        String desc = super.getDescription().split("/from")[0];
        String[] temp = super.getDescription().split("/from")[1].split("/to");
        String from = temp[0];
        String to = temp[1];
        return String.format("[E][%s] %s (from:%s to:%s)", status, desc, from, to);
    }
}
