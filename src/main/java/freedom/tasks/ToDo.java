package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;

public class ToDo extends Task {
    public ToDo(String description) throws Exception {
        super(description.trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
        } catch (DescriptionEmpty e) {
            ui.printEmptyDescriptionError();
            throw new Exception("Description is empty");
        }
    }

    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String generateTaskLine() {
        return "[T]" + super.generateTaskLine();
    }

    public String generateStorageLine() {
        return "T | " + super.generateStorageLine() + "\n";
    }
}
