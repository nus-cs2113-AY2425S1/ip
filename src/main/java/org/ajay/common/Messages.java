package org.ajay.common;

import org.ajay.ui.Color;

public class Messages {

        static final String LOGO = """
                              @@@@@@@@@@@@@@@@@@@@@@@
                          @@%     @@          @@      @@@
                       @@         @@          @@          @@
                    @@            @@          @@             @@
                  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                 @                                              *@
                @@@                                             @@@
               @  @@@                                         @@@  @
              @&    @@         @@@@@@@@@@@@@@@@@@@@@         @@.   @@
              @      @@@        @@              @@         @@@      @
              @       *@@         @@           @(         @@(       @
              @       ,@@@@        @@        @@         @@@@        @
              @   @@@@@  %@@         @@     @          @@& @@@@@    @
              @@@@         @@@        /@  @@         @@@       *@@@@/
               @@           @@@         @@          @@@           @@
                @@            @@&                 (@@            @@
                  @       @@@@@@@@               @@@@@@@        @
                   @@ @@@@*      @@,            @@      @@@@  @&
                      @@          @@@         @@@          @@
                        @@@         @@       @@         @@#
                            @@@@     @@@   @@@     @@@@
                                   @@@@@@@@@@@@@

                 ██╗    █████╗    ██████╗   ██╗   ██╗  ██╗   ███████╗
                 ██║   ██╔══██╗   ██╔══██╗  ██║   ██║  ██║   ██╔════╝
                 ██║   ███████║   ██████╔╝  ██║   ██║  ██║   ███████╗
            ██   ██║   ██╔══██║   ██╔══██╗  ╚██╗ ██╔╝  ██║   ╚════██║
            ╚█████╔╝██╗██║  ██║██╗██║  ██║██╗╚████╔╝██╗██║██╗███████║██╗
             ╚════╝ ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═╝╚═╝ ╚═══╝ ╚═╝╚═╝╚═╝╚══════╝╚═╝
            """;

    public static final String MESSAGE_PROMPT = Color.ANSI_GREEN + "  " + Color.ANSI_RESET;
    public static final String MESSAGE_BREAKLINE = Color.ANSI_BLACK + "────────────────────────────────────────────────────────────" + Color.ANSI_RESET;
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LOGO = "Hello from\n" + Color.ANSI_BLUE + LOGO + Color.ANSI_RESET;
    public static final String MESSAGE_WELCOME = "Hello! I'm " + Color.ANSI_YELLOW + Constants.CHAT_BOT_NAME + Color.ANSI_RESET + "\nWhat can I do for you?";
    public static final String MESSAGE_HELP = "Here are the list of commands you can use: \n1. todo <task> - Adds a todo task to the list \n2. deadline <task> /by <date> - Adds a deadline task to the list \n3. event <task> /from <date> /to <date> - Adds an event task to the list \n4. list - Lists all the tasks in the list \n5. done <task number> - Marks the task as done \n6. delete <task number> - Deletes the task from the list \n7. find <keyword> - Finds the tasks with the keyword \n8. help - Shows the list of commands \n9. bye | exit - Exits the program";
}
