import java.util.Scanner;

public class KenChat {
    public static void printLine() {
        String line = "____________________________________" ;
        System.out.println(line);
    }

    public static void startProgramme() {
        String chatBotName = "KenChat";
        printLine();
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void endProgramme() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void displayList(String[] doList){
        printLine();
        for (int i=0; i< doList.length; i++){
            if(doList[i] != null){
                System.out.println((i+1)+". "+doList[i]);
            }
        }
        printLine();
    }

    public static void addList(String[] doList, String item){
        printLine();
        System.out.println("added: "+item);
        printLine();
        for (int i=0; i< doList.length; i++){
            if(doList[i] == null){
                doList[i] = item;
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        boolean running = true;
        int arraySize = 100;
        String[] doList = new String[arraySize];
        startProgramme();
        while (running){
            System.out.println();
            String str= sc.nextLine();
            if (str.equalsIgnoreCase("bye"))
                running = false;
            else if(str.equalsIgnoreCase("list"))
                displayList(doList);
            else
                addList(doList, str);
        }
        endProgramme();
    }
}
