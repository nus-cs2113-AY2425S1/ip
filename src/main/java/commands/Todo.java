package commands;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getStatusIcon() {
        return ("[T]" + super.getStatusIcon());
    }

    public String createTodoTxt() {
        return ("[T] | " + super.getStatus() + " | " + description);
    }
}
