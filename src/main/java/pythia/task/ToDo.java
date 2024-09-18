package pythia.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTxt() {
        return "T" + " | " + super.toTxt();
    }
}