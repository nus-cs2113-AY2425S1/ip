package cristiano.goals;

public class Todo extends Goal {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Todo fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        String description = parts[2];
        Todo todo = new Todo(description);
        if (parts[1].equals("1")) {
            todo.isDone = true;
        }
        return todo;
    }
}
