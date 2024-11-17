package CodyChen.Task;

public class Todo extends Task {
    protected char type;

    public Todo(String description) {
        super(description);
        this.type = 'T';
    }

    @Override
    public char getType() {
        return type;
    }
}
