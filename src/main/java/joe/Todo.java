package joe;

public class Todo extends Task{
    public Todo(String itemDescription) {
        super(itemDescription);
    }
    public static String extractDescription(String input) {
        return Task.extractDescription(input, "todo");
    }
}
