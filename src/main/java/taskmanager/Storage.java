package taskmanager;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;


public class Storage {


    private List<Task> taskList;

    public Storage() {
        taskList = new ArrayList<Task>();
    }

    public void storageInsert(Task task) {
        taskList.add(task);
        int numOfTasks = taskList.size();

        System.out.println("Got it. I've added this task:");
        System.out.print(task.getStatusIcon() + " " + task.getContents());

        if (task instanceof Deadline) {
            System.out.print(" (by: " + ((Deadline) task).getDeadline() + ")");
        } else if (task instanceof Event) {
            System.out.print(" (from: " + ((Event) task).getStart() + " to: " + ((Event) task).getEnd() + ")");
        }

        System.out.println();
        System.out.println("Now you have " + numOfTasks + " tasks in your list.");
    }

    public void storageDelete(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.remove(index - 1);
    }

    public void storageMark(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.get(index - 1).setStatus();
    }

    public void storageUnmark(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.get(index - 1).unsetStatus();
    }

    public void storageList() {
        System.out.println("Here is your current list: ");

        int index = 0;
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                System.out.println((index + 1) + "." + task.getStatusIcon() + " " + task + " (by: " + ((Deadline) task).getDeadline() + ")");
            } else if (task instanceof Event) {
                System.out.println((index + 1) + "." + task.getStatusIcon() + " " + task + " (from: " + ((Event) task).getStart() + " to: " + ((Event) task).getEnd() + ")");
            } else {
                System.out.println((index + 1) + "." + task.getStatusIcon() + " " + task);
            }
            index++;
        }
    }
}
