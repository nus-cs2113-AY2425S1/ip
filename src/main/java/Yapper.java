import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {
        String Greet = "____________________________________________________________\n"
                     + "Heyo! Imma Yapper!\n"
                     + "Whatchu wanna do?\n"
                     + "____________________________________________________________";
        String Exit = "____________________________________________________________\n"
                    + "Buh-Byeeee!!! Cya soon!\n"
                    + "____________________________________________________________";

        System.out.println(Greet);
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(Exit);
                break;
            }
            System.out.println(line);
        }
    }
}
