import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int currentTaskCount;
    private Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks, int currentTaskCount) {
        this.currentTaskCount = currentTaskCount;
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return currentTaskCount;
    }

    public void list() {
        ui.horizontalLine();
        if (currentTaskCount == 0) {
            System.out.println("You have no tasks right now YIPPEE!");
        }
        ui.printTaskList(currentTaskCount, tasks);
        ui.horizontalLine();
    }

    public void addTodo(String input) {
        try {
            tasks.add(new Todo(input, false));
            ui.printAddedTaskMessage(tasks, currentTaskCount);
            currentTaskCount++;
        } catch(LeginEmptyTaskException e) {
            ui.printExceptionMessage(e);
        }
    }

    public void addDeadline(String input) {
        try {
            tasks.add(new Deadline(input));
            ui.printAddedTaskMessage(tasks, currentTaskCount);
            currentTaskCount++;
        } catch (LeginEmptyTaskException | LeginMissingParamsException e) {
            ui.printExceptionMessage(e);
        }
    }

    public void addEvent(String input) {
        try {
            tasks.add(new Event(input));
            ui.printAddedTaskMessage(tasks, currentTaskCount);
            currentTaskCount++;
        } catch (LeginEmptyTaskException | LeginMissingParamsException e) {
            ui.printExceptionMessage(e);
        }
    }

    public void markTask(int index) {
        ui.horizontalLine();
        Task taskToMark = tasks.get(index - 1);
        taskToMark.markTask();
        ui.printMarkTaskMessage(index, taskToMark);
        ui.horizontalLine();
    }

    public void unmarkTask(int index) {
        ui.horizontalLine();
        Task taskToUnmark = tasks.get(index - 1);
        taskToUnmark.unmarkTask();
        ui.printUnmarkTaskMessage(index, taskToUnmark);
        ui.horizontalLine();
    }



    public void deleteTask(int index) {
        ui.horizontalLine();
        Task taskToRemove = tasks.get(index - 1);
        tasks.remove(index - 1);
        currentTaskCount--;
        ui.printDeleteTaskMessage(taskToRemove, currentTaskCount);
        ui.horizontalLine();
    }


}
