package joe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> toDoItemArrayList;

    public TaskList() {
        this.toDoItemArrayList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> toDoItemArrayList) {
        this.toDoItemArrayList = toDoItemArrayList;
    }

    public void addToList(Task task) {
        this.toDoItemArrayList.add(task);
        UI.printReply(task.getItemDescription(), "Added: ");
    }

    public void loadToList(Task task) {
        this.toDoItemArrayList.add(task);
    }

    public void deleteFromList(int toDoNumber) {
        Task itemToDelete = toDoItemArrayList.get(toDoNumber -1);
        this.toDoItemArrayList.remove(toDoNumber - 1);
        UI.printReply(itemToDelete.toString(), "Deleted:");
    }

    public void markTask(int toDoNumber) {
        this.toDoItemArrayList.get(toDoNumber - 1).setToDo(true);
        UI.printReply(Integer.toString(toDoNumber), "Marked: ");
    }

    public void unmarkTask(int toDoNumber) {
        this.toDoItemArrayList.get(toDoNumber - 1).setToDo(false);
        UI.printReply(Integer.toString(toDoNumber), "Unmarked: ");
    }

    public int size() {
        return this.toDoItemArrayList.size();
    }

    public String toTaskString(int toDoNumber) {
        return this.toDoItemArrayList.get(toDoNumber - 1).toString();
    }

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
