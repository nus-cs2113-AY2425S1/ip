import java.util.ArrayList;
import java.util.Scanner;

public class Ronaldo {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String indent = "___________________________________________ \n";
        System.out.println( indent + "Hello! I'm Ronaldo, Cristiano Ronaldo! The greatest footballer of all time. SIUUUUUUU! \n"+ "Hehehe what can I do for you? \n"  + indent);

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you soon! SIUUUUUUU!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the goals to complete in order for you to reach your dreams:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
                }
                System.out.println(" "); //Indentation
            } else {
                System.out.println("Your goal has been added: " + input + "\n");
                Task t = new Task(input);
                tasks.add(t);
            }
        }
    }
}
