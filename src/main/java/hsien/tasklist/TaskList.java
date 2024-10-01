package hsien.tasklist;

import hsien.task.Task;
import hsien.exception.HsienException;
import hsien.task.Deadline;
import hsien.task.Event;
import hsien.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("List is currently empty. Please add a task!");
            return;
        }
        int counter = 1;
        System.out.println("Here are the tasks in your list!");
        for (Task t : tasks) {
            System.out.printf("%d. %s%n", counter, t.getStatusDescription());
            counter += 1;
        }
    }

    public void addTask(String command, String desc) {
        try {
            if (desc.isEmpty()) {
                throw new HsienException();
            }
        } catch (HsienException e) {
            System.out.println("Description cannot be left empty");
            return;
        }

        Task newTask = null;
        String tempDesc;

        if (command.equals("todo")) {
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            tempDesc = desc.split("/by")[0].trim();
            String byDate = desc.split("/by")[1].trim();
            newTask = new Deadline(tempDesc, byDate);
        } else {
            tempDesc = desc.split("/from")[0].trim();
            String[] dates = desc.split("/from")[1].split("/to");
            String fromDate = dates[0].trim();
            String toDate = dates[1].trim();
            newTask = new Event(tempDesc, fromDate, toDate);
        }

        tasks.add(newTask);
        System.out.println("Added task: " + newTask.getDescription());
        System.out.printf("Now you have [%d] tasks in the list.%n", tasks.size());
    }

    public void deleteTask(int index) {
        System.out.println("Noted I have removed this task");
        System.out.println(tasks.get(index).getStatusDescription());
        tasks.remove(index);
        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
    }

    public void handleMark(String command, String desc) {
        int index = Integer.parseInt(desc);
        boolean isMarking = command.equals("mark");

        if (isMarking) {
            tasks.get(index - 1).mark();
            System.out.println("You marked " + index + " as marked");
        } else {
            tasks.get(index - 1).unmark();
            System.out.println("You marked " + index + " as unmarked");
        }

        System.out.println(tasks.get(index - 1).getStatusDescription());
    }

    public void findTask(String query) {
        int counter = 1;
        boolean foundMatch = false;

        for (Task t : tasks) {
            if (t.getDescription().contains(query)) {
                if (!foundMatch) {
                    System.out.println("These are the matching tasks in your list:");
                    foundMatch = true;
                }
                System.out.printf("%d. %s%n", counter, t.getStatusDescription());
                counter++;
            }
        }

        // No results
        if (!foundMatch) {
            System.out.println("No matching tasks found");
        }
    }

}
