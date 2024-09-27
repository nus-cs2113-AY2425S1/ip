package doug;

import java.util.Scanner;

/**
 * Class that deals with showing certain UI elements to the user
 */
public class UI {
    public static final String DASHED_LINE = "__________________________________________________________\n";
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
     * Prints the Doug Dimmadome ASCII art to the CLI
     */
    public void printLogo() {
        for (int i = 0; i < hatHeight; i++) {
            System.out.println(hat);
        }
        System.out.println(face);
    }

    /**
     * Prints the message to new users to the CLI
     */
    public void sayNewUserWelcome() {
        System.out.println(newWelcomeMessage);
    }

    /**
     * Prints the message to existing users to the CLI
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
