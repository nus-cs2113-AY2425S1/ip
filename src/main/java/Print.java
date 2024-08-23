public class Print {
    // 40 characters long
    private static final String horizontalLine =
            "________________________________________";

    private static final String name = "Jeremy";

    public static void line() {
        System.out.println(horizontalLine);
    }

    public static void ascii() {
        String amongus = "                                        \n"
                + "                .-++++++-.              \n"
                + "              -##+------+##+.           \n"
                + "             +#+      ....+##+          \n"
                + "            .##.  -####+++++###.        \n"
                + "          ..+#+   ##+..      .+#-       \n"
                + "        +#####-  .##--.........##       \n"
                + "       +#+  ##-.  +##+++-+++++##+       \n"
                + "       +#-.-##-.   -########+##+        \n"
                + "       +#-.-##-.     ......  .##        \n"
                + "       ##-.-##--.            -##        \n"
                + "       ##---##---.          .-##        \n"
                + "       +#-..##----...........-#+        \n"
                + "       .##+-##--------------.+#-        \n"
                + "        .+++##----.+#####+---##         \n"
                + "            +#-.--.+#+.#+...-#+         \n"
                + "            -#+----##+-##+++##-         \n"
                + "          ++####################-       \n"
                + "            ....------------...         \n"
                + "                                        \n";

        System.out.print(amongus);
        line();
    }

    public static void greeting() {
        line();
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        line();
    }

    public static void bye() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
}
