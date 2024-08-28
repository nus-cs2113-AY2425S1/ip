import java.util.Scanner;

public class Amy {
    private static String[] text = new String[100];
    private static int inputNum = 0;
    public static void doEcho(String line){
        System.out.println(line + "! I wonder what I can do with this information (●'◡'●)");
    }
    public static void getUserInputList(){
        for(int i = 0; i<inputNum; i++){
            System.out.println((i+1) + ". " + text[i]);
        }
    }
    public static void addUserInputList(String input){
        text[inputNum++] = input;
        System.out.println("I added <" + input + "> to the list! ☆*: .｡. o(≧▽≦)o .｡.:*☆");
    }
    public static void doGreeting(){
        String name = "Amy";
        String greet = "Hello! I'm " + name + " ✿" + "\nWhat can I do for you? (❁´◡`❁) \n";
        System.out.println(greet);
    }
    public static void exit(){
        String bye = "Bye. Hope to see you again soon! (*/ω＼*)\n";
        System.out.println(bye);
    }
    public static void filler(){
        String filler = "____________________________________________________________" + "\n";
        System.out.println(filler);
    }
    public static void main(String[] args) {
        filler();
        doGreeting();
        filler();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(!line.equals("bye")){
            if(line.equals("list")){
                getUserInputList();
            }
            else {
                addUserInputList(line);
            }
            //doEcho(line);
            filler();
            line = in.nextLine().trim();
        }
        exit();
    }
}
