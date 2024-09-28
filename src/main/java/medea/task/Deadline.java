package medea.task;

public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String toCSV(){
        return String.format("D,%s,%s", super.toCSV(), by);
    }
}
