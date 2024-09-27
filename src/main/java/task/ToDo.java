package task;

public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    @Override
    public String getTaskMarker() {
        return "T";
    }

    @Override
    public String toFileFormat() {
        String status;
        if (isDone) {
            status = "1";
        } else {
            status = "0";
        }
        return "T | " + status + " | " + description;
    }
}
