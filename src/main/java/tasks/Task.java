package tasks;

public class Task {
    protected String command;
    protected boolean isDone;

    protected Task(String command, boolean isDone) {
        this.command = command;
        this.isDone = isDone;
    }

    protected Task(String command) {
        this.command = command;
        this.isDone = false;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void printTask() {
        String mark = (this.isDone) ? "X" : " ";
        System.out.printf("[%s] %s\n", mark, this.command);
    }
}
