package taskmanager;

import tasks.Task;
import tasks.Deadline;
import tasks.Todo;
import tasks.Event;
import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;
import filemanager.FileManager;


public class Storage {

    private ArrayList<Task> taskList;
    FileManager fileManager;

    public Storage() {
        taskList = new ArrayList<Task>();
        fileManager = new FileManager("C:\\Users\\ASUS\\Documents\\NUS\\Yr 2 Sem 1\\CS2113\\ip\\.\\data\\tasks.txt");
        taskList = fileManager.loadTasks();
    }

    public void saveTasks() {
        fileManager.saveTasksToFile(taskList);
    }


    public void storageInsert(Task task) {
        taskList.add(task);
        saveTasks();
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
        saveTasks();
    }

    public void storageMark(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.get(index - 1).setStatus();
        saveTasks();
    }

    public void storageUnmark(int index) throws InvalidCommandException {
        if (index > taskList.size()) {
            throw new InvalidCommandException("Task number " + index + " does not exist");
        }
        taskList.get(index - 1).unsetStatus();
        saveTasks();
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

    public void storagePrintTask(int index){
        Task task = taskList.get(index - 1);
        if (task instanceof Deadline) {
            System.out.println((index) + "." + task.getStatusIcon() + " " + task + " (by: " + ((Deadline) task).getDeadline() + ")");
        }else if (task instanceof Event) {
            System.out.println((index) + "." + task.getStatusIcon() + " " + task + " (from: " + ((Event) task).getStart() + " to: " + ((Event) task).getEnd() + ")");
        }else{
            System.out.println((index) + "." + task.getStatusIcon() + " " + task);
        }
    }


    public void storageClear(){
        taskList.clear();
        saveTasks();
    }
}

