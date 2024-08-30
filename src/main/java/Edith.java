import java.util.Scanner;
import java.util.ArrayList;

public class Edith {
    Scanner sc=new Scanner(System.in);
    private static final String myName="Edith";
    private final static String horizontalLine="______________________________________________";
    ArrayList<String> texts= new ArrayList<>();

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
            } else if(temporyString.equals("list")) {
                int indexNumber=1;
                for(String text: texts) {
                    System.out.println(indexNumber + ". " + text);
                    indexNumber++;
                }
                System.out.println(horizontalLine);
            } else {
                texts.add(temporyString);
                System.out.println("added : " + texts.get(texts.size()-1));
                System.out.println(horizontalLine);
            }
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
