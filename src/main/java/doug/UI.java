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
            + "Now what can I do for ya?\n"
            + DASHED_LINE;
    private static final String existingWelcomeMessage
            = DASHED_LINE
            + "Welcome back partner, YEEHAW!\n"
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
     * Prints the dashed line to CLI
     */
    public void printDashedLine() {
        System.out.println(DASHED_LINE);
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
