package pythia.utility;

import pythia.task.Deadline;
import pythia.task.Event;
import pythia.task.Task;
import pythia.task.ToDo;

public class WriteVisitor {
    public String visitTask(Task task) {
        return task.getDone() + " | " + task.getName();
    }

    public String visitToDo(ToDo toDo) {
        return "T" + " | " + visitTask(toDo);
    }

    public String visitEvent(Event event) {
        return "E" + " | " + visitTask(event) + " | " + event.getStartDate() + " | " + event.getEndDate();
    }

    public String visitDeadline(Deadline deadline) {
        return "D" + " | " + visitTask(deadline) + " | " + deadline.getDueDate();
    }
}
