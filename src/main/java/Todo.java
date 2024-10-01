public class Todo extends Task {
    public Todo() {
        this.name = "";
        this.done = false;
        this.symbol = "T";
    }

    public Todo(String name) {
        this.name = name;
        this.done = false;
        this.symbol = "T";
    }
}