package Commands;

/**
 * Represents an exception where the user does not key in any task details.
 */
public class EmptyTaskEntry extends RuntimeException{
    public EmptyTaskEntry(){
        super("Task cannot be empty! Please key in specifics of task.");
    }
}
