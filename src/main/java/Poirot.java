import java.util.Scanner;

public class Poirot {
    private static Task[] list_actions = new Task[100];
    private static int last_index = 0;

    public static void echo(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    public static void print(Task[] list) {
        if (last_index == 0) {
            System.out.println("____________________________________________________________\n");
            System.out.println("No actions available");
        } else {
            System.out.println("____________________________________________________________\n");
            for (int i = 0; i < last_index; i++) {
                System.out.println((i + 1) + ".[" + list_actions[i].getStatusIcon() + "]" + list_actions[i].getDescription());
            }
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void add(Task action) {
        list_actions[last_index] = action;
        last_index++;
        System.out.println("____________________________________________________________\n");
        System.out.println("added: " + action.getDescription());
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I'm POIROT\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");
        boolean working = true;
        Scanner scan = new Scanner(System.in);
        while (working) {
            String input = scan.nextLine();
            String[] list_input = input.split(" ");
            switch (list_input[0]) {
                case "list":
                    print(list_actions);
                    break;
                case "bye":
                    working = false;
                    break;
                case "mark":
                    int x = Integer.parseInt(list_input[1]) - 1;
                    list_actions[x].setDone(true);
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.print("[" + list_actions[x].getStatusIcon() + "] ");
                    System.out.println(list_actions[x].getDescription());
                    System.out.println("____________________________________________________________\n");
                    break;
                case "unmark":
                    int y = Integer.parseInt(list_input[1]) - 1;
                    list_actions[y].setDone(false);
                    System.out.println("____________________________________________________________\n");
                    System.out.println("OK, I've marked this task as not done yet:\n");
                    System.out.print("[" + list_actions[y].getStatusIcon() + "] ");
                    System.out.println(list_actions[y].getDescription());
                    System.out.println("____________________________________________________________\n");
                    break;
                default:
                    String clearInput = input.trim();
                    if(clearInput.isEmpty()){
                        System.out.println("____________________________________________________________\n");
                        System.out.println("Invalid task");
                        System.out.println("____________________________________________________________\n");
                    }
                    else {
                        Task newTask = new Task(clearInput);
                        add(newTask);
                    }
                    break;
            }
        }
        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}