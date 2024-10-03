import exception.InvalidCreateDeadlineException;
import exception.InvalidCreateEventException;

/**
 * Represent Events with a description, a <code>from</code> and a <code>to</code>field.
 */

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event object with the given description.
     *
     * @param description The description of the event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Constructs a new Event object with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Reformats user input to create a new event.
     *
     * @param userInput The user input for creating the event.
     * @throws InvalidCreateEventException If the input format is invalid.
     */
    public static void createNewEvent(String userInput) throws InvalidCreateEventException {
        String[] eventInfo = userInput.split("/from|/to");
        if (eventInfo.length == 3) {
            Event event = new Event(eventInfo[0], eventInfo[1].strip(), eventInfo[2].strip());
            TaskList.tasks.add(event);
            UI.printContent("Added Event: " + event.toString());
        } else {
            throw new InvalidCreateEventException();
        }
    }

    /**
     * Prints a message indicating that the event has been marked as done.
     */
    @Override
    public void printMark() {
        UI.printContent("Nice! You have done this event:\n\t" + this.toString());
    }

    /**
     * Prints a message indicating that the event has been unmarked.
     */
    @Override
    public void printUnmark() {
        UI.printContent("I have unmarked this event:\n\t" + this.toString());
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    
    /**
     * Generates a string for saving the event with Data Manager.
     *
     * @return A formatted string for saving the event.
     */
    @Override
    public String toSaveString() {
        return "E" + this.getStatusIcon() + "//" + this.description + "//" + this.from + "//" + this.to;
    }
}
