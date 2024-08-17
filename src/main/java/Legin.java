public class Legin {
    public static void horizontalLine() {
        System.out.println("______________________" +
                "______________________________________");
    }
    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Legin");
        System.out.println("What can I do for you?");
        horizontalLine();
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }
    public static void main(String[] args) {
        greet();
        bye();
    }
}
