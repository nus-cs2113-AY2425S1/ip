package TaskTypes;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description, TypeOfTask.ToDos);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
