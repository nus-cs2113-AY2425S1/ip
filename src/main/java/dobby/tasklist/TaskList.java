package dobby.tasklist;

import dobby.exceptions.MissingDescriptionException;
import dobby.exceptions.TaskAlreadyMarkedException;
import dobby.exceptions.TaskAlreadyUnmarkedException;
import dobby.tasks.Task;
import dobby.ui.Ui;
import dobby.storage.Storage;

import java.util.ArrayList;


public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void addTaskFromCommand(String line, Ui ui, Storage storage) throws MissingDescriptionException {
        Task task = TaskCreator.createTask(line);
        if (task != null) {
            addTask(task);
            Ui.printTaskAddedMessage(task, size());
            storage.saveTasks(taskList);
        }
    }

    public void deleteTask(String line, Ui ui) {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)) {
            Task t = taskList.get(taskNumber - 1);
            taskList.remove(t);
            ui.printSeparator();
            System.out.println("    Dobby is removing this task:");
            System.out.println("        " + t);
            System.out.println("    Dobby says master has " + size() + " remaining tasks!");
            ui.printSeparator();
        }
    }

    public void markTaskAsDone(String line, Ui ui, Storage storage) throws TaskAlreadyMarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)){
            Task task = taskList.get(taskNumber - 1);
            if (task.isDone()){
                throw new TaskAlreadyMarkedException();
            }
            task.markAsDone();
            ui.printTaskStatus("done", task);
            storage.saveTasks(taskList);
        }
    }

    public void unmarkTaskAsDone(String line, Ui ui, Storage storage) throws TaskAlreadyUnmarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)) {
            Task task = taskList.get(taskNumber-1);
            if (!task.isDone()){
                throw new TaskAlreadyUnmarkedException();
            }
            task.unmarkAsDone();
            ui.printTaskStatus("incomplete", task);
            storage.saveTasks(taskList);
        }
    }

    public void findTasks(String line, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keyword = line.substring(line.indexOf("find") + 5).trim();

        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.printSeparator();
            ui.showMessage("Dobby found no tasks containing: " + keyword);
            ui.printSeparator();
        } else {
            ui.printSeparator();
            ui.showMessage("Here are the matching tasks in master's list: ");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.printTask(i+1, matchingTasks.get(i));
            }
            ui.printSeparator();
        }
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= taskList.size();
    }

}
