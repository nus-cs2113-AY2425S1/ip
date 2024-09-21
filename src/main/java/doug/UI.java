package doug;

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

    /**
     * Prints the Doug Dimmadome ASCII art to the CLI
     */
    public static void printLogo() {
        for (int i = 0; i < hatHeight; i++) {
            System.out.println(hat);
        }
        System.out.println(face);
    }

    /**
     * Prints the message to new users to the CLI
     */
    public static void sayNewUserWelcome() {
        System.out.println(newWelcomeMessage);
    }

    /**
     * Prints the message to existing users to the CLI
     */
    public static void sayExistingUserWelcome() {
        System.out.println(existingWelcomeMessage);
    }

    /**
     * Prints the closing message to the CLI
     */
    public static void sayGoodbye() {
        System.out.println(goodbyeMessage);
    }
}
