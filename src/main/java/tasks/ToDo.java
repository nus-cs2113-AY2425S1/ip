package tasks;

public class ToDo extends Task {

    public ToDo(String command, boolean isDone) {
        super(command, isDone);
    }

    public ToDo(String command) {
        super(command);
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
        System.out.printf("[T][%s] %s\n", mark, super.command);
    }
}