package joe;

public class Todo extends Task{
    public Todo(String itemDescription) {
        super(itemDescription);
    }

    public Todo(String itemDescription, boolean isTodo) {
        super(itemDescription, isTodo);
    }

    public static String extractDescription(String input) {
        return Task.extractDescription(input, "todo");
    }

    public static Todo readInTodo(String line) {
        boolean isToDo;
        String itemDescription;

        if (line.contains("[not done]")) {
            isToDo = false;
        } else {
            isToDo = true;
        }

        int startDescriptionIndex = line.indexOf("done]") + "done]".length();
        itemDescription = line.substring(startDescriptionIndex, line.length()).strip();

        return new Todo(itemDescription, isToDo);
    }
}
