public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String printLine() {
        return "[T]" + super.printLine();
    }
}
