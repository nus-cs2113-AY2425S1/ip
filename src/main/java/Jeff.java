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

    public static void updateList(String line, String[] list, int count){
        list[count] = line;
    }

    public static void printList(String[] list, int count){
        for(int i = 0; i < count; i++){
            System.out.println((i+1) + ". " + list[i]);
        }
    }

    public static void echo(String[] list){
        String divider = "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("You say:");
        int count = 0;
        while(!(line = in.nextLine()).equals("bye")){
            if(line.equals("list")){
                printList(list, count);
            } else {
                updateList(line, list, count);
                count++;
                String echo = randomlyCapitalise(line);
                System.out.println(divider + System.lineSeparator() + "I echo:");
                System.out.println(echo + System.lineSeparator() + divider);
                System.out.println("You say:");
            }
        }
    }

    public static void main(String[] args) {
        String  introText = """
                ____________________________________________________________
                Hello! I'm JEFF
                I will echo whatever you say, and store them in a list!
                However, I will echo what you say with random capitalisation!
                
                Type 'list' to display everything you've said!
                Type 'bye' to exit!
                ____________________________________________________________
                """;

        String exitText = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;

        String[] list = new String[100];
        System.out.print(introText);
        echo(list);
        System.out.println(exitText);
    }
}
