import java.util.Scanner;

public class Edith {
    Scanner sc=new Scanner(System.in);
    private static final String myName="Edith";
    private final static String horizontalLine="______________________________________________";


    public void giveIntroduction() {
        System.out.println("Hello I am " + myName + ".");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public void talkToUser() {
        do {
            String temporyString= sc.nextLine();
            System.out.println(horizontalLine);
            if(temporyString.equals("bye")){
                sayGoodbye();
                return;
            }

            System.out.println("added : " + temporyString);
            System.out.println(horizontalLine);
        } while (true);
    }
    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(horizontalLine + "\n");
    }

    public static void main(String[] args) {
        Edith obj = new Edith();
        obj.giveIntroduction();
        obj.talkToUser();
    }
}
