package tasks;
public class ToDo extends Task {

    // Constructor for Todo
    public ToDo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString(); // Add [T] tag for to-dos
    }
}
