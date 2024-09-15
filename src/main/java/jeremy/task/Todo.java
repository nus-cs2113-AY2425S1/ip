package jeremy.task;

import jeremy.exception.EmptyArgumentException;

public class Todo extends Task {
    public Todo(String description) throws EmptyArgumentException {
        super(description);

        if (description.isBlank()) {
            throw new EmptyArgumentException("Description cannot be empty");
        }

        this.icon = "T";
    }

    public Todo(String description, boolean isDone) throws EmptyArgumentException {
        super(description);

        if (description.isBlank()) {
            throw new EmptyArgumentException("Description cannot be empty");
        }

        this.isDone = isDone;
        this.icon = "T";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return icon + " | " + (isDone ? 1 : 0) + " | " + description;
    }
}