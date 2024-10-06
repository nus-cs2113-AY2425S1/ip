package data;

import tasks.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private final int DEFAULT_TASKS = 100;
    private final ArrayList<Task> taskList;

    /**
     * Creates a new instance of the TaskList class with default values.
     */
    public TaskList() {
        taskList = new ArrayList<>(DEFAULT_TASKS);
    }

    /**
     * Creates a new instance of the TaskList class with a given list of tasks.
     *
     * @param taskList ArrayList of tasks to be used as the starting taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return size of TaskList
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Deletes the task at the given (1-indexed) index.
     *
     * @param idx 1-indexed index to delete task at
     */
    public void deleteTask(int idx) {
        int zeroIndexedIdx = idx - 1;
        Task toDelete = taskList.get(zeroIndexedIdx);
        taskList.remove(zeroIndexedIdx);
        System.out.println(Ui.DIVIDER + "Noted. I've removed this task: \n" + toDelete.toString() + "\nNow you have "
                + taskList.size() + " tasks in this list.\n" + Ui.DIVIDER);
    }

    /**
     * Prints every element of the TaskList.
     */
    public void printList() {
        System.out.print(Ui.DIVIDER);
        int curIdx = 0;
        System.out.println("Here are the tasks in your list:");
        while (curIdx != taskList.size()) {
            Task curTask = taskList.get(curIdx);
            int oneIndexedIdx = curIdx + 1;
            System.out.println(oneIndexedIdx + ". " + curTask.toString());
            curIdx++;
        }
        System.out.print(Ui.DIVIDER);

    }

    /**
     * Adds the given task to the TaskList.
     *
     * @param toAdd Task to be added to the list
     */
    public void addToList(Task toAdd) {
        taskList.add(toAdd);
        System.out.print(Ui.DIVIDER + "added: " + toAdd + "\n" + Ui.DIVIDER);
    }

    /**
     * Returns a copy of the underlying ArrayList of the TaskList.
     *
     * @return copy of the ArrayList of tasks used in the TaskList
     */
    public ArrayList<Task> getTaskListCopy() {
        return new ArrayList<Task>(taskList);
    }

    /**
     * Returns the task at the given index in the TaskList.
     *
     * @param idx index of task to be returned
     * @return Task at the given index
     */
    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    /**
     * Return the list of tasks that contain the given keyword.
     *
     * @param keyword keyword to search for
     * @return ArrayList of tasks containing the given keyword
     */
    public ArrayList<ArrayList<Object>> findTasks(String keyword) {
        ArrayList<ArrayList<Object>> taskAndIdxList = new ArrayList<>();
        int i = 0;
        for (Task curTask : taskList) {
            i++;
            if (curTask.toString().contains(keyword)) {
                Object[] taskAndIdx = {curTask, i};
                taskAndIdxList.add(new ArrayList<Object>(Arrays.asList(taskAndIdx)));
            }
        }
        return taskAndIdxList;
    }
}