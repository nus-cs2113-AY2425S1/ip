public class Miku {
    public static void main(String[] args) {
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
        System.out.println("Bye, see you later!");
        printDivider();
    }

    public static void printDivider(){
        System.out.println("____________________________________________________________");
    }
}
