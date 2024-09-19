
public class Deadline extends Task {

    static String typeIcon = "[D]";

    String by;


    public Deadline(String newName, String newDeadline) {
        super(newName);
        this.by = newDeadline;
    }

    @Override
    public String getBy() {
        return by;
    }

    public void setBy(String newBy) {
        by = newBy;
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }
}
