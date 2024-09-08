public class EmptyTaskEntry extends RuntimeException{
    public EmptyTaskEntry(){
        super("Task cannot be empty! Please key in specifics of task.");
    }
}
