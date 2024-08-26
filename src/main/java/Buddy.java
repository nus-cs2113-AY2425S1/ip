import java.util.Scanner;

public class Buddy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hi Daddy, I'm Buddy");
        System.out.println(" What can I do for you? ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in); //Scanner object named "scanner"
        String[] tasks = new String[100]; //Array to store tasks
        int task_count = 0; //Counter for the number of tasks
        String input;

        while (true)
        {
            input = scanner.nextLine();

            if (input.equals("bye")) //end loop when user inputs "bye"
            {
                break;
            }
            else if (input.equals("list")) //display tasks when user inputs "list"
            {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are your tasks Daddy:");
                for (int i = 0; i < task_count; i++)
                {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            }
            else //store whatever user input in "tasks" array
            {
                tasks[task_count] = input;
                task_count++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________"); //first line after "bye"
        System.out.println(" Bye Daddy. I will miss you (>ᴗ•) !");
        System.out.println("____________________________________________________________"); //second line after "bye"
    }
}
