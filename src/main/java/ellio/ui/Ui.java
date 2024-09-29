package ellio.ui;

import ellio.BotText;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void closeScanner(){
        in.close();
    }

    public String readCommand(){
        String input;
        input = in.nextLine();
        return input;
    }

    public void showLine(){
        System.out.println(BotText.LINE_BORDER);
    }

    public void showList(){
        System.out.print(BotText.LINE_BORDER + BotText.MESSAGE_LIST);
    }

    public void showWelcome(){
        System.out.println(BotText.LINE_BORDER +
                BotText.MESSAGE_WELCOME +
                BotText.LINE_BORDER);
    }
    public void endProgram(){
        System.out.println(BotText.LINE_BORDER +
                BotText.MESSAGE_GOODBYE +
                BotText.LINE_BORDER);
    }


}
