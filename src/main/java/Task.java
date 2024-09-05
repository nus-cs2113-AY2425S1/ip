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

    protected String getCommand() {
        return command;
    }

    protected void setCommand(String command) {
        this.command = command;
    }

    protected boolean isDone() {
        return isDone;
    }

    protected void setDone(boolean done) {
        isDone = done;
    }

    protected void printTask() {
        String mark = (this.isDone) ? "X" : " ";
        System.out.printf("[%s] %s\n", mark, this.command);
    }
}
