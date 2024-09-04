public class Todo extends Task{
    public Todo(String description){
        super(description);
    }

    public String getStatusDescription() {
        return "[T]" + super.getStatusDescription();
    }
}
