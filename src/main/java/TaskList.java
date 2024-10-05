import java.util.ArrayList;

/**
 * Contains the list of Tasks as well as methods to print and manipulate the Tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Prints each task in the task list and then states how many tasks there are.
     */
    public void printList() {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.print(i + ".");
            System.out.println(taskList.get(i-1).toString());
        }
        System.out.println("You have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done or not done.
     * @param lineInputArr the line input to interpret
     */
    public void markTask(String[] lineInputArr) {
        int index = InputParser.parseMark(lineInputArr, this);
        if (index < 0) {
            System.out.println("That is not a valid index.");
        } else {
            taskList.get(index).mark();
        }
    }

    /**
     * Adds a task to the task list and then prints that task
     * @param task the task to add
     * @param lineInput the line input to print
     */
    public void addTask(Task task, String lineInput) {
        taskList.add(task);
        System.out.println("added: " + lineInput);
    }

    /**
     * Deletes a task from the list, prints it, and then prints how many tasks remain.
     * @param lineInputArr the line input to interpret
     */
    public void deleteTask(String[] lineInputArr) {
        try {
            Task taskToRemove = taskList.get(Integer.parseInt(lineInputArr[1]) - 1);
            System.out.println("Ok. I've removed this task: ");
            taskToRemove.toString();
            taskList.remove(Integer.parseInt(lineInputArr[1])-1);
            System.out.println("You now have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is not a valid index.");
        }
    }

    /**
     * Prints a task based on the inputted keyword.
     * Checks for multiple keywords inputted. Isn't case-sensitive.
     * @param lineInputArr the inputted line to interpret
     */
    public void findKeyword(String[] lineInputArr) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<Task>();
        for(int i = 1; i < lineInputArr.length; i++) {
            for(int j = 0; j < taskList.size(); j++) {
                if(taskList.get(j).name.contains(lineInputArr[i].toLowerCase())) {
                    tasksWithKeyword.add(taskList.get(j));
                }
            }
        }

        if(tasksWithKeyword.isEmpty()) {
            System.out.println("Could not find any matching entries.");
        } else {
            System.out.println("Found " + tasksWithKeyword.size() + " matching entries.");
            for (Task task : tasksWithKeyword) {
                System.out.println(task.toString());
            }
        }

    }
}
