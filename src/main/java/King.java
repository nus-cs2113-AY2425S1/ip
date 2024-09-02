public class King {
    public static final String name = "King";


    public static void greeting() {
        String logo =
                "| |/ /|_ _|| \\| | / _` |\n" +
                        "| ' <  | | | .` || (_| |\n" +
                        "|_|\\_\\|___||_|\\_| \\__,_|\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void chatting() {
        System.out.println(" Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    public static void main(String[] args) {
        King.greeting();
        King.chatting();
    }
}
