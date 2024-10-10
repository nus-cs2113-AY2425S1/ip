package tommi;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tommi {

    /**
     * Main method that runs. Handles printing of the intro message and exit message,
     * and ensure Parser handles intermediate inputs
     *
     * @param args not used
     * @throws FileNotFoundException If Scanner has Error
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        Ui.printIntroMessage();
        Parser.readInputStrings(scanner);
        Ui.printExitMessage();
    }
}
