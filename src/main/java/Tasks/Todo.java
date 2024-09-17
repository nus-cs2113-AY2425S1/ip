package Tasks;
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getPrefix(){
        return "[T]" + super.getPrefix();
    }
}
