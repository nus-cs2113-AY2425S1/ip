import java.util.Scanner;

public class Gertrude {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int task_counter = 0;
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");

        while(true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println("____________________________________________________________");
            if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }else if(line.equals("list")) {
                for(int i = 1; i <= task_counter; i++) {
                    System.out.println(i + ". " + tasks[i-1]);
                }
            }else {
                tasks[task_counter] = line;
                task_counter++;
                System.out.println("added: " + line);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
