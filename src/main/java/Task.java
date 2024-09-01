public class Task {
    protected String description;
    protected boolean isDone;
   // private int id;

    /*
    private char type;
    private String from;
    private String to;
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public void markDone() {
        this.isDone = true;
    }

    public void markDel() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    public String getType() {
        return "";
    }

    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }

//    public int getId() {
//        return id;
//    }

    /*
    public void setType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }



    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

     */

    /*
    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

     */

}
