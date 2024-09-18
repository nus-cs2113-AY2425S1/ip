package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;

public class ToDo extends Task {
    public ToDo(String description) throws Exception {
        super(description.trim());
        this.type = "T";
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
        } catch (DescriptionEmpty e) {
            printEmptyDescriptionError();
            throw new Exception("Description is empty");
        }
    }

    public ToDo(String description, boolean isDone) {
        super(description);
        this.type = "T";
        this.isDone = isDone;
    }

    public String printLine() {
        return "[T]" + super.printLine();
    }
}
