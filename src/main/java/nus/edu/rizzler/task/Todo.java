package nus.edu.rizzler.task;

public class Todo extends Task{

    public Todo(String taskName, Boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toCSV(){
        return String.format("T, %s", super.toCSV());
    }
}
