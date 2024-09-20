public class Deadlines extends Task{
    private String by;
    Deadlines(String description, String by){
        super(description, TypeOfTask.Deadlines);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    public void setBy(String by){
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
