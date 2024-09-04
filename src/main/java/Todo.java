public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public String getTypeMarker() {
        return "[T]";
    }
    public void print() {
        System.out.print(getTypeMarker());
        super.print();
    }
}
