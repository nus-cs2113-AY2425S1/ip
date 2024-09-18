package ellio.task;

public class Todo extends Task{

    public Todo(String description, String isDone){
        super(description, isDone);
    }

    public String getTask(){
        return "[T]" + super.getTask();
    }

    public String getSaveFileTask(){
        return " t " + super.getSaveFileTask();
    }
}
