public class Task {

    // Keep track of tasks
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    // Object specific variables
    public String taskString;
    public boolean isDone = false;

    // Constructor Function
    public Task(String inputString) {
        this.taskString = inputString;
        tasks[taskCount] = this;
        taskCount++;
    }

    public void constructorMessage(){
        System.out.println("Muy Bien, work hard compadre!");
        System.out.println("I've Added the Task:");
        System.out.println(checkboxString());
        System.out.println("You've got " + taskCount + " tasks, better start working!");
    }

    // Class mark function
    public static void mark(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskCount){ // Validity Check
            System.out.println("Not possible Amigo, try again");
            return;
        }
        tasks[taskIndex].isDone = true;
        System.out.println("Fantastica!!!! I marked it:");
        System.out.println(tasks[taskIndex].checkboxString());
    }

    // Class unmark function
    public static void unmark(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskCount){ // Validity Check
            System.out.println("Not possible Amigo, try again");
            return;
        }
        tasks[taskIndex].isDone = false;
        System.out.println("Ay Caramba, I unmarked it:");
        System.out.println(tasks[taskIndex].checkboxString());
    }

    // Function to create String with Checkbox and Task
    public String checkboxString(){
        String returnString = "[";
        if (this.isDone){
            returnString += "X";
        } else {
            returnString += " ";
        }
        returnString += "] " + this.taskString;
        return returnString;
    }

    // Function to print out task checklist
    public static void printTasksList(){
        if (taskCount == 0){
            System.out.println("Por Favor? Nothing Here");
        } else {
            System.out.println("Si compinche, your tasks:");
            for (int i = 0; i < taskCount; i++){
                System.out.println((i+1) + "." + tasks[i].checkboxString());
            }
        }
    }
}