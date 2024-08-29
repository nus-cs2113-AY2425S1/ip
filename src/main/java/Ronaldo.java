import java.util.Scanner;

public class Ronaldo {
    public static void main(String[] args) {
        String indent = "___________________________________________ \n";
        System.out.println( indent + "Hello! I'm Ronaldo, Cristiano Ronaldo! The greatest footballer of all time. SIUUUUUUU! \n"+ "Hehehe what can I do for you? \n"  + indent + "Bye. Hope to see you soon! SIUUUUUUU!\n" + indent);

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you soon! SIUUUUUUU!");
                break;
            } else {
                System.out.println(input + "\n");
            }
        }
    }
}
