package classes;

public class Todo extends Task {

    public Todo(String input) {
        super(input.substring(!input.contains(" ") ? -1 : input.indexOf(" ") + 1));
    }

    public Todo(String task, boolean isDone) {
        super(task, isDone);
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
