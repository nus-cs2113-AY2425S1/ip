public class Jeremy {
    public static void printSus() {
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
    }

    public static void main(String[] args) {
        String name = "Jeremy";

        // 40 characters long
        String horizontalLine = "________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        printSus();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
