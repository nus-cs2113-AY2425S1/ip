package tommi.Task;

public class ToDo extends Task {

    public ToDo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    public ToDo updateIsDone(boolean newIsDone) {
        return new ToDo(newIsDone, this.taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
