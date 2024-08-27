import java.util.Objects;
import java.util.Scanner;

public class Ellio {

    public static void endProgram(){
        System.out.println(BotText.lineBorder + BotText.goodbyeMessage + BotText.lineBorder);
    }

    public static void startProgram(){
        System.out.println(BotText.lineBorder + BotText.welcomeMessage + BotText.lineBorder);
    }

    public static void getInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = (in.nextLine()).toLowerCase();

        while(!line.equals("bye")){
            System.out.println(BotText.lineBorder + line + "\n" + BotText.lineBorder);
            line = in.nextLine().toLowerCase();
        }
        endProgram();
    }

    public static void main(String[] args) {
        startProgram();
        getInput();

    }
}
