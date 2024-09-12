package ellio.task;

public class Todo extends Task{

    public Todo(String description){
        super(description);
    }

    public String getTask(){
        return "[T]" + super.getTask();
    }
}
