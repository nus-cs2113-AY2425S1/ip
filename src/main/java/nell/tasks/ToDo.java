package nell.tasks;

public class ToDo extends nell.tasks.Task {

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public String toString() {
        return super.toString();
    }
}
