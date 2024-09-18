package bean.task;

import bean.exceptions.InsufficientSpaceException;

public class Todo extends Task {

    public Todo(String description) throws InsufficientSpaceException {
        super(description);
    }

    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }

    @Override
    public String serialise() {
        return super.serialise();
    }

}
