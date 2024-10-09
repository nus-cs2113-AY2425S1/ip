package taskpackage; // Package for Task-related classes

// Parent class Task which serves as a base for ToDo, Deadline, and Event tasks
public class Task {

    // Array to keep track of all tasks added
    public String inputString;


    // Object-specific variables
    public String taskString; // String to hold the task description
    public boolean isDone = false; // Boolean to track if the task is marked as done

    // Constructor Function: initializes a Task object and adds it to the tasks array
    public Task(String inputString, TaskList tasks){
        this.taskString = inputString; // Set the task description
        tasks.addTask(this); // Add the current task to the array
    }

    // Method to display a message when a task is successfully created
    public void constructorMessage(){
        System.out.println("Muy Bien, work hard compadre!"); // Success message in Spanish
        System.out.println("I've Added the Task:");
        System.out.println(checkboxString()); // Display the task in checkbox format
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


}
