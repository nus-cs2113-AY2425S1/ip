package classes;

public class Todo extends Task {

    public Todo(String input) {
        super(input.substring(!input.contains(" ") ? -1 : input.indexOf(" ") + 1));
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
        return getTypeMarker() + super.toString();
    }
}
