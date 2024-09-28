package CassHelpers.util;

public class UI {

    protected final static int SEPARATOR_LENGTH = 75;

    public void drawLine(){
        for(int i=0;i<SEPARATOR_LENGTH;i+=1){
            System.out.print("-");
        }
        System.out.println();
    }

    public void displayIntroductionArt() {
        System.out.println("   ___                                               _                   ");
        System.out.println("  / __|   __ _     ___     ___    __ _    _ _     __| |     _ _   __ _   ");
        System.out.println(" | (__   / _` |   (_-<    (_-<   / _` |  | ' \\   / _` |    | '_| / _` |  ");
        System.out.println("  \\___|  \\__,_|   /__/_   /__/_  \\__,_|  |_||_|  \\__,_| "+"  _|_|_  \\__,_|  ");
        System.out.println("_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|"+
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| ");
        System.out.println("`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ");
    }

    public void displayIntroduction(){
        drawLine();
        displayIntroductionArt();
        drawLine();
        System.out.println(" Hello! I'm Cassandra");
        System.out.println(" What can I do for you?");
        drawLine();
    }

    public void displayHelpInstructions() {
        System.out.println("Welcome to Cassandra - Your Task Manager App!");
        System.out.println("Here are the commands you can use:");

        System.out.println("\n1. Add a Todo Task:");
        System.out.println("   todo <task_description>");
        System.out.println("   Example: todo Read a book");

        System.out.println("\n2. Add a Deadline Task:");
        System.out.println("   deadline <task_description> /by <due_date>");
        System.out.println("   Example: deadline Submit assignment /by 2024-09-15");

        System.out.println("\n3. Add an Event Task:");
        System.out.println("   event <event_description> /from <start_time> /to <end_time>");
        System.out.println("   Example: event Team meeting /from 2pm /to 4pm");

        System.out.println("\n4. List All Tasks:");
        System.out.println("   list");
        System.out.println("   Displays all tasks currently in your list.");

        System.out.println("\n5. Mark a Task as Complete:");
        System.out.println("   mark <task_index>");
        System.out.println("   Example: mark 2");

        System.out.println("\n6. Unmark a Task (set as incomplete):");
        System.out.println("   unmark <task_index>");
        System.out.println("   Example: unmark 2");

        System.out.println("\n7. Find Tasks with similar Descriptions :");
        System.out.println("   find <prompt>");
        System.out.println("   Example: find book");

        System.out.println("\n8. Exit the App:");
        System.out.println("   bye");
        System.out.println("   Exits the application.");

        System.out.println("\nNote:");
        System.out.println(" - Task indices start from 1.");
    }

    public void showError(Exception e){
        System.out.println(e.getMessage());
    }
}
