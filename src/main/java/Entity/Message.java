package Entity;

import java.time.LocalDate;

public class Message {
    private int type; //1: todo, 2: ddl, 3: event
    private String message;
    private boolean isDone;
    private String startTime;
    private String endTime;

    //todo
    public Message(String message) {
        this.message = message;
        this.isDone = false;
        this.type = 1;
    }

    //event
    public Message(String message, String startTime, String endTime, int type) {
        this.message = message;
        this.isDone = false;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    //ddl
    public Message(String message, String endTime, int type) {
        this.message = message;
        this.isDone = false;
        this.endTime = endTime;
        this.type = type;
    }

    //retrieve
    public Message(String message, boolean isDone, String startTime, String endTime, int type) {
        this.message = message;
        this.isDone = false;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {

        this.endTime = endTime;
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
