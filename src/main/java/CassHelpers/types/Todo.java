package CassHelpers.types;

public class Todo extends Task{

    public Todo(String taskName){
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toWritableString(){
        return "T"+super.toWritableString();
    }
}
