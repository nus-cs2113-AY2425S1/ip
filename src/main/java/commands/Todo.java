package commands;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getStatusIcon() {
        return ("[T]" + super.getStatusIcon());
    }

    @Override
    public String createTaskList() {
        return (getStatusIcon()+ " " +  description);
    }

    public String createTodoTxt() {
        return ("T | " + super.getStatus() + " | " + description);
    }
}
