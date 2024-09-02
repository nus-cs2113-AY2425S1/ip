public class Todo extends Task{

    /**
     * Constructor for Todo
     *
     * @param name Name of the todo.
     */
    public Todo(String name) {
        super(name);
        this.type = "T";
    }

    /**
     * Prints the todo and its status in one line.
     * Overwrites toString method in Java Object class.
     *
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
