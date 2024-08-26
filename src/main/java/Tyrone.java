import java.util.Scanner;

public class Tyrone {
    public class Constants{
        public static final String line = "    ___________________________________";
        public static final String Whatcanido ="    What can I do for you cuh?";
        public static void goodbye(){
            System.out.println(Constants.line);
            System.out.println("    see you brother");
            System.out.println(Constants.line);
        }
    }
    public static void main(String[] args) {
        String logo = "    tyrone";
        Scanner in = new Scanner(System.in);
        System.out.println(Constants.line);
        System.out.println("    Hello from\n" + logo);
        System.out.println(Constants.Whatcanido);
        System.out.println(Constants.line);
        String input = in.nextLine();
        while (!input.equals("bye")){
            System.out.println(Constants.line);
            System.out.println("    " + input);
            System.out.println(Constants.line);
            input = in.nextLine();
        }
        Constants.goodbye();
    }
}
