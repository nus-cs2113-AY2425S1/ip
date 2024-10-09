package doug;

import java.util.Scanner;

/**
 * Class that deals with showing certain UI elements to the user
 */
public class UI {
    public static final String DASHED_LINE
            = "_____________________________________________________________________________________________________\n";
    private static final String hat = "                                    %,                         ,#.\n";
    private static final int hatHeight = 50;
    private static final String face = """
                             (@@%*.                 #/                         ,%,\s
                            (*      ./&@%/.         #/                         .%.
                           .#.              .*#&&(, /(.,...............,.......*#
                            (/                       ,(#(*,..,...,...........,./(       ./%@&#*.       *&/
                            .#*                              ./(/*,...........,(/,#&%*                   *&
                              (%                                      ,**.   */.                          %.
                                ##                                 /%%# (&%&%&%&%%%    ...,,,***//((##%%%&#
                                  (%#%%%&&&&&&&&&&&%&%*,,.*/**(%%%@@@%/**////*((.
                                                    .#*,,.//*/@@%/#/**/(*(***(#(
                                                /&@&/(,../*******/, ((.*/*.(/*#/
                                               %(****##/************//**//((((#####*(&&/
                                              .&*/**/******/*....,.,/%/**********(#,.../#&*
                                               /%*//*//***/,....,.,...*#*******((.,,....,/#&
                                                 .*/*(#***/. .,..,.....,.,,,.....,..,...,/*(&
                                                     *#***(**///(((((((////*****************%*
                                                     *#*************************************#(
                                                     *#*************************************%/
                                                     ,#*************************************&@@/
                                                     ,#***************/&%(/*****////***/%@@@@@%.
                                                     ,(.      **       %@@@@&&&@@@@@&&&@@&&@@@#.
                                                     ,#.       .*.     ,@@@@@%%&@@@&#&@&&@@@@#* .(&/
                                                     ,#.         *,  */ %@@@@(  /@@@%.    ,#&(*/    ,&%.
                                                     .#(*******,   %,   */      (@%&@&        *.#. ,/, .#/
                                                     .#,     (.     #(          #@&(@@&      *,  /*    ./*#%
                                                     .#,  ./.       .*.(.      .&@@/%@@%    *,    **      (.#/
                                                     .(,  ,/.        .*  ,/    *&@@//@@@(  /.      *,     (  *#
                                                     .#,     */       ./   .(. /@@@#,&@@@//         /.    (   .&.
                                                     .#/,      ./      ,*     ,%@@@%.#@@@%.         .(   ./    .&.
                                                     .#*.,.    .,.(     /.    .#@@@&**@@@@(          **  *,     /(
                                                     .%*.,     .,  ./   .*    .%@@@@#.&@@@@,         (  /        .#.
                """;

    private static final String newWelcomeMessage
            = DASHED_LINE
            + "YEEHAW, they call me Doug Dimmadome in these parts.\n"
            + "I see this is your first rodeo, partner.\n"
            + "If you are ever confused on what's what, just holler \"help\" and I'll clear things up for ya.\n"
            + "Now what can I do for ya?\n"
            + DASHED_LINE;
    private static final String existingWelcomeMessage
            = DASHED_LINE
            + "Welcome back partner, YEEHAW!\n"
            + "If you are ever confused on what's what, just holler \"help\" and I'll clear things up for ya.\n"
            + "Now what can old Doug do for ya?\n"
            + DASHED_LINE;
    private static final String goodbyeMessage
            = DASHED_LINE
            + "It's been a pleasure partner.\n"
            + "Hope to see you around these parts again soon!\n"
            + DASHED_LINE;
    private static final String confusedMessage
            = DASHED_LINE
            + "It ain't clear to me what you wanna do pal...\n"
            + DASHED_LINE;
    private static final String missingIndexMessage
            = DASHED_LINE
            + "You're missing the index number pal...\n"
            + DASHED_LINE;

    private static final String listOfCommands
            = DASHED_LINE
            + "This is all the services I offer partner:\n"
            + "0. help                              -  Lists out all the available commands and their useage\n"
            + "1. list                              -  Lists out all the existing tasks in order of creation\n"
            + "2. todo TASK_NAME                    -  Creates a new todo task\n"
            + "3. deadline TASK_NAME /by DUE_DATE   -  Creates a new deadline task\n"
            + "4. event TASK_NAME /from START_DATE /to END_DATE  -  Creates a new event task\n"
            + "5. mark INDEX                        -  Marks the task (with that index) as done\n"
            + "6. unmark INDEX                      -  Marks the task (with that index) as not done\n"
            + "7. delete INDEX                      -  Deletes the task (with that index) from the list\n"
            + "8. find KEYWORDS                     -  Finds and lists all tasks that match the input keywords\n"
            + "9. bye                               -  Ends the program\n"
            + DASHED_LINE
            + "The format for the DUE_DATE, START_DATE and END_DATE parameters must be one of the following:\n"
            + "1. In Text form                                  (e.g. tomorrow)\n"
            + "2. In Date form              yyyy-MM-dd          (e.g. 2001-11-09)\n"
            + "3. In Date AND Time form     yyyy-MM-dd hhmm     (e.g. 2020-03-14 1745)\n"
            + DASHED_LINE
            + "Now what can I do for ya?\n"
            + DASHED_LINE;

    private Scanner input;

    public UI() {
        input = new Scanner(System.in);
    }

    /**
     * Reads in user input from the CLI
     *
     * @return full line from CLI as a String
     */
    public String readCommand() {
        return input.nextLine().trim();
    }

    /**
     * Returns the dashed line as a string
     *
     * @return dashed line string
     */
    public String getDashedLine() {
        return DASHED_LINE;
    }

    /**
     * Prints the dashed line to the CLI
     */
    public void printDashedLine() {
        System.out.println(DASHED_LINE);
    }

    /**
     * Prints a list of all available commands to the CLI
     */
    public void printListOfCommands() {
        System.out.println(listOfCommands);
    }

    /**
     * Get the confused message string
     *
     * @return confused message string
     */
    public String getConfusedMessage() {
        return confusedMessage;
    }

    /**
     * Get the missing index message string
     *
     * @return missing index message string
     */
    public String getMissingIndexMessage() {
        return missingIndexMessage;
    }

    /**
     * Prints the Doug Dimmadome ASCII art to the CLI
     */
    public void printLogo() {
        for (int i = 0; i < hatHeight; i++) {
            System.out.println(hat);
        }
        System.out.println(face);
    }

    /**
     * Prints the welcome message to new users to the CLI
     */
    public void sayNewUserWelcome() {
        System.out.println(newWelcomeMessage);
    }

    /**
     * Prints the welcome message to existing users to the CLI
     */
    public void sayExistingUserWelcome() {
        System.out.println(existingWelcomeMessage);
    }

    /**
     * Prints the closing message to the CLI
     */
    public void sayGoodbye() {
        System.out.println(goodbyeMessage);
    }
}
