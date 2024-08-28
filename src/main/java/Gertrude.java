import java.util.Scanner;

public class Gertrude {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int task_counter = 0;
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");

        while(true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] lineArr = line.split(" ");
            System.out.println("____________________________________________________________");
            if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }else if(lineArr[0].equals("list")) {
                for(int i = 1; i <= task_counter; i++) {
                    System.out.print(i + ".");
                    tasks[i-1].printTask();
                }
            }else if(lineArr[0].equals("mark") || lineArr[0].equals("unmark")) {
                int index = Integer.parseInt(lineArr[1]);
                if(index < 1 || index > task_counter) {
                    System.out.println("That is not a valid index.");
                }else if(lineArr[0].equals("mark")) {
                    tasks[index - 1].markDone();
                }else {
                    tasks[index-1].markNotDone();
                }
            }else {
                Task new_task = new Task(line);
                tasks[task_counter] = new_task;
                task_counter++;
                System.out.println("added: " + line);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
