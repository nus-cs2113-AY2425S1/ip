public class Event extends Task {

    final static String COMMAND_STRING = "event";
    final static String FROM_KEYWORD_STRING = "/from";
    final static String TO_KEYWORD_STRING = "/to";

    protected String from;
    protected String to;

    public Event(String description) {
        super(getDescriptionFromString(description));
        setFrom(getFromFromString(description));
        setTo(getToFromString(description));

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        Task.printNumberOfTasks();

    }

    public Event(String description, String from, String to) {
        super(description);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static String getDescriptionFromString(String input) {
        int indexOfFrom = input.indexOf(FROM_KEYWORD_STRING);
        input = input.substring(0, indexOfFrom);
        return input.trim();
    }

    public static String getFromFromString(String input) {

        if (input == null) {
            return null;
        }

        int indexOfFrom = input.indexOf(FROM_KEYWORD_STRING);
        int indexOfTo = input.indexOf(TO_KEYWORD_STRING);

        return input.substring(indexOfFrom + FROM_KEYWORD_STRING.length(), indexOfTo).trim();

        // String[] split = input.split(" ");
        // for (int i = 0; i < split.length; i++) {
        //     if (split[i].equals(FROM_KEYWORD_STRING) && split[i + 2].equals(TO_KEYWORD_STRING)) {
        //         return split[i + 1];
        //     } else if (split[i].equals(FROM_KEYWORD_STRING)) {
        //         return split[i + 1] + ' ' + split[i + 2];
        //     }
        // }

    }

    public static String getToFromString(String input) {

        // String[] split = input.split(" ");
        // for (int i = 0; i < split.length; i++) {
        //     if (split[i].equals(TO_KEYWORD_STRING)) {
        //         return split[i + 1];
        //     }
        // }
        // return null;

        if (input == null) {
            return null;
        }

        int indexOfTo = input.indexOf(TO_KEYWORD_STRING);

        return input.substring(indexOfTo + TO_KEYWORD_STRING.length()).trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
