package bean.ui;

import static bean.constants.Constants.LOGO;
import static bean.constants.Constants.SEPARATOR_LINE;
import static bean.constants.Constants.INDENT;
import static bean.constants.Constants.DELIMITER;

public class Ui {

    /**
     * Prints logo with greeting message
     */
    public static void greet() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Howdy! :) I'm bean, your personal assistant.\n" +
                INDENT + "Let me help you keep track of your tasks!\n" +
                SEPARATOR_LINE
        );
    }

    /**
     * Prints logo with exit message
     */
    public static void exit() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Bye bye, glad I could help! See you soon? :'(\n" +
                SEPARATOR_LINE);
    }

    /**
     * Prints message with separator line above and below message
     */
    public static void printFormattedReply(String reply) {
        System.out.println(SEPARATOR_LINE +
                reply + "\n" +
                SEPARATOR_LINE);
    }

    /**
     * Prints error message for when user input is invalid
     */
    public static void printInvalidInputMessage() {
        printFormattedReply(INDENT + "Sorry, I am not equipped to respond to that yet... :(\n" +
                INDENT + "These are the commands I understand:\n" +
                INDENT + "1. To add a new task:\n" +
                INDENT + INDENT + "a. todo [description]\n" +
                INDENT + INDENT + "b. deadline [description] /by [by]\n" +
                INDENT + INDENT + "c. event [description] /from [from] /to [to]\n" +
                INDENT + INDENT + INDENT + "example: event dinner /from 6pm /to 8pm\n" +
                INDENT + "2. To view your task list: list\n" +
                INDENT + "3. To mark a task as done: mark [task number]\n" +
                INDENT + "4. To mark a task as undone: unmark [task number]\n" +
                INDENT + "5. To delete a task from your task list: delete [task number]");
    }
}
