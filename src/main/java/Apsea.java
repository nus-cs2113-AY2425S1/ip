public class Apsea {
    public static void main(String[] args) {
        final String STRAIGHT_LINE = "    ________________________________________________________\n";

        String helloMessage = STRAIGHT_LINE
                + "    Hello! I'm Apsea!\n"
                + "    What can I do for you?\n"
                + STRAIGHT_LINE;

        String byeMessage = STRAIGHT_LINE
                + "    Bye. Hope to see you again soon!\n"
                + STRAIGHT_LINE;

        System.out.print(helloMessage);
        System.out.print(byeMessage);
    }
}
