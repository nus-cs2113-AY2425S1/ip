public class Todo extends Task{
    public Todo(String input) {
        super(input.substring(input.indexOf(" ") + 1));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
