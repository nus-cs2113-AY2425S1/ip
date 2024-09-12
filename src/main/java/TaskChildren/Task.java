package TaskChildren; // Package for Task-related classes

// Parent class Task which serves as a base for ToDo, Deadline, and Event tasks
public class Task {

    // Array to keep track of all tasks added
    private static Task[] tasks = new Task[100]; // Task array of size 100 to store tasks
    private static int taskCount = 0; // Counter to keep track of the number of tasks

    // Object-specific variables
    public String taskString; // String to hold the task description
    public boolean isDone = false; // Boolean to track if the task is marked as done

    // Constructor Function: initializes a Task object and adds it to the tasks array
    public Task(String inputString){
        this.taskString = inputString; // Set the task description
        tasks[taskCount] = this; // Add the current task to the array
        taskCount++; // Increment the task count
    }

    // Static method to delete the latest task in case of errors
    public static void deleteLatestTask(){
        tasks[taskCount-1] = null; // Remove the latest task from the array
        taskCount--; // Decrement the task count
    }

    // Method to display a message when a task is successfully created
    public void constructorMessage(){
        System.out.println("Muy Bien, work hard compadre!"); // Success message in Spanish
        System.out.println("I've Added the Task:");
        System.out.println(checkboxString()); // Display the task in checkbox format
        System.out.println("You've got " + taskCount + " tasks, better start working!"); // Display the current task count
    }

    // Static method to mark a task as done by index
    public static void mark(int taskIndex){
        tasks[taskIndex].isDone = true; // Mark the task as done
        System.out.println("Fantastica!!!! I marked it:"); // Success message in Spanish
        System.out.println(tasks[taskIndex].checkboxString()); // Display the updated task
    }

    // Static method to unmark a task as undone by index
    public static void unmark(int taskIndex){
        tasks[taskIndex].isDone = false; // Unmark the task as not done
        System.out.println("Ay Caramba, I unmarked it:"); // Message indicating task was unmarked
        System.out.println(tasks[taskIndex].checkboxString()); // Display the updated task
    }

    // Method to create and return a string with a checkbox (marked or unmarked) for the task
    public String checkboxString(){
        String returnString = "["; // Start of the checkbox string
        if (this.isDone){
            returnString += "X"; // Mark the checkbox as done if the task is completed
        } else {
            returnString += " "; // Leave the checkbox empty if the task is not completed
        }
        returnString += "] " + this.taskString; // Append the task description
        return returnString; // Return the formatted string
    }

    // Static method to print all tasks in the list
    public static void printTasksList(){
        if (taskCount == 0){
            System.out.println("Por Favor? Nothing Here"); // Message when there are no tasks
        } else {
            System.out.println("Si compinche, your tasks:"); // Message when displaying tasks
            for (int i = 0; i < taskCount; i++){
                // Print each task with its index and checkbox format
                System.out.println((i+1) + "." + tasks[i].checkboxString());
            }
        }
    }
}
