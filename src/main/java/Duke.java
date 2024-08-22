public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________\n";

        String helloMessage = horizontalLine
                + "Hello! I'm Apsea!\n"
                + "What can I do for you?\n"
                + horizontalLine;

        String byeMessage = horizontalLine
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine;

        System.out.println(helloMessage);
        System.out.println(byeMessage);
    }
}
