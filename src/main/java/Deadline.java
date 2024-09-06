public class Deadline extends Task {
    final static String COMMAND_STRING = "deadline";
    final static String BY_KEYWORD_STRING = "/by";
    protected String by;

    public Deadline(String description) {
        super(getDescriptionFromString(description));
        setBy(getDayFromString(description));

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        Task.printNumberOfTasks();
    }

    public Deadline(String description, String by) {
        super(description);
        setBy(by);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        Task.printNumberOfTasks();
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public static String getDayFromString(String input) {

        if (input == null) {
            return null;
        }
        int indexOfBy = input.indexOf(BY_KEYWORD_STRING);

        return input.substring(indexOfBy + BY_KEYWORD_STRING.length()).trim();
    }

    public static String getDescriptionFromString(String input) {
        input = input.substring(0, input.indexOf(BY_KEYWORD_STRING));
        return input.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
