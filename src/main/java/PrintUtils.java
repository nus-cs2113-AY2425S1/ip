public class PrintUtils {
    private static final String LINE_BREAK =
            "________________________________________"; // 40 characters long

    private static final String NAME = "Jeremy";

    private static final String LOGO = """
                                                   \s
                            .-++++++-.             \s
                          -##+------+##+.          \s
                         +#+      ....+##+         \s
                        .##.  -####+++++###.       \s
                      ..+#+   ##+..      .+#-      \s
                    +#####-  .##--.........##      \s
                   +#+  ##-.  +##+++-+++++##+      \s
                   +#-.-##-.   -########+##+       \s
                   +#-.-##-.     ......  .##       \s
                   ##-.-##--.            -##       \s
                   ##---##---.          .-##       \s
                   +#-..##----...........-#+       \s
                   .##+-##--------------.+#-       \s
                    .+++##----.+#####+---##        \s
                        +#-.--.+#+.#+...-#+        \s
                        -#+----##+-##+++##-        \s
                      ++####################-      \s
                        ....------------...        \s
                                                   \s
            """;

    public static void print(String string) {
        System.out.print(string);
    }

    public static void println(String string) {
        print(string);
        newline();
    }

    public static void newline() {
        print("\n");
    }

    public static void lineBreak() {
        println(LINE_BREAK);
    }

    public static void logo() {
        print(LOGO);
        lineBreak();
    }

    public static void greeting() {
        lineBreak();
        println("Hello! I'm " + NAME + ".");
        println("What can I do for you?");
        lineBreak();
    }

    public static void bye() {
        lineBreak();
        println("Bye. Hope to see you again soon!");
        lineBreak();
    }
}
