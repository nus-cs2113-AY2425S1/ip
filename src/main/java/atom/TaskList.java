package atom;

import atom.task.Deadline;
import atom.task.Event;
import atom.task.Task;
import atom.task.Todo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasksList;

    public TaskList() {
        tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public int getTasksListSize() {
        return tasksList.size();
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public void markAsDone(int id) {
        Task currTask = tasksList.get(id);
        currTask.markAsDone();
    }

    public void markAsUndone(int id) {
        Task currTask = tasksList.get(id);
        currTask.markAsUndone();
    }

    public Todo addTodoTask(String todoName) {
        Todo todo = new Todo(todoName.trim());
        tasksList.add(todo);
        return todo;
    }

    public void addTodoTaskWithMessage(String todoName) {
        Todo todo = addTodoTask(todoName);

        System.out.println("Gotcha! TODO task added to list!");
        System.out.println("> [" + todo.setTaskType() + "]" + "[ ] " + todo.getItem());
        System.out.println("You now have " + tasksList.size() + " tasks in your list!");
    }

    public Deadline addDeadlineTask(String deadlineName, String dueDate) {
        Deadline deadline = new Deadline(deadlineName.trim(), dueDate.trim());
        tasksList.add(deadline);
        return deadline;
    }

    public void addDeadlineTaskWithMessage(String deadlineName, String dueDate) {
        Deadline deadline = addDeadlineTask(deadlineName, dueDate);

        System.out.println("Gotcha! DEADLINE task added to list");
        System.out.println("> [" + deadline.setTaskType() + "]" + "[ ] "
                + deadline.getItem() + " (by: " + deadline.getDueDate() + ")");
        System.out.println("You now have " + tasksList.size() + " tasks in your list!");
    }

    public Event addEventTask(String eventName, String startDate, String endDate) {
        Event event = new Event(eventName.trim(), startDate.trim(), endDate.trim());
        tasksList.add(event);
        return event;
    }

    public void addEventTaskWithMessage(String eventName, String startDate, String endDate) {
        Event event = addEventTask(eventName, startDate, endDate);

        System.out.println("Gotcha! EVENT task added to list");
        System.out.println("> [" + event.setTaskType() + "]" + "[ ] " + event.getItem()
                + " (from: " + event.getStartDate() + " to: " + event.getEndDate() + ")");
        System.out.println("You now have " + tasksList.size() + " tasks in your list!");
    }

    public void deleteTask(int taskId) {
        Task currTask = tasksList.get(taskId);
        tasksList.remove(currTask);
    }
}
