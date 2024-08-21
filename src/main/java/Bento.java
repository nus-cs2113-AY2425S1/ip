public class Bento {
    public static void printLogo() {
        String logo = "  ____             _        \n"
                + " | __ )  ___ _ __ | |_ ___  \n"
                + " |  _ \\ / _ \\ '_ \\| __/ _ \\ \n"
                + " | |_) |  __/ | | | || (_) |\n"
                + " |____/ \\___|_| |_|\\__\\___/ \n"
                + "                            \n";
        System.out.print(logo);
    }

    public static void sayKonichiwa() {
        System.out.println("Konichiwa! I am Bento, your personal assistant!\nHow can I help you with your tasks today?");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void saySayonara() {
        System.out.println("Thank you for working with me today! See you next time! Sayonara~");
    }

    public static void main(String[] args) {
        printLine();
        printLogo();
        sayKonichiwa();
        printLine();
        saySayonara();
        printLine();
    }
}