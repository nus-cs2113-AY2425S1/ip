public class Constants {
    public static Task[] toDoList = new Task[100];
    public static final String LINE = "    ___________________________________";

    public static String logo = " _____                           \n" + //
                "|_   _|   _ _ __ ___  _ __   ___ \n" + //
                "  | || | | | '__/ _ \\| '_ \\ / _ \\\n" + //
                "  | || |_| | | | (_) | | | |  __/\n" + //
                "  |_| \\__, |_|  \\___/|_| |_|\\___|\n" + //
                "      |___/                      ";

    public static void goodbye() {
        System.out.println(Constants.LINE);
        System.out.println("    see you brother");
        System.out.println(Constants.LINE);
    }

    public static void markAsDone(int index){
        Constants.toDoList[index].isDone = true;
        System.out.println(Constants.LINE);
        System.out.println("    TIGHT! I've marked this task as done:");
        System.out.println("      [X] " + Constants.toDoList[index].description);
        System.out.println(Constants.LINE);
    }

    public static void unmarkAsUndone(int index){
        Constants.toDoList[index].isDone = false;
        System.out.println(Constants.LINE);
        System.out.println("    alright cuh, I've marked this task as unfinished:");
        System.out.println("      [ ] " + Constants.toDoList[index].description);
        System.out.println(Constants.LINE);
    } 

    public static void getList(){
        System.out.println(Constants.LINE);
            for (int i = 0; i < Task.listCount; i++) {
                String status = Constants.toDoList[i].getStatusIcon();
                if (Constants.toDoList[i] instanceof Deadline) {
                    System.out.println("    " + (i + 1) + ". [D][" + status + "] " + Constants.toDoList[i].description + " (by: " + ((Deadline) Constants.toDoList[i]).doBy + ")");
                } else if (Constants.toDoList[i] instanceof Event) {
                    System.out.println("    " + (i + 1) + ". [E][" + status + "] " + Constants.toDoList[i].description + " (" + ((Event) Constants.toDoList[i]).timing + ")");
                } else {
                    System.out.println("    " + (i + 1) + ". [T][" + status + "] " + Constants.toDoList[i].description);
                }
            }
        System.out.println(Constants.LINE);
    }
}