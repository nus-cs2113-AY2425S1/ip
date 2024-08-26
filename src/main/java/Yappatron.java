import java.util.Scanner;

public class Yappatron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Yappatron");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        int flag = 0;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")){
                flag = 1;
            }
            else{
                System.out.println(input);
            }
        }while (flag==0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
