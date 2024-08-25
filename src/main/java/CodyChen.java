import java.util.Scanner;
public class CodyChen {
    public static void main(String[] args) {
        String hi = "________________________\n" +
                "Hello! I'm CodyChen\n" +
                "What can I do for you?\n" +
                "________________________\n";
        System.out.println(hi);
        Scanner in = new Scanner(System.in);
        String line = "";
        int flag = 0;
        while(!line.equals("bye")){
            line = in.nextLine();
            if(flag == 0){
                System.out.print("Thank You for Responding" + "\n" +
                        "__________________________________\nYou said:    " +
                        line +  "\n__________________________________\n");
                flag = 1;
            } else {
                System.out.println(
                        "__________________________________\nYou said:     " +
                        line +
                        "\n__________________________________\n");
            }

        }

        String bye = "Bye. Hope to see you again soon!\n" +
                "________________________\n";
        System.out.println(bye);


    }
}
