package atom.tasklist;

import atom.task.Deadline;
import atom.task.Event;
import atom.task.Task;
import atom.task.Todo;

import java.util.ArrayList;

/**
 * Represents a task list that stores different types of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasksList;

    public TaskList() {
        tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Size of task list.
     */
    public int getTasksListSize() {
        return tasksList.size();
    }

    /**
     * Returns the task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Marks a specific task as done based on the given task id.
     *
     * @param id Task id of task.
     */
    public void markAsDone(int id) {
        Task currTask = tasksList.get(id);
        currTask.markAsDone();
    }

    /**
     * Marks a specific task as undone based on the given task id.
     *
     * @param id Task id of task.
     */
    public void markAsUndone(int id) {
        Task currTask = tasksList.get(id);
        currTask.markAsUndone();
    }

    /**
     * Adds a <code>todo</code> task to the task list.
     *
     * @param todoName Name of <code>todo</code> task.
     * @return <code>Todo</code> object.
     */
    public Todo addTodoTask(String todoName) {
        Todo todo = new Todo(todoName.trim());
        tasksList.add(todo);
        return todo;
    }

    /**
     * Adds a <code>todo</code> task to the task list, and prints
     * out a message to the user.
     * <p>
     * The message notifies the user that the <code>todo</code> task was added to the
     * list and updates the number of tasks in the task list.
     *
     * @param todoName Name of <code>todo</code> task.
     */
    public void addTodoTaskWithMessage(String todoName) {
        Todo todo = addTodoTask(todoName);

        System.out.println("Gotcha! TODO task added to list!");
        System.out.println("> [" + todo.setTaskType() + "]" + "[ ] " + todo.getItem());
        System.out.println("You now have " + tasksList.size() + " tasks in your list!");
    }

    /**
     * Adds a <code>deadline</code> task to the task list.
     *
     * @param deadlineName Name of <code>deadline</code> task.
     * @param dueDate Due date of <code>deadline</code> task.
     * @return <code>Deadline</code> object.
     */
    public Deadline addDeadlineTask(String deadlineName, String dueDate) {
        Deadline deadline = new Deadline(deadlineName.trim(), dueDate.trim());
        tasksList.add(deadline);
        return deadline;
    }

    /**
     * Adds a <code>deadline</code> task to the task list, and prints
     * out a message to the user.
     * <p>
     * The message notifies the user that the <code>deadline</code> task was added to the
     * list and updates the number of tasks in the task list.
     *
     * @param deadlineName Name of <code>deadline</code> task.
     * @param dueDate Due date of <code>deadline</code> task.
     */
    public void addDeadlineTaskWithMessage(String deadlineName, String dueDate) {
        Deadline deadline = addDeadlineTask(deadlineName, dueDate);

        System.out.println("Gotcha! DEADLINE task added to list");
        System.out.println("> [" + deadline.setTaskType() + "]" + "[ ] "
                + deadline.getItem() + " (by: " + deadline.getDueDate() + ")");
        System.out.println("You now have " + tasksList.size() + " tasks in your list!");
    }

    /**
     * Adds an <code>event</code> task to the task list.
     *
     * @param eventName Name of <code>event</code> task.
     * @param startDate Start date of <code>event</code> task.
     * @param endDate End date of <code>event</code> task.
     * @return <code>Event</code> object.
     */
    public Event addEventTask(String eventName, String startDate, String endDate) {
        Event event = new Event(eventName.trim(), startDate.trim(), endDate.trim());
        tasksList.add(event);
        return event;
    }

    /**
     * Adds an <code>event</code> task to the task list, and prints
     * out a message to the user.
     * <p>
     * The message notifies the user that the <code>event</code> task was added to the
     * list and updates the number of tasks in the task list.
     *
     * @param eventName Name of <code>event</code> task.
     * @param startDate Start date of <code>event</code> task.
     * @param endDate End date of <code>event</code> task.
     */
    public void addEventTaskWithMessage(String eventName, String startDate, String endDate) {
        Event event = addEventTask(eventName, startDate, endDate);

        System.out.println("Gotcha! EVENT task added to list");
        System.out.println("> [" + event.setTaskType() + "]" + "[ ] " + event.getItem()
                + " (from: " + event.getStartDate() + " to: " + event.getEndDate() + ")");
        System.out.println("You now have " + tasksList.size() + " tasks in your list!");
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskId Task id of task.
     */
    public void deleteTask(int taskId) {
        Task currTask = tasksList.get(taskId);
        tasksList.remove(currTask);
    }

    /**
     * Finds tasks containing the specified keyword and adds them
     * to a new task list.
     * <p>
     * Returns the new task list containing all matching tasks.
     *
     * @param keyword Keyword to search for in task name.
     * @return <code>ArrayList</code> containing tasks with keyword.
     */
    public ArrayList<Task> findTasksWithKeyword(String keyword) {
        ArrayList<Task> tasksFoundList = new ArrayList<>();
        keyword = keyword.trim();

        for (Task task : tasksList) {
            String taskName = task.getItem();

            if (taskName.contains(keyword)) {
                tasksFoundList.add(task);
            }
        }

        return tasksFoundList;
    }

    /**
     * Filters tasks by specified date and adds them
     * to a new task list.
     * <p>
     * Returns the new task list containing all tasks occurring on the
     * specified date.
     *
     * @param date Date to filter tasks by.
     * @return <code>ArrayList</code> containing tasks filtered by date.
     */
    public ArrayList<Task> filterTasksByDate(String date) {
        ArrayList<Task> tasksFoundList = new ArrayList<>();
        date = date.trim();

        for (Task task : tasksList) {
            if (task.setTaskType().equals("T")) {
                continue;
            }

            if (task.setTaskType().equals("D")) {
                String taskDueDate = task.getDueDate();
                String[] taskDueDateParams = taskDueDate.split(",");
                String taskDueDateWithoutTime = taskDueDateParams[0];

                if (taskDueDateWithoutTime.equals(date)) {
                    tasksFoundList.add(task);
                }

            } else if (task.setTaskType().equals("E")) {
                String taskStartDate = task.getStartDate();
                String[] taskStartDateParams = taskStartDate.split(",");
                String taskStartDateWithoutTime = taskStartDateParams[0];

                String taskEndDate = task.getEndDate();
                String[] taskEndDateParams = taskEndDate.split(",");
                String taskEndDateWithoutTime = taskEndDateParams[0];

                if (taskStartDateWithoutTime.equals(date) || taskEndDateWithoutTime.equals(date)) {
                    tasksFoundList.add(task);
                }
            }
        }

        return tasksFoundList;
    }
}
