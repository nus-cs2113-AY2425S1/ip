package CassHelpers.util;

import CassHelpers.exceptions.*;
import CassHelpers.types.Deadline;
import CassHelpers.types.Event;
import CassHelpers.types.Task;
import CassHelpers.types.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TodoList {
    private final ArrayList<Task> taskList;
    private final FileUtil fileUtil;

    public TodoList(FileUtil fileUtil){
        this.taskList = new ArrayList<Task>();
        this.fileUtil = fileUtil;
    }

    public TodoList(ArrayList<Task> taskList, FileUtil fileUtil){
        this.taskList = taskList;
        this.fileUtil = fileUtil;
    }

    public void saveTask(Task input){
        taskList.add(input);
        fileUtil.appendTasktoFile(input);
        System.out.println("Got it. I've added this task: \n "+ input.toString());
        printCurrentListSize();
    }

    public void deleteTask(int index) {
        if (index <= 0 || index > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else {
            String removedTaskDescription = taskList.get(index - 1).toString();
            taskList.remove(index - 1);
            fileUtil.writeTasksToFile(this.taskList);
            System.out.println(" Noted, I've removed this task: ");
            System.out.println("  "+removedTaskDescription);
            printCurrentListSize();
        }
    }

    public  void markTask(int index) throws TaskNotFoundException, TaskAlreadyMarkedException {
        if (index <= 0 || index > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else if (taskList.get(index - 1).getIsCompleted()) {
            throw new TaskAlreadyMarkedException("Task has already been marked complete");
        } else {
            taskList.get(index - 1).setCompleted(true);
            this.fileUtil.writeTasksToFile(this.taskList);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(" " + taskList.get(index - 1).toString());
        }
    }

    public  void unmarkTask(int index) throws TaskNotFoundException, TaskAlreadyUnmarkedException {
        if (index <= 0 || index > taskList.size()) {
            throw new TaskNotFoundException("Sorry, no task found");
        } else if (!taskList.get(index - 1).getIsCompleted()) {
            throw new TaskAlreadyUnmarkedException("Task has already been marked incomplete");
        } else {
            taskList.get(index - 1).setCompleted(false);
            this.fileUtil.writeTasksToFile(this.taskList);
            System.out.println(" OK, I've marked this task as not done yet: ");
            System.out.println(" " + taskList.get(index - 1).toString());
        }
    }

    public  void printList(){
        if(taskList.isEmpty()){
            System.out.println("List is empty");
        } else {
            System.out.println("Here are the tasks in your list: :");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
        }
    }

    public void printCurrentListSize() {
        System.out.println("Now you have "+taskList.size()+" tasks in the list.");
    }

    public  void addTodo(String input) {
        int todoOffset = 4;
        String taskName = input.substring(todoOffset).trim();
        saveTask(new Todo(taskName));
    }


    public void addEvent(String input) throws InvalidEventFormatException {
        int fromIndexOffset = 6;
        int toIndexOffset = 4;

        int fromIndex = input.indexOf("/from") + fromIndexOffset;
        int toIndex = input.indexOf("/to");

        if (fromIndex < fromIndexOffset || toIndex < 0) {
            throw new InvalidEventFormatException("Sorry, event entered has the wrong format");
        }

        String from = input.substring(fromIndex, toIndex).trim();
        String to = input.substring(toIndex + toIndexOffset).trim();
        String eventTaskName = input.substring(0, fromIndex - fromIndexOffset).trim();

        saveTask(new Event(eventTaskName, from, to));
    }

    public void addDeadline(String input) throws InvalidDeadlineFormatException {
        int byIndexOffset = 4;
        int byIndex = input.indexOf("/by");

        if (byIndex < 0) {
            throw new InvalidDeadlineFormatException("Sorry, deadline entered has the wrong format");
        }

        String by = input.substring(byIndex + byIndexOffset).trim();
        String deadlineTaskName = input.substring(0, byIndex).trim();

        saveTask(new Deadline(deadlineTaskName, by));
    }
}
