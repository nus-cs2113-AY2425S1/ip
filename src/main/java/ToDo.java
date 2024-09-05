public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        //TODO Auto-generated constructor stub
    }

    public static void createToDo(String userInput){
        Constants.toDoList[Task.listCount] = new ToDo(userInput.substring(5));
            Task.listCount++;
            System.out.println(Constants.LINE);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      [T][ ] " + Constants.toDoList[Task.listCount - 1].description);
            System.out.println("    Now you have " + Task.listCount + " tasks in the list.");
            System.out.println(Constants.LINE);
    }
    
}
