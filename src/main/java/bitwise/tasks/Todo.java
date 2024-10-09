package bitwise.tasks;

import bitwise.constants.Icons;

public class Todo extends Task {

    protected static final String symbol = Icons.ICON_TODO;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return symbol + " " + super.toString();
    }
}
