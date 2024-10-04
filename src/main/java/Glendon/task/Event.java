package Glendon.task;

public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs the event with the given name, start and end date with default completion
     * status of the task is always set to false
     *
     * @param description - the description of the task
     * @param start - the start date of the task
     * @param end - the end date of the task
     *
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs the event task with the given completion indicator, where 1 indicates that the
     * task is completed and 0 indicates that the task has yet to be completed, given name,
     * start and end date
     *
     * @param completionIndicator an indicator to show if the task has been completed
     * @param description the description of the task
     * @param start the start date of the task
     * @param end the end date of the task
     */
    public Event(int completionIndicator, String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        if (completionIndicator == 1) {
            super.isCompleted = true;
        }
    }

    /**
     * Returns the String format of saving an event task
     *
     * @return the format for saving an event task
     */
    @Override
    public String saveToFile() {
        return "E|" + super.saveToFile() + "|" + start + "|" + end;
    }

    /**
     * Returns a string representation of the task type, [E] representing event task,
     * task description, completion status of the task, the start and end date.
     *
     * @return the task type, task info, completion status of task, the start and end date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + "to: " + end + ")";
    }
}
