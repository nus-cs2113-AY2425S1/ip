package nell;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}
