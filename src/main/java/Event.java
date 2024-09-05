public class Event extends Task {
    protected String timing;

    public Event(String description, String time) {
        super(description);
        timing = time;
        //TODO Auto-generated constructor stub
    }

    public static void createEvent(String userInput) {
        String[] parts = userInput.split(" /from | /to ");
            Constants.toDoList[Task.listCount] = new Event(parts[0].substring(6), "from: " + parts[1] + " to: " + parts[2]);
            Task.listCount++;
            System.out.println(Constants.LINE);
            System.out.println("    Got it. I've added this task cuh:");
            System.out.println("      [E][ ] " + Constants.toDoList[Task.listCount - 1].description + " (" + ((Event) Constants.toDoList[Task.listCount - 1]).timing + ")");
            System.out.println("    Now you have " + Task.listCount + " tasks in the list.");
            System.out.println(Constants.LINE);
    }
    
}
