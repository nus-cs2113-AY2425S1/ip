public class Deadline extends Task {
    protected String doBy;


    public Deadline(String description, String by) {
        super(description);
        doBy =  by;
        //TODO Auto-generated constructor stub
    }

    public static void createDeadline(String userInput){
        String[] parts = userInput.split(" /by ");
        Constants.toDoList[Task.listCount] = new Deadline(parts[0].substring(9), parts[1]);
        Task.listCount++;
        System.out.println(Constants.LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      [D][ ] " + Constants.toDoList[Task.listCount - 1].description + " (by: " + ((Deadline) Constants.toDoList[Task.listCount - 1]).doBy + ")");
        System.out.println("    Now you have " + Task.listCount + " tasks in the list.");
        System.out.println(Constants.LINE);
    }
    
}
