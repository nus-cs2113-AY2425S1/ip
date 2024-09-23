package Glendon;

import Glendon.task.Deadline;
import Glendon.task.Event;
import Glendon.task.Task;
import Glendon.task.Todo;
import java.util.ArrayList;
import static Glendon.Glendon.filePath;

public class TaskList {
    public static ArrayList<Task> taskList;
    public static int taskCounter = 0;

    TaskList() {
        this.taskList = new ArrayList<>();
        new Storage(filePath).createTaskFile();
    }

    TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
        taskCounter = tasks.size();
    }

    public boolean checkMark(String response) {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1; //getting the index of Task to be deleted
        if (taskList.get(taskIndex).isCompleted()) {
            return true;
        }
        return false;
    }

    public boolean checkUnmark(String response) {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1;
        if (taskList.get(taskIndex).isCompleted()) {
            return false;
        }
        return true;
    }

    public void deleteTask(String response) throws IndexOutOfBoundsException {
        int taskNumber = Integer.parseInt(response.split(" ")[1]);
        Task removedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
    }

    public void addEvent(String response) throws StringIndexOutOfBoundsException,ArrayIndexOutOfBoundsException {
        String[] answers = response.split("/");
        String taskInfo = answers[0].substring(6);
        String startDate = answers[1].substring(5);
        String endDate = answers[2].substring(3);
        taskList.add(new Event(taskInfo, startDate, endDate));
        taskCounter++;
    }

    public void addDeadline(String response) throws StringIndexOutOfBoundsException,ArrayIndexOutOfBoundsException {
        String[] answers = response.split("/");
        String taskInfo = answers[0].substring(9);
        String taskDeadline = answers[1].substring(3);
        taskList.add(new Deadline(taskInfo, taskDeadline));
        taskCounter++;
    }

    public void addTodo(String response) throws StringIndexOutOfBoundsException {
        String taskInfo = response.substring(5);
        taskList.add(new Todo(taskInfo));
        taskCounter++;
    }

    public void unmarkTask(String response) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1;
        taskList.get(taskIndex).setCompleted(false);
    }

    public void markTask(String response) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1; //getting the index of Task to be deleted
        taskList.get(taskIndex).setCompleted(true);
    }
}
