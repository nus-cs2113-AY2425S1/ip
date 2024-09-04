public class Todo extends Task{

    public Todo(String input) {
        super(input.substring(input.indexOf(" ") + 1));
    }

    public String getTypeMarker() {
        return "[T]";
    }
    public void print() {
        System.out.print(getTypeMarker());
        super.print();
    }
}
