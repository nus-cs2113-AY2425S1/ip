package jeremy.util;

public class Ui {
    private static final String LINE_BREAK = "_".repeat(40);

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

    public void print(String string) {
        System.out.print(string);
    }

    public void println(String string) {
        print(string);
        newline();
    }

    public void newline() {
        print("\n");
    }

    public void lineBreak() {
        println(LINE_BREAK);
    }

    public void logo() {
        print(LOGO);
        lineBreak();
    }

    public void greeting() {
        lineBreak();
        println("Hello! I'm " + NAME + ".");
        println("What can I do for you?");
        lineBreak();
    }

    public void bye() {
        lineBreak();
        println("Bye. Hope to see you again soon!");
        lineBreak();
    }
}
