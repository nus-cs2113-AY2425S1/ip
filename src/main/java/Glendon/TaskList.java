package Glendon;

import Glendon.task.Deadline;
import Glendon.task.Event;
import Glendon.task.Task;
import Glendon.task.Todo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static Glendon.Glendon.filePath;

public class TaskList {
    public static ArrayList<Task> taskList;
    public static int taskCounter = 0;
    public static UI ui = new UI();

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
        taskList.remove(taskNumber - 1);
    }

    public void addEvent(String response) throws StringIndexOutOfBoundsException,ArrayIndexOutOfBoundsException {
        String[] answers = response.split("/from ");
        String taskInfo = answers[0].substring(6);
        String[] dates = answers[1].split("/to ");
        String startDateTime = dates[0];
        String endDateTime = dates[1];
        if (taskInfo.length() == 0) {
            throw new GlendonException();
        }
        taskList.add(new Event(taskInfo, startDateTime, endDateTime));
        taskCounter++;
    }

    public void addDeadline(String response) throws GlendonException {
        String[] answers = response.split("/by ");
        String taskInfo = answers[0].substring(9);
        if (taskInfo.length() == 0) {
            throw new GlendonException();
        }
        String taskDeadline = answers[1];
        taskList.add(new Deadline(taskInfo, taskDeadline));
        taskCounter++;
    }

    public void addTodo(String response) throws StringIndexOutOfBoundsException {
        String taskInfo = response.substring(5);
        if (taskInfo.length() == 0) {
            throw new GlendonException();
        }
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

    public void findTask(String wantedTask) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskList) {
            String taskName = task.taskName.toLowerCase();
            if (taskName.contains(wantedTask.toLowerCase())) {
                tasks.add(task);
            }
        }
        if (tasks.size() == 0) {
            ui.noSuchTask();
        } else {
            ui.tasksFound(tasks);
        }
    }
}
