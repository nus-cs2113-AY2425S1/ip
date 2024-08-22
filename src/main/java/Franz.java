public class Franz {
    public static void main(String[] args) {
        lineMessage();
        helloMessage();
        lineMessage();
        byeMessage();
        lineMessage();
    }
    public static void lineMessage() {
        String line = "____________________________________________________________\n";
        System.out.print(line);
    }
    public static void helloMessage() {
        String greeting = "Hello! I'm Franz \n"
                + "What can I do for you?\n";
        System.out.print(greeting);
    }
    public static void byeMessage() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.print(bye);
    }
}