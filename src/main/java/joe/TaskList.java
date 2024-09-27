package joe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

/**
 * The TaskList class contains and manages operations on a mutable ArrayList of Task objects
 */

public class TaskList {

    private ArrayList<Task> toDoItemArrayList;

    public TaskList() {
        this.toDoItemArrayList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> toDoItemArrayList) {
        this.toDoItemArrayList = toDoItemArrayList;
    }

    /**
     * Adds an object of type Task to this object's mutable toDoItemArrayList
     * @param task the task to add to the list
     */
    public void addToList(Task task) {
        this.toDoItemArrayList.add(task);
        UI.printReply(task.getItemDescription(), "Added: ");
    }

    /**
     * Adds a task to this object's mutable toDoItemArrayList without user feedback
     * Used when reading data from a file into the current TaskList object
     * @param task the task to add to the list
     */
    public void loadToList(Task task) {
        this.toDoItemArrayList.add(task);
    }

    /**
     * Deletes the task in this object's mutable toDoItemArrayList with the inputted index
     * @param toDoNumber the index of the task that is to be deleted
     */
    public void deleteFromList(int toDoNumber) {
        Task itemToDelete = toDoItemArrayList.get(toDoNumber -1);
        this.toDoItemArrayList.remove(toDoNumber - 1);
        UI.printReply(itemToDelete.toString(), "Deleted:");
    }

    /**
     * Sets the todo field of a Task object to true
     * @param toDoNumber index of the Task object that is to be set to todo
     */
    public void markTask(int toDoNumber) {
        this.toDoItemArrayList.get(toDoNumber - 1).setToDo(false);
        UI.printReply(Integer.toString(toDoNumber), "Marked: ");
    }

    /**
     * Sets the todo field of a Task object to false
     * @param toDoNumber index of the Task object that is to be set to todo
     */
    public void unmarkTask(int toDoNumber) {
        this.toDoItemArrayList.get(toDoNumber - 1).setToDo(true);
        UI.printReply(Integer.toString(toDoNumber), "Unmarked: ");
    }

    public int size() {
        return this.toDoItemArrayList.size();
    }

    public String toTaskString(int toDoNumber) {
        return this.toDoItemArrayList.get(toDoNumber - 1).toString();
    }

    /**
     * Collects this object's Task objects in the toDoItemArrayList that contain a provided keyword
     * @param keyword the keyword for which all Tasks in the ToDoItemArrayList will be searched
     * @return Optional<TaskList></TaskList> a filtered Task List object whose toDoItemArrayList contains
     *     tasks whose task description include the keyword. Empty if no task description contains the keyword.
     */
    public Optional<TaskList> findTasksByKeyword(String keyword) {
        List<Task> filteredList = this.toDoItemArrayList.stream()
            .filter(task -> task.toString().contains(keyword))
            .toList();
        ArrayList<Task> filteredArrayList = new ArrayList<>();
        filteredArrayList.addAll(filteredList);
        if (filteredArrayList.size() > 0) {
            return Optional.of(new TaskList(filteredArrayList));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Collects this object's Tasks in the toDoItemArrayList that are do be done or commenced previous to dueDate
     * @param dueDate The date to which all Tasks deadlines are compared.
     *                If the Task is an instance of Deadline, the dueDate is compared to the deadlineDate
     *                If the Task is an instance of Event, the dueDate is compared to the startDate
     * @return TaskList A new Task List containing Tasks that are to be done at OR before the dueDate
     */
    public TaskList getDueTaskList(LocalDateTime dueDate) {
        List<Deadline> dueDeadlinesList = this.toDoItemArrayList.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline)task)
                .filter(task -> task.isDueBy(dueDate))
                .toList();
        List<Event> dueEventsList = this.toDoItemArrayList.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event)task)
                .filter(task -> task.isDueBy(dueDate))
                .toList();
        ArrayList<Task> dueList = new ArrayList<>();
        dueList.addAll(dueEventsList);
        dueList.addAll(dueDeadlinesList);
        return new TaskList(dueList);
    }
}
