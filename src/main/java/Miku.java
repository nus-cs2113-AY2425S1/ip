import java.util.Scanner;
import java.util.ArrayList;

public class Miku {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo =
            """
             __  __   _   _           \s
            |  \\/  | (_) | |          \s
            | \\  / |  _  | | __  _   _\s
            | |\\/| | | | | |/ / | | | |
            | |  | | | | |   <  | |_| |
            |_|  |_| |_| |_|\\_\\  \\__,_|
            """;
        //Text to Ascii generated through https://patorjk.com/software/taag/
        System.out.println(logo);

        printDivider();
        System.out.println("Hello! I'm Miku\nWhat can I do for you?");
        printDivider();

        ArrayList<String> list = new ArrayList<>();        
        String line = input.nextLine();
        printDivider();

        while(!line.equals("bye")){
            if (line.equals("list")){
                printList(list);
            }
            else{
                System.out.println("added: "+line);
                list.add(line);
            }
            printDivider();
            line = input.nextLine();
            printDivider();
        }

        System.out.println("Bye, see you later!");
        printDivider();

        input.close();
    }

    public static void printDivider(){
        System.out.println("____________________________________________________________");
    }

    public static void printList(ArrayList<String> list){
        for (int i=1;i<=list.size();i++){
            System.out.printf("%d. %s%n",i,list.get(i-1));
        }
    }
}
