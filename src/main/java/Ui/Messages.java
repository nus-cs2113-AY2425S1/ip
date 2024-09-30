package Ui;

import utils.Storage;

/**
 * The {@code Messages} class contains static final strings used for displaying
 * various messages and UI elements in the application.
 * <p>
 * This class includes:
 * <ul>
 *   <li>{@link #LOGO} - The ASCII art logo of the application.</li>
 *   <li>{@link #CHAT_PREFIX} - The prefix used for chat messages.</li>
 *   <li>{@link #CHAT_BAR} - A separator bar used in the chat UI.</li>
 *   <li>{@link #MESSAGE_GOODBYE} - The message displayed when the user exits the application.</li>
 *   <li>{@link #MESSAGE_INVALID_COMMAND_FORMAT} - The message displayed when the user enters an invalid command format.</li>
 * </ul>
 * </p>
 */
public class Messages {
    public static final String LOGO =   
            "   ______      __                       \n"+
            "  / ____/_  __/ /_  ____  ____  ___     \n"+
            " / /   / / / / __ \\/ __ \\/ __ \\/ _ \\\n"+
            "/ /___/ /_/ / /_/ / /_/ / / / /  __/    \n"+
            "\\____/\\__,_/_.___/\\____/_/ /_/\\___/ \n";
    public static final String CHAT_PREFIX = "\n(Cubone) ";
    public static final String CHAT_BAR = "---------------------------------";

    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
}
