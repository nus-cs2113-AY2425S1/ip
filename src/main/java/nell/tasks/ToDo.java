package nell.tasks;

import java.time.LocalDate;

public class ToDo extends nell.tasks.Task {

    public ToDo(String description) {
        super(description, "T");
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        this.type = "T";
    }

    public String toString() {
        return super.toString();
    }

    /**
     * Returns true if the given task occurs on a given date.
     * Always returns false as ToDo tasks are undated.
     *
     * @param date The specified date
     * @return False in all cases
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }
}
