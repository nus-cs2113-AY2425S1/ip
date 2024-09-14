package nell.tasks;

public class ToDo extends nell.tasks.Task {

    public ToDo(String description) {
        super(description, "T");
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        this.type = "T";
    }

    public String toString() {
        return super.toString();
    }
}
