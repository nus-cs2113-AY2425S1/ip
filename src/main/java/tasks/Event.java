package tasks;

public class Event extends Task {
    private String from;
    private String to;


    public Event(String command, boolean isDone, String from, String to) {
        super(command, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String command, String from, String to) {
        super(command);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getCommand() {
        return super.getCommand();
    }

    @Override
    public void setCommand(String command) {
        super.setCommand(command);
    }

    @Override
    public boolean isDone() {
        return super.isDone();
    }

    @Override
    public void setDone(boolean done) {
        super.setDone(done);
    }

    @Override
    public void printTask() {
        String mark = (super.isDone) ? "X" : " ";
        System.out.printf("[E][%s] %s (from: %s to: %s)\n",
            mark, super.command, this.from, this.to);

    }
}
