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

    /**
     * Constructor for the TaskList with default empty list and a given filePath from the chatbot
     */
    TaskList() {
        this.taskList = new ArrayList<>();
        new Storage(filePath).createTaskFile();
    }

    /**
     * Constructor for the TaskList with a given list of task and a filePath from the chatbot
     * @param tasks the list of tasks
     */
    TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
        taskCounter = tasks.size();
    }

    /**
     * Check if the task indicated has already been marked as completed
     * @param response input given by the user
     * @return true or false based on the completion status of the task,
     * completed returns true else false
     */
    public boolean checkMark(String response) {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1; //getting the index of Task to be deleted
        if (taskList.get(taskIndex).isCompleted()) {
            return true;
        }
        return false;
    }

    /**
     * Check if the task indicated has already been marked as completed
     * @param response input given by the user
     * @return true or false based on the completion status of the task,
     * completed returns false else true
     */
    public boolean checkUnmark(String response) {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1;
        if (taskList.get(taskIndex).isCompleted()) {
            return false;
        }
        return true;
    }

    /**
     * Deletes a task indicated by the user
     * @param response input given by the user including the task number
     */
    public void deleteTask(String response) throws IndexOutOfBoundsException {
        int taskNumber = Integer.parseInt(response.split(" ")[1]);
        taskList.remove(taskNumber - 1);
    }

    /**
     * Add an event task based on the users input
     * @param response input given by the user including the task information, start date and
     *                 end date of the task
     */
    public void addEvent(String response) throws StringIndexOutOfBoundsException,ArrayIndexOutOfBoundsException {
        String[] answers = response.split("/from ");
        String taskInfo = answers[0].substring(6);
        String[] dates = answers[1].split("/to ");
        String startDateTime = dates[0];
        String endDateTime = dates[1];
        if (taskInfo.length() == 0) {
            throw new GlendonException(); // throw exception due to a lack of information of the task
        }
        taskList.add(new Event(taskInfo, startDateTime, endDateTime));
        taskCounter++;
    }

    /**
     * Add a deadline task based on the users input
     * @param response input given by the user including the task information
     *                 deadline of the task
     */
    public void addDeadline(String response) throws GlendonException {
        String[] answers = response.split("/by ");
        String taskInfo = answers[0].substring(9);
        if (taskInfo.length() == 0) {
            throw new GlendonException(); // throw exception due to a lack of information of the task
        }
        String taskDeadline = answers[1];
        taskList.add(new Deadline(taskInfo, taskDeadline));
        taskCounter++;
    }

    /**
     * Add a todo task based on the users input
     * @param response input given by the user including the task information
     */
    public void addTodo(String response) throws StringIndexOutOfBoundsException {
        String taskInfo = response.substring(5);
        if (taskInfo.length() == 0) {
            throw new GlendonException();
        }
        taskList.add(new Todo(taskInfo));
        taskCounter++;
    }

    /**
     * Unmarks the task, setting its completion status to incomplete
     * @param response input given by the user including the task number
     *
     */
    public void unmarkTask(String response) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1;
        taskList.get(taskIndex).setCompleted(false);
    }

    /**
     * Marks the task, setting its completion status to completed
     * @param response input given by the user including the task number
     *
     */
    public void markTask(String response) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] responsesArray = (response.split(" "));
        int taskIndex = Integer.parseInt(responsesArray[1]) - 1; //getting the index of Task to be deleted
        taskList.get(taskIndex).setCompleted(true);
    }

    /**
     * Finds the task with the keyword given by the user
     * @param wantedTask input given by the user of the task to be found
     *
     */
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
