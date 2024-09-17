package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;
import freedom.exceptions.TimeEmpty;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description) throws Exception {
        super(description);
        final int DESCRIPTION_INDEX = 0;
        final int FROM_INDEX = 1;
        final int TO_INDEX = 2;

        String[] components = description.split("/from|/to");
        updateDescription(components[DESCRIPTION_INDEX].trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
            setFrom(components[FROM_INDEX].trim());
            setTo(components[TO_INDEX].trim());
            if (getFrom().isEmpty() || getTo().isEmpty()) {
                throw new TimeEmpty();
            }
        } catch (DescriptionEmpty e) {
            printEmptyDescriptionError();
            throw new Exception("Description is empty");
        } catch (TimeEmpty | ArrayIndexOutOfBoundsException e) {
            System.out.print(LOGO);
            System.out.print("""
                    \tYou need to set a duration!
                    \tRemember:
                    \t  Use /from and /to before the date/time
                    \t  Include a date/time span
                    """);
            System.out.println(LOGO);
            throw new Exception("No from/to time given");
        }
    }

    public Event(String description, boolean isDone, String from, String to) throws Exception {
        super(description);
        this.isDone = isDone;
        setFrom(from);
        setTo(to);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String printLine() {
        return "[E]" + super.printLine() + " (from: " + from + " to: " + to + ")";
    }
}
