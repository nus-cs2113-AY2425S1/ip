package UranusExceptions;

public class IllegalCommandException extends UranusExceptions {
    public IllegalCommandException() {
        super();
    }

    @Override
    public String getMessage() {
      return "No such commands exist. Do check what I can do again";
    }
}
