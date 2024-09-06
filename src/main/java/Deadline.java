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
        // String[] split = input.split(" ");
        // for (int i = 0; i < split.length; i++) {
        //     if (split[i].equals(BY_KEYWORD_STRING)) {
        //         return split[i + 1];
        //     }
        // }

        if (input == null) {
            return null;
        }
        int indexOfBy = input.indexOf(BY_KEYWORD_STRING);

        return input.substring(indexOfBy + BY_KEYWORD_STRING.length()).trim();

        // input = input.substring(input.indexOf(BY_KEYWORD_STRING), input.);
        // return input.trim();

        // String[] splitSentences = input.split(BY_KEYWORD_STRING);
        // return splitSentences[0].trim();
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
