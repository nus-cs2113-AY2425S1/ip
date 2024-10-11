package task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markTask(boolean isDone){
        this.isDone = isDone;
    }
    public void markAsDone(){
        isDone = true;
    }
    public void markAsUndone(){
        isDone = false;
    }
    public String toString(){
        return("[" + getStatusIcon() + "] " + description);
    }
    public String saveAsString(){
        return(String.join(" | ", new String[]{description, String.valueOf(isDone)}));
    }
    public static Task fromStorage(String data){
        String[] dataArr = data.split(" \\| ");
        String type = dataArr[0];
        String description = dataArr[1];
        boolean isDone = Boolean.parseBoolean(dataArr[2]);
        switch(type){
            case "T":
                return new Todo(description, isDone);
            case "D":
                return new Deadline(description, isDone, dataArr[3]);
            case "E":
                return new Event(description, isDone, dataArr[3], dataArr[4]);
            default:
                return null;
        }
    }
}
