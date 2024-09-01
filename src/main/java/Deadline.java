public class Deadline extends Task{
    protected String by;
    public Deadline(String input) {
        super(input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1));
        int startingIndexOfDueDate =  input.indexOf("/by") + 4;
        this.by = input.substring(startingIndexOfDueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
