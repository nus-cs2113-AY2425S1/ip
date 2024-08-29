import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBot {
    private static boolean isOn;
    private static List<String> datas;

    public static void main(String[] args) {
        isOn = true;
        datas = new ArrayList<>();

        String greeting = "____________________________________________________________\n" +
                "Hello! I'm DBot\nWhat can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);

        while (isOn) {
            System.out.print("Command: ");
            String line = in.nextLine();
            System.out.println("____________________________________________________________");
            if(line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isOn = false;
            } else if (line.equalsIgnoreCase("list")) {
                for (int i = 0; i < datas.size(); i++) {
                    System.out.printf("%d. ", i + 1);
                    System.out.println(datas.get(i));
                }
            }else {
                datas.add(line);
                System.out.print("added: ");
                System.out.println(line);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
