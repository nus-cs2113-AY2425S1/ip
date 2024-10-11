package task;
import exception.AmyException;
import java.util.ArrayList;
import java.util.List;
import ui.Ui;
/**
 * Represents a list of tasks and provides methods to manipulate the list.
 * The list is stored as an ArrayList of Task objects.
 * The TaskList class provides methods to add, delete, and update status of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object with an existing list of tasks.
     * @param taskList The list of tasks to be stored in the TaskList object.
     */
    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }
    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList(){
        this.taskList = new ArrayList<>();
    }
    public int getTasksSize(){
        return taskList.size();
    }
    /**
     * Checks if the task number is valid.
     * @param taskNo The task number to be checked.
     * @return True if the task number is valid, false otherwise.
     */
    public boolean isValidTask(int taskNo){
        return !(taskNo < 0 || taskNo >= getTasksSize());
    }

    /**
     * Prints the list of tasks in the TaskList object.
     */
    public void listTasks(){
        if(taskList.isEmpty()){
            Ui.print("You don't have anything in your TDL! Take some rest for now (uwu)");
        } else{
            Ui.print("Let's try to get this done! You got this \\(^o^)/");
            List<String> tasks = new ArrayList<>();
            for (int i = 0; i < taskList.size(); i++) {
                tasks.add(String.valueOf(i + 1) + '.' + taskList.get(i));
            }    
            Ui.print(String.join("\n", tasks));
        }
    }

    /**
     * Returns the task at the specified index in the TaskList object.
     * @param taskNo The index of the task to be returned.
     * @return The task at the specified index.
     * @throws AmyException If the task number is invalid.
     */
    public Task getTask(int taskNo) throws AmyException{
        if(!isValidTask(taskNo)){
            throw new AmyException("I didn't find that task on your list. Wanna try again? (T_T)");
        }
        return taskList.get(taskNo);
    }

    /**
     * Adds a task to the TaskList object.
     * @param task The task to be added to the TaskList object.
     */
    public void addTask(Task task){
        taskList.add(task);
        Ui.print("I added <" + task + "> to the todo-list! (>v<)/\nNow you have " + getTasksSize() + " tasks in the todolist. Good luck! (o_o)");
    }

    /**
     * Returns the tasks in the TaskList object.
    } */
    public ArrayList<Task> getTaskList(){
        return taskList;
    }
    /**
     * Marks a task as done or undone in the TaskList object.
     * @param taskNo The index of the task to be marked.
     */
    public void markTaskStatus(int taskNo, boolean isDone) throws AmyException{
        if(!isValidTask(taskNo)){
            throw new AmyException("I didn't find that task on your list. Wanna try again? (T_T)");
        }
        Task task = taskList.get(taskNo);
        task.markTask(isDone);
        if(isDone){
            Ui.print("Good job \\o/ You have been working hard~\n" + task);
        } else {
            Ui.print("Okay, let's do this again (-v-)\n" + task);
        }
    }
    /**
     * Deletes a task from the TaskList object.
     * @param taskNo The index of the task to be deleted.
     * @throws AmyException If the task number is invalid.
     */
    public void deleteTask(int taskNo) throws AmyException{
        if(!isValidTask(taskNo)){
            throw new AmyException("I didn't find that task on your list. Wanna try again? (T_T)");
        }
        Task task = taskList.remove(taskNo);
        Ui.print("Noted. I have removed this task! (^o^)/\n" + task + "\nNow you have " + getTasksSize() + " tasks in the todolist. Good luck! (o_o)");
    }

    /**
     * Finds tasks that match the keyword in the TaskList object.
     * @param match The keyword to search for in the tasks.
     */
    public void findTask(String match){
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList){
            if(task.getDescription().contains(match)){
                matchedTasks.add(task);
            }
        }
        if(matchedTasks.isEmpty()){
            Ui.print("I didn't find any tasks that match your keyword. Try again? (T_T)");
        } else {
            Ui.print("Here are the tasks that match your keyword:");
            for (int i = 0; i < matchedTasks.size(); i++){
                Ui.print(String.valueOf(i + 1) + '.' + matchedTasks.get(i));
            }
        }
    }
}
