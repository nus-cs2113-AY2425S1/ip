/**
 * Nearly identical to the Task abstract class.
 * Includes the symbol "T" for todo.
 */
public class Todo extends Task {
    public Todo() {
        this.name = "";
        this.isDone = false;
        this.symbol = "T";
    }

    public Todo(String name) {
        this.name = name;
        this.isDone = false;
        this.symbol = "T";
    }
}