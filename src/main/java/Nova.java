import java.util.Scanner;

public class Nova {
    public static void print(String info) {
        System.out.println("____________________________________________________________\n" +
                info +
                "\n____________________________________________________________\n");
    }

    public static void main(String[] args) {
        print(" Hello! I'm Nova\n What can I do for you?");
        String[] tasks = new String[100];
        int numberOfTasks = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();
            switch (info) {
            case "bye":
                print("Bye. Hope to see you again soon!");
                return;
            case "list":
                System.out.println("____________________________________________________________");
                for (int i = 1; i <= numberOfTasks; i++) {
                    System.out.println(i + ": " + tasks[i - 1]);
                }
                System.out.println("____________________________________________________________");
                break;
            default:
                print(info);
                tasks[numberOfTasks] = info;
                numberOfTasks++;
                break;
            }
        }
    }
}
