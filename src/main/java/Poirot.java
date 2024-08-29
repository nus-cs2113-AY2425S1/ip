import java.util.Scanner;

public class Poirot {
    private static String[] list_actions = new String[100];
    private static int last_index = 0;

    public static void echo(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    public static void print(String[] list) {
        if (last_index == 0) {
            System.out.println("__________________________________________________________\n");
            System.out.println("No actions available");
        } else {
            System.out.println("___________________________________________________________\n");
            for (int i = 0; i < last_index; i++) {
                System.out.println(i + 1 + "." + list_actions[i] + " ");
            }
        }
        System.out.println("___________________________________________________________\n");
    }

    public static void add(String action) {
        list_actions[last_index] = action;
        last_index++;
        System.out.println("___________________________________________________________\n");
        System.out.println("added: " + action);
        System.out.println("___________________________________________________________\n");
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
            switch (input) {
                case "list":
                    print(list_actions);
                    break;
                case "bye":
                    working = false;
                    break;
                default:
                    add(input);
            }
        }
        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}