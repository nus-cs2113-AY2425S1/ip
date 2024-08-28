public class Task {

    // Keep track of tasks
    private static Task[] tasks = new Task[100];
    private static int taskNumber = 0;

    // Object specific variables
    public String taskString;
    public boolean taskDone = false;

    // Constructor Function
    public Task(String taskString) {
        this.taskString = taskString;
        tasks[taskNumber] = this;
        taskNumber++;
        System.out.println("Muy Bien, work hard compadre!");
        System.out.println("I've Added the Task:");
        System.out.println(taskString);
    }

    // Class mark function
    public static void mark(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskNumber){ // Validity Check
            System.out.println("Not possible Amigo, try again");
            return;
        }
        tasks[taskIndex].taskDone = true;
        System.out.println("Fantastica!!!! I marked it:");
        System.out.println(tasks[taskIndex].checkboxString());
    }

    // Class unmark function
    public static void unmark(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskNumber){ // Validity Check
            System.out.println("Not possible Amigo, try again");
            return;
        }
        tasks[taskIndex].taskDone = false;
        System.out.println("Ay Caramba, I unmarked it:");
        System.out.println(tasks[taskIndex].checkboxString());
    }

    // Function to create String with Checkbox and Task
    public String checkboxString(){
        String returnString = "[";
        if (this.taskDone){
            returnString += "X";
        } else {
            returnString += " ";
        }
        returnString += "] " + this.taskString;
        return returnString;
    }

    // Function to print out task checklist
    public static void printTasksList(){
        if (taskNumber == 0){
            System.out.println("Por Favor? Nothing Here");
        } else {
            System.out.println("Si compinche, your tasks:");
            for (int i = 0; i < taskNumber; i++){
                System.out.println((i+1) + "." + tasks[i].checkboxString());
            }
        }
    }
}