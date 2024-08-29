import java.util.Scanner;

public class ChattyCharlie {

    public static class List {
        private String[] list;
        private int size;

        //constructor
        public List() {
            list = new String[100];
            size = 0;
        }

        //Method to add an item to the list
        public void add(String text) {
            //add the text into the list
            list[size] = text;
            //account for the item
            size++;
        }

        //To print list
        public void toPrint() {
            //print all
            System.out.println("ToDo List:");
            for (int i = 0; i < size; i++) {
                int number = i+1;
                System.out.println("        " + number + ". " + list[i]);
            }
        }
    }

    public static void toDoMaker() { //Echo as a function
        String line;
        String you = "User: ";

        //make the scanner
        Scanner in = new Scanner(System.in);

        //create an instance of list class
        List toDo = new List();

        //accept an insert
        while (true) {
            System.out.print(you);
            line = in.nextLine();

            //if the line contains bye, it signals the end
            if (line.contains("Bye") || line.contains("bye")) {
                break;
            }
            //add or print
            if (line.equals("list")) {
                toDo.toPrint();
            } else {
                //add the item
                toDo.add(line);
                System.out.println("        Added: " + line);
            }
        }
    }

    public static void main(String[] args) {
        String logo = "   _____      \n"
                + "  /     \\     \n"
                + " |  O O  |    \n"
                + " | \\___/ |    \n"
                + "  \\_____/     \n"
                + " /\\_____/\\    \n"
                + " |       |    \n"
                + " |       |    \n"
                + " |_______|    \n"
                + "              \n";
        String charlie = "Charlie: ";

        String greeting = "Hello! I'm ChattyCharlie, your consistent buddy.\n"
                + "         What shall we do today?\n" ;

        String farewell = "All the best in clearing your list!";

        System.out.println(logo + charlie+ greeting);
        toDoMaker();
        System.out.println(charlie + farewell);

    }
}
