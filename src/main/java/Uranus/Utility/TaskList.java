package Uranus.Utility;

import Uranus.Tasks.Deadlines;
import Uranus.Tasks.Events;
import Uranus.Tasks.Task;
import Uranus.Tasks.ToDos;
import UranusExceptions.EmptyCommandException;
import UranusExceptions.EmptyInputExceptions;
import UranusExceptions.IllegalCommandException;
import UranusExceptions.UranusExceptions;

public class TaskList extends Functions{

    protected static void handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (index >= 0 && index < taskList.size()) {
                Ui.print("Got it. I've removed this task:",
                        "  " + taskStatus(index),
                        "Now you have %d task(s) in the list".formatted(taskList.size() - 1));
                taskList.remove(index);
            } else {
                Ui.print("No such task exists. Please try again.");
            }
        } catch (NumberFormatException e) {
            Ui.print("Invalid task input. Please try again.", "Correct format: delete <int>");
        } catch (IllegalArgumentException e) {
            Ui.print("Task Number cannot be empty!");
        }
    }

    protected static void handleMarking(String input) {
        try {
            int taskNumIndex = Integer.parseInt(input.substring(input.indexOf(' ') + 1)) - 1;
            if (taskNumIndex >= 0 && taskNumIndex < taskList.size()) {
                if (input.startsWith("mark")) {
                    taskList.get(taskNumIndex).setDone();
                    Ui.print("Nice! I've marked this task as done:", taskStatus(taskNumIndex));
                } else {
                    taskList.get(taskNumIndex).setNotDone();
                    Ui.print("OK! I've marked this task as not done yet:", taskStatus(taskNumIndex));
                }
            } else {
                Ui.print("No such task exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            Ui.print("Invalid task input. Please try again.", "Correct format: mark <int> / unmark <int>");
        } catch (IllegalArgumentException e) {
            Ui.print("Task number cannot be empty!");
        }
    }

    public static String taskStatus(int index){
        return taskList.get(index).getTaskStatus();
    }

    protected static void listTasks() {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskStatus(i));
        }
        System.out.println(SEPARATOR);
    }

    protected static void addTask(String input) {

        try {
            if (input == null || input.trim().isEmpty()) {
                throw new EmptyInputExceptions();
            } else if (input.trim().equals("todo") ||
                    input.trim().equals("deadline") ||
                    input.trim().equals("task") ||
                    input.trim().equals("event")){
                throw new EmptyCommandException();
            } else if (input.startsWith("todo ")){
                taskList.add(new ToDos(input));
            } else if (input.startsWith("deadline ")){
                taskList.add(new Deadlines(input));
            } else if (input.startsWith("event ")){
                taskList.add(new Events(input));
            } else if (input.startsWith("task ")){
                taskList.add(new Task(input));
            } else{
                throw new IllegalCommandException();
            }
            Ui.print("Got it. I've added this task:",
                    "  " + taskStatus(taskList.size() - 1),
                    "Now you have %d task(s) in the list".formatted(taskList.size()));
        } catch (UranusExceptions e){
            Ui.print(e.getMessage());
        }
    }
}
