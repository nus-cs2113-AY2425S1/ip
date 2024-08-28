public class Task {

    private static Task[] tasks = new Task[100];
    private static int taskNumber = 0;

    public String taskString;
    public boolean taskDone = false;

    public Task(String taskString) {
        this.taskString = taskString;
        tasks[taskNumber] = this;
        taskNumber++;
        System.out.println("Muy Bien, work hard compadre!");
        System.out.println("I've Added the Task:");
        System.out.println(taskString);
    }
    public static void mark(int taskIndex){
        tasks[taskIndex].taskDone = true;
        System.out.println("Fantastica!!!! I marked it:");
        System.out.println(tasks[taskIndex].checkboxString());
    }
    public static void unmark(int taskIndex){
        tasks[taskIndex].taskDone = false;
        System.out.println("Ay Caramba, I unmarked it:");
        System.out.println(tasks[taskIndex].checkboxString());
    }
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