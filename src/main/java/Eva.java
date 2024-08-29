import java.util.Scanner;


public class Eva {
    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";


    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Eva!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);


        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        String[] sentenceArray = new String[100];


        while(true)
        {
            line = in.nextLine();


            if(line.equalsIgnoreCase("list"))
            {
                for(int i = 0; i < count; i ++)
                {
                    System.out.println(i + 1 + ". " + sentenceArray[i]);
                }
                System.out.println(HORIZONTAL_LINE);
                continue;
            }


            sentenceArray[count] = line;
            count++;


            if(line.equalsIgnoreCase("Bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            }


            System.out.println("added: " + line);
            System.out.println(HORIZONTAL_LINE);
        }
    }


}
