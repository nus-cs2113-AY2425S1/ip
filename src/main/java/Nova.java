import java.util.Scanner;


public class Nova {
    
    private static final int MAX_TASKS = 100;

    public static void displayMessage(String info) {
        System.out.println("____________________________________________________________\n" +
                info +
                "\n____________________________________________________________\n");
    }

    public static void main(String[] args) {

        displayMessage(" Hello! I'm Nova\n What can I do for you?");
        Task[] tasks = new Task[MAX_TASKS];

        while (true) {

            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();
            String[] words = info.split(" ");
            int taskIndex;

            switch (words[0]) {

            case "bye":
                displayMessage("Bye. Hope to see you again soon!");
                return;

            case "list":
                //List out all the tasks
                System.out.println("____________________________________________________________");
                for (int i = 1; i <= Task.getNumberOfTasks(); i++) {
                    System.out.println(i + ".[" + tasks[i - 1].getStatusIcon() + "] " + tasks[i - 1].getDescription());
                }
                System.out.println("____________________________________________________________");
                break;

            case "mark":
            case "unmark":
                //Mark and Unmark a task completion
                try {
                    taskIndex = Integer.parseInt(words[1]);
                } catch (NumberFormatException e) { //Display error if user did not put a number as the second argument
                    displayMessage("Invalid Index");
                    break;
                } catch (ArrayIndexOutOfBoundsException e) { //Display error if user did not input second argument
                    displayMessage("Missing Index Argument");
                    break;
                }

                if (taskIndex <= 0 || taskIndex > Task.getNumberOfTasks()) { //Display error if index is invalid
                    displayMessage("Invalid task index: " + taskIndex);
                } else if (words[0].equals("mark")) {
                    //Mark case
                    tasks[taskIndex - 1].markDone();
                    displayMessage("Nice! I've marked this task as done:\n" + "  [" +
                            tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].getDescription());
                } else {
                    //Unmark case
                    tasks[taskIndex - 1].unmarkDone();
                    displayMessage("OK, I've marked this task as not done yet:\n" + "  [" +
                            tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].getDescription());
                }
                break;

            default:
                //Add input as a task
                displayMessage(info);
                tasks[Task.getNumberOfTasks()] = new Task(info);
                break;

            }
        }
    }
}
