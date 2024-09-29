package exceptions;

public class XiaoMeException extends Throwable {
    String error;

    public XiaoMeException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
