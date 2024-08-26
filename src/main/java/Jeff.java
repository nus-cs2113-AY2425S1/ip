import java.util.Scanner;
import java.util.Random;

public class Jeff {

    private static String randomlyCapitalise(String line){
        Random randomBool = new Random();
        StringBuilder randomString = new StringBuilder(line.length());
        for(int i = 0; i < line.length(); i++){
            if(randomBool.nextBoolean()) {
                randomString.append(Character.toUpperCase(line.charAt(i)));
            } else {
                randomString.append(Character.toLowerCase(line.charAt(i)));
            }
        }
        return randomString.toString();
    }

    public static void echo(){
        String divider = "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("You say:");
        while(!(line = in.nextLine()).equals("bye")){
            String echo = randomlyCapitalise(line);
            System.out.println(divider + System.lineSeparator() + "I echo:");
            System.out.println(echo + System.lineSeparator() + divider);
            System.out.println("You say:");
        }
    }

    public static void main(String[] args) {
        String  introText = """
                ____________________________________________________________
                Hello! I'm JEFF
                I will echo whatever you say!
                However, I will return them with random capitalisation!
                Type 'bye' to exit!
                ____________________________________________________________
                """;

        String exitText = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;
        System.out.print(introText);
        echo();
        System.out.println(exitText);
    }
}
