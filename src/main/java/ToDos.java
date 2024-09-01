public class ToDos extends Task{
    public ToDos(String description) {
        super(description, TypeOfTask.ToDos);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
