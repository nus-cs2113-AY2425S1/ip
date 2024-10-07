package pythia.utility;

import pythia.task.Deadline;
import pythia.task.Event;
import pythia.task.Task;
import pythia.task.ToDo;

/**
 * A visitor class responsible for converting various task objects ({@link Task}, {@link ToDo},
 * {@link Deadline}, {@link Event}) into their respective string representations. The resulting
 * string format is suitable for storage in text files.
 */
public class WriteVisitor {

    /**
     * Generates a string representation of a generic task.
     *
     * @param task The task to be converted to a string.
     * @return A string representing the task in the format "doneStatus | taskName".
     */
    public String visitTask(Task task) {
        return task.getDone() + " | " + task.getName();
    }

    /**
     * Generates a string representation of a {@link ToDo} task.
     *
     * @param toDo The {@link ToDo} task to be converted to a string.
     * @return A string representing the {@link ToDo} task in the format "T | doneStatus | taskName".
     */
    public String visitToDo(ToDo toDo) {
        return "T" + " | " + visitTask(toDo);
    }

    /**
     * Generates a string representation of an {@link Event} task, including its start
     * and end dates.
     *
     * @param event The {@link Event} task to be converted to a string.
     * @return A string representing the {@link Event} task in the format
     *         "E | doneStatus | taskName | startDate | endDate".
     */
    public String visitEvent(Event event) {
        return "E" + " | " + visitTask(event) + " | " + event.getStartDate() + " | " + event.getEndDate();
    }

    /**
     * Generates a string representation of a Deadline task, including its due date.
     *
     * @param deadline The {@link Deadline} task to be converted to a string.
     * @return A string representing the {@link Deadline} task in the format
     *         "D | doneStatus | taskName | dueDate".
     */
    public String visitDeadline(Deadline deadline) {
        return "D" + " | " + visitTask(deadline) + " | " + deadline.getDueDate();
    }
}
