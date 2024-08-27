import java.util.Scanner;

public class Ellio {

    private static Task[] listTask = new Task[100];
    private static int numberTask = 0;

    public static void endProgram(){
        System.out.println(BotText.lineBorder + BotText.messageGoodbye + BotText.lineBorder);
    }

    public static void startProgram(){
        System.out.println(BotText.lineBorder + BotText.messageWelcome + BotText.lineBorder);
    }

    public static void printList(){
        System.out.print(BotText.lineBorder + BotText.messageList);
        for(int i = 0; i < numberTask; i++){
            System.out.println((i+1) + "." + listTask[i].getTask());
        }
        System.out.println(BotText.lineBorder);
    }

    public static void addList(String line){
        listTask[numberTask] = new Task(line);
        numberTask++;
        System.out.println(BotText.lineBorder + "added: " + line + "\n" + BotText.lineBorder);
    }

    public static void markList(int index){
        listTask[index-1].markTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageMarked + "  " + listTask[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void unmarkList(int index){
        listTask[index-1].unmarkTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageUnmark + "  " + listTask[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void getInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = (in.nextLine()).toLowerCase();

        while(!line.equals("bye")){
            if(line.equals("list")){
                printList();
            }
            else if(line.startsWith("mark")){
                String[] words = line.split(" ");
                markList(Integer.parseInt(words[1]));
            }
            else if(line.startsWith("unmark")){
                String[] words = line.split(" ");
                unmarkList(Integer.parseInt(words[1]));
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
