public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String taskInfo, String from, String to) {
        super(taskInfo);
        this.from = from;
        this.to = to;
    }

    @Override
    public void printTask() {
        System.out.print("[E]");
        super.printTask();
        System.out.println("(from: " + from + " to: " + to + ")");
    }
}
