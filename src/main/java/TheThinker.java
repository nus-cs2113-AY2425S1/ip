public class TheThinker {

    public static String name = "TheThinker";

    public static void main(String[] args) {
        separation();
        greet();
        separation();
        bye();
        separation();
    }

    public static void greet(){
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void separation(){
        System.out.println("____________________________________________________________");
    }

}
