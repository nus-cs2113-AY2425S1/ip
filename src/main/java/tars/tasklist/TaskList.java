package tars.tasklist;

import tars.tarsexception.TarsException;
import tars.task.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    // 构造函数，初始化一个空的任务列表
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    // 获取任务列表
    public List<Task> getTasks() {
        return tasks;
    }

    // 添加任务到任务列表
    public void addTask(Task task) {
        tasks.add(task);
    }

    // 获取指定索引的任务，如果索引超出范围，抛出异常
    public Task getTask(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("Task number out of range.");
        }
        return tasks.get(index);
    }

    // 删除指定索引的任务，并返回该任务；如果索引超出范围，抛出异常
    public Task deleteTask(int index) throws TarsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TarsException("Task number out of range.");
        }
        return tasks.remove(index);
    }

    // 返回任务列表中的任务总数
    public int getTaskCount() {
        return tasks.size();
    }

    // 检查是否已有相同的任务，避免重复添加
    public boolean hasTask(Task task) {
        for (Task existingTask : tasks) {
            if (existingTask.equals(task)) {
                return true;  // 如果发现重复任务，返回 true
            }
        }
        return false;  // 如果没有重复任务，返回 false
    }

    public List<Task> findTasksContainingKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
