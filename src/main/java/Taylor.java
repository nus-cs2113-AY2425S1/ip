import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taylor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
        System.out.println(line);

        String input = sc.nextLine();
        List<String> list = new ArrayList<>();
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(line);
                for(int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }
            System.out.println(line);
            System.out.println("added: " + input);
            list.add(input);
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
