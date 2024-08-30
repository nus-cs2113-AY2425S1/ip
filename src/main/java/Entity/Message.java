package Entity;

public class Message {
    private String message;
    private boolean isDone;

    public Message(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
