import java.util.Scanner;

public class Amy {
    public static void doEcho(String line){
        System.out.println(line + "! I wonder what I can do with this information (●'◡'●)");
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
            doEcho(line);
            filler();
            line = in.nextLine();
        }
        exit();
    }
}
