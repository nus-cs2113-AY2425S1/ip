package tasks;

/**
 * Todo class acts as the basic class for tasks with
 * only a description: {@code String task} and a {@code boolean isDone}
 */
public class Todo extends Task {
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }
    public Todo(String task) {
        super(task);
    }
    public String getTypeMarker() {
        return "[T]";
    }
    @Override
    public void print() {
        System.out.print(getTypeMarker());
        super.print();
    }
    @Override
    public String toString() {
        return String.format("%s | %b | %s", getTypeMarker(), this.isDone, this.task);
    }
}
