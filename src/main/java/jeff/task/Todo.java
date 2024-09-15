package jeff.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String fileContent() {
        return "T" + super.fileContent();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

