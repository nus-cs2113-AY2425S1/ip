import java.util.Scanner;

public class Yappatron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Yappatron");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        String[] arrayInput = new String[100];
        int flag = 0, position = 0, i;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")){
                flag = 1;
            }
            else if (!input.equalsIgnoreCase("list")){
                System.out.println("added: " + input);
                arrayInput[position++] = input;
            }
            else{
                for (i=0; i<position; i++){
                    System.out.println(i+1 + ". " + arrayInput[i]);
                }
            }
        }while (flag==0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
