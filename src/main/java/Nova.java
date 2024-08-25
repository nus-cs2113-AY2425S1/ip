import java.util.Scanner;
import java.util.Vector;

public class Nova {
    public static void print(String info) {
        System.out.println("____________________________________________________________\n" +
                info +
                "\n____________________________________________________________\n");
    }

    public static void main(String[] args) {
        print(" Hello! I'm Nova\n What can I do for you?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();
            if (info.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            }
            print(info);
        }
    }
}
