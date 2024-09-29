package ellio.ui;

import ellio.BotText;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;

    /**
     * Constructor for Ui Object. Creates the scanner for user input
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * To close scanner once no more user input is expected
     */
    public void closeScanner(){
        in.close();
    }

    /**
     * Scans for the next user input line and returns it for parsing
     * @return Current user input line
     */
    public String readCommand(){
        String input;
        input = in.nextLine();
        return input;
    }

    /**
     * Display the Line Border
     */
    public void showLine(){
        System.out.println(BotText.LINE_BORDER);
    }

    /**
     * Display the Line Border without a break line
     */
    public void showLineWithoutNewline(){
        System.out.print(BotText.LINE_BORDER);
    }

    /**
     * Display Custom Show List message
     */
    public void showList(){
        System.out.print(BotText.LINE_BORDER + BotText.MESSAGE_LIST);
    }

    /**
     * Display Custom Welcome Message
     */
    public void showWelcome(){
        System.out.println(BotText.LINE_BORDER +
                BotText.MESSAGE_WELCOME +
                BotText.LINE_BORDER);
    }

    /**
     * Display Custom end Program Message
     */
    public void endProgram(){
        System.out.println(BotText.LINE_BORDER +
                BotText.MESSAGE_GOODBYE +
                BotText.LINE_BORDER);
    }

    /**
     * Display Custom Failed Search Message
     */
    public void showFailedSearch(){
        System.out.println("I have failed to find a match, Please try with a different keyword.");
    }

    /**
     * Display Custom Successful Match Message
     */
    public void showMatch(){
        System.out.print("Here are the matching tasks in your list:\n");
    }

}
