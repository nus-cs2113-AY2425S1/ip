package tasks;

/**
 * The {@code ToDo} class represents a basic task with no specific time constraints.
 * It extends the {@code Task} class and provides its own task type icon to distinguish it
 * from other task types such as {@code Deadline} and {@code Event}.
 */
public class ToDo extends Task {

    static String typeIcon = "[T]";

    /**
     * Constructs a new {@code ToDo} task with the specified name.
     *
     * @param newName the name or description of the {@code ToDo} task.
     */
    public ToDo(String newName) {
        super(newName);
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }


    /**
     * This method is irrelevant for {@code ToDo} tasks and returns {@code null},
     * as {@code ToDo} tasks do not have a due date.
     *
     * @return {@code null}, since {@code ToDo} tasks do not have deadlines.
     */
    @Override
    public String getBy() {
        return null;
    }

    /**
     * This method is irrelevant for {@code ToDo} tasks and returns {@code null},
     * as {@code ToDo} tasks do not have an event start time.
     *
     * @return {@code null}, since {@code ToDo} tasks do not involve events.
     */
    @Override
    public String getEventStart() {
        return null;
    }

    /**
     * This method is irrelevant for {@code ToDo} tasks and returns {@code null},
     * as {@code ToDo} tasks do not have an event end time.
     *
     * @return {@code null}, since {@code ToDo} tasks do not involve events.
     */
    @Override
    public String getEventEnd() {
        return null;
    }

}
