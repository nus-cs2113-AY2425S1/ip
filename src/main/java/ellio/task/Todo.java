package ellio.task;

public class Todo extends Task{

    public Todo(String description, String isDone){
        super(description, isDone);
    }

    public String getTaskInfo(){
        return "[T]" + super.getTaskInfo();
    }

    public String getSaveFileTask(){
        return " t " + super.getSaveFileTask();
    }
}
