public class Joe {
    public static void main(String[] args) {
        String logo = "     _            \n"
            + "    (_)           \n"
            + "     _  ___   ___ \n"
            + "    | |/ _ \\ / _ \\\n"
            + "    | | (_) |  __/\n"
            + "    | |\\___/ \\___|\n"
            + "   _/ |           \n"
            + "  |__/            \n";
        System.out.println("Hello from\n" + logo);
        joeInteracts();
    }

    public static void joeInteracts() {

        String greeting = "Hello! I'm Joe.\n";
        String farewell = "See you soon!\n";
        System.out.println("____________________________________________________________\n" +
                greeting);

        System.out.println("\n" +
                "____________________________________________________________\n" +
                farewell +
                "____________________________________________________________\n");
    }
}
