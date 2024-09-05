public class Deadline extends Task{
    private String time;
    public Deadline(String command, boolean isDone, String time) {
        super(command, isDone);
        this.time = time;
    }

    public Deadline(String command, String time) {
        super(command);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        System.out.printf("[D][%s] %s (by: %s)\n", mark, super.command, this.time);
    }
}
