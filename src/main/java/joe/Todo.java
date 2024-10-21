package joe;

/**
 * Subclass of Task. Representing Tasks of type Todo.
 */
public class Todo extends Task{
    /**
     * Constructs a new Todo object with default todo status true
     * @param itemDescription String describing the task
     */
    public Todo(String itemDescription) {
        super(itemDescription);
    }

    /**
     * Constructs a new Todo object with modifyable todo status
     * @param itemDescription String describing the task
     * @param isTodo boolean todo status of the task
     */
    public Todo(String itemDescription, boolean isTodo) {
        super(itemDescription, isTodo);
    }

    /**
     * Extracts tasks's description out of provided user input
     * @param input String user input
     * @return String task description
     */
    public static String extractDescription(String input) {
        return Task.extractDescription(input, "todo");
    }

    /**
     * Parses a String into a new Todo object
     * @param line String from which the Todo
     * @return new Todo object
     */
    public static Todo readInTodo(String line) {
        boolean isToDo;
        String itemDescription;

        if (line.contains("[not done]")) {
            isToDo = true;
        } else {
            isToDo = false;
        }

        int startDescriptionIndex = line.indexOf("done]") + "done]".length();
        itemDescription = line.substring(startDescriptionIndex, line.length()).strip();

        return new Todo(itemDescription, isToDo);
    }
}
