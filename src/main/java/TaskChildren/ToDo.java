package TaskChildren;

import CustomExceptions.ToDoConstructorException;

public class ToDo extends Task{

    public ToDo(String inputString) throws ToDoConstructorException {
        super(inputString.replace("todo ", ""));
        if (this.taskString.isEmpty()){
            throw new ToDoConstructorException(inputString);
        }
        constructorMessage();
    }

    @Override
    public String checkboxString() {
        return "[T]" + super.checkboxString();
    }
}
