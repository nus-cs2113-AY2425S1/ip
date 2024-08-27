import java.util.Objects;
import java.util.Scanner;

public class Ellio {

    private static String[] listTask = new String[100];
    private static int numberTask = 0;

    public static void endProgram(){
        System.out.println(BotText.lineBorder + BotText.goodbyeMessage + BotText.lineBorder);
    }

    public static void startProgram(){
        System.out.println(BotText.lineBorder + BotText.welcomeMessage + BotText.lineBorder);
    }

    public static void printList(){
        System.out.print(BotText.lineBorder);
        for(int i = 0; i < numberTask; i++){
            System.out.println((i+1) + ". " + listTask[i]);
        }
        System.out.println(BotText.lineBorder);
    }

    public static void addList(String line){
        listTask[numberTask] = line;
        numberTask++;
        System.out.println(BotText.lineBorder + "added: " + line + "\n" + BotText.lineBorder);
    }

    public static void getInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = (in.nextLine()).toLowerCase();

        while(!line.equals("bye")){
            if(line.equals("list")){
                printList();
            }
            else{
                addList(line);
            }
            line = in.nextLine().toLowerCase();
        }
        endProgram();
    }

    public static void main(String[] args) {
        startProgram();
        getInput();

    }
}
