package Tasks;

/**
 * Represents a todo task that the user wants to take note of, with no deadline or duration specified.
 */
public class Todo extends Task{
    public Todo(String description){
        super(description);
    }
    public String toString(){
        return "[T]" + super.toString() ;
    }
}
