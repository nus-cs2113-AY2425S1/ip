package jeremy.util;

import java.util.Scanner;

/**
 * The {@code Ui} class is responsible for handling input and output for the
 * user interface. It provides methods for reading commands, printing messages,
 * and displaying the application's logo and greeting.
 */
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

    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} object with a {@code Scanner} for reading input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user's input as a {@code String}.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the given string to the console without a newline.
     *
     * @param string The string to print.
     */
    public void print(String string) {
        System.out.print(string);
    }

    /**
     * Prints the given string to the console followed by a newline.
     *
     * @param string The string to print.
     */
    public void println(String string) {
        print(string);
        newline();
    }

    /**
     * Prints a newline character to the console.
     */
    public void newline() {
        print("\n");
    }

    /**
     * Prints a line break made of repeated underscores to the console.
     */
    public void lineBreak() {
        println(LINE_BREAK);
    }

    /**
     * Prints jeremy's logo to the console followed by a line break.
     */
    public void logo() {
        print(LOGO);
        lineBreak();
    }

    /**
     * Prints jeremy's greeting message, including the bot's name.
     */
    public void greeting() {
        lineBreak();
        println("Hello! I'm " + NAME + ".");
        println("What can I do for you?");
        lineBreak();
    }

    /**
     * Prints jeremy's exit message to the user.
     */
    public void bye() {
        lineBreak();
        println("Bye. Hope to see you again soon!");
        lineBreak();
    }
}
