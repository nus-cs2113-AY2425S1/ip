package UranusExceptions;

public class EmptyInputExceptions extends UranusExceptions {
    public EmptyInputExceptions() {
        super();
    }

    @Override
    public String getMessage() {
        return "Input cannot be empty!";
    }
}
