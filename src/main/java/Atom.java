public class Atom {
    public static void divider() {
        System.out.println("__________________________________________________");
    }

    public static void main(String[] args) {

        divider();

        String logo = "    ____   __________  ________  __       __\n"
                    + "   / __ \\ |___    ___||  ____  ||  \\     /  |\n"
                    + "  / /__\\ \\    |  |    | |    | || |\\\\   //| |\n"
                    + " / /    \\ \\   |  |    | |____| || | \\\\_// | |\n"
                    + "/_/      \\_\\  |__|    |________||_|  \\_/  |_|\n";

        System.out.println(logo);

        divider();

        System.out.println("Hey there! I'm your friendly chatbot, ATOM!");
        System.out.println("How can i assist you today?");

        divider();

        System.out.println("Bye Bye. See ya soon!");

        divider();
    }
}
