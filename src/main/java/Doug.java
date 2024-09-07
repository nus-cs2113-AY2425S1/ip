import java.util.Scanner;

public class Doug {

    private static final String DASHED_LINE = "______________________________________________\n";
    private static int counter = 0;

    // Declaring Magic Literals
    static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];

    static final int DEADLINE_NAME_START = 9;
    static final int DEADLINE_DATE_START = 4;

    static final int EVENT_NAME_START = 6;
    static final int EVENT_FROM_START = 6;
    static final int EVENT_TO_START = 4;


    public static boolean checkOutOfBounds(int listIndex) {
        if (listIndex > counter || listIndex <= 0) {
            System.out.println(DASHED_LINE + "No can do bud, your input is invalid!\n" + DASHED_LINE);
            return true;
        }
        return false;
    }

    public static boolean checkListFull() {
        if (counter >= MAX_TASKS) {
            System.out.println(DASHED_LINE + "Sorry partner, the list is full!" + DASHED_LINE);
            return true;
        }
        return false;
    }

    public static void doList() {
        if (counter == 0) {
            System.out.println(DASHED_LINE + "Got nothing on your roster bud.");
            System.out.print(DASHED_LINE);
            return;
        }

        System.out.println(DASHED_LINE + "Here, let me lay out your tasks for you.");
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + "." + tasks[i].toString());
        }
        System.out.print(DASHED_LINE);
    }

    public static void doMark(String command) {
        int listIndex = Integer.parseInt(command.substring(5));

        if (checkOutOfBounds(listIndex)) {
            return;
        }

        tasks[listIndex - 1].markAsDone();
        System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as done");
        System.out.print(tasks[listIndex - 1].toString() + "\n" + DASHED_LINE);
    }

    public static void doUnmark(String command) {
        int listIndex = Integer.parseInt(command.substring(7));

        if (checkOutOfBounds(listIndex)) {
            return;
        }

        tasks[listIndex - 1].markAsNotDone();
        System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as not done");
        System.out.print(tasks[listIndex - 1].toString() + "\n" + DASHED_LINE);
    }

    public static void doToDo(String command) {
        Todo todoTask = new Todo(command.substring(5));
        tasks[counter] = todoTask;
        counter++;
        System.out.println(DASHED_LINE + "added: " + command + "\n" + DASHED_LINE);
    }

    public static void doDeadline(String command) {
        int indexOfSlash = command.indexOf('/');

        String taskName = command.substring(DEADLINE_NAME_START, indexOfSlash - 1);
        String taskDeadline = command.substring(indexOfSlash + DEADLINE_DATE_START);

        Deadline deadlineTask = new Deadline(taskName, taskDeadline);
        tasks[counter] = deadlineTask;
        counter++;
        System.out.println(DASHED_LINE + "added: " + taskName + "\n" + DASHED_LINE);
    }

    public static void doEvent(String command) {
        int indexOfFirstSlash = command.indexOf('/');
        int indexOfSecondSlash = command.indexOf('/', indexOfFirstSlash + 1);

        String taskName = command.substring(EVENT_NAME_START, indexOfFirstSlash - 1);
        String taskStart = command.substring(indexOfFirstSlash + EVENT_FROM_START, indexOfSecondSlash - 1);
        String taskEnd = command.substring(indexOfSecondSlash + EVENT_TO_START);

        Event eventTask = new Event(taskName, taskStart, taskEnd);
        tasks[counter] = eventTask;
        counter++;
        System.out.println(DASHED_LINE + "added: " + taskName + "\n" + DASHED_LINE);
    }

    public static void sayWelcome() {
        String hat = "                                    %,                         ,#.\n";
        for (int i = 0; i < 51; i++) {
            System.out.println(hat);
        }
        String face = "             (@@%*.                 #/                         ,%, \n"
                + "            (*      ./&@%/.         #/                         .%.\n"
                + "           .#.              .*#&&(, /(.,...............,.......*#\n"
                + "            (/                       ,(#(*,..,...,...........,./(       ./%@&#*.       *&/\n"
                + "            .#*                              ./(/*,...........,(/,#&%*                   *&\n"
                + "              (%                                      ,**.   */.                          %.\n"
                + "                ##                                 /%%# (&%&%&%&%%%    ...,,,***//((##%%%&#\n"
                + "                  (%#%%%&&&&&&&&&&&%&%*,,.*/**(%%%@@@%/**////*((.\n"
                + "                                    .#*,,.//*/@@%/#/**/(*(***(#(\n"
                + "                                /&@&/(,../*******/, ((.*/*.(/*#/\n"
                + "                               %(****##/************//**//((((#####*(&&/\n"
                + "                              .&*/**/******/*....,.,/%/**********(#,.../#&*\n"
                + "                               /%*//*//***/,....,.,...*#*******((.,,....,/#&\n"
                + "                                 .*/*(#***/. .,..,.....,.,,,.....,..,...,/*(&\n"
                + "                                     *#***(**///(((((((////*****************%*\n"
                + "                                     *#*************************************#(\n"
                + "                                     *#*************************************%/\n"
                + "                                     ,#*************************************&@@/\n"
                + "                                     ,#***************/&%(/*****////***/%@@@@@%.\n"
                + "                                     ,(.      **       %@@@@&&&@@@@@&&&@@&&@@@#.\n"
                + "                                     ,#.       .*.     ,@@@@@%%&@@@&#&@&&@@@@#* .(&/\n"
                + "                                     ,#.         *,  */ %@@@@(  /@@@%.    ,#&(*/    ,&%.\n"
                + "                                     .#(*******,   %,   */      (@%&@&        *.#. ,/, .#/\n"
                + "                                     .#,     (.     #(          #@&(@@&      *,  /*    ./*#%\n"
                + "                                     .#,  ./.       .*.(.      .&@@/%@@%    *,    **      (.#/\n"
                + "                                     .(,  ,/.        .*  ,/    *&@@//@@@(  /.      *,     (  *#\n"
                + "                                     .#,     */       ./   .(. /@@@#,&@@@//         /.    (   .&.\n"
                + "                                     .#/,      ./      ,*     ,%@@@%.#@@@%.         .(   ./    .&.\n"
                + "                                     .#*.,.    .,.(     /.    .#@@@&**@@@@(          **  *,     /(\n"
                + "                                     .%*.,     .,  ./   .*    .%@@@@#.&@@@@,         (  /     .#.\n";

        System.out.println(face);


        // Welcome statement
        System.out.println(DASHED_LINE + "Howdy partner! My name is Doug Dimmadome.\n"
                + "Now what can I do for ya?\n" + DASHED_LINE);
    }


    public static void main(String[] args) {

        //sayWelcome();
        Scanner input = new Scanner(System.in);

        while (true) {

            String command = input.nextLine().trim();

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")){
                doList();
            } else if (command.startsWith("mark")) {
                doMark(command);
            } else if (command.startsWith("unmark")) {
                doUnmark(command);
            } else if (command.startsWith("todo")) {
                if (checkListFull()) {
                    continue;
                }
                doToDo(command);
            } else if (command.startsWith("deadline")) {
                if (checkListFull()) {
                    continue;
                }
                doDeadline(command);
            } else if (command.startsWith("event")) {
                if (checkListFull()) {
                    continue;
                }
                doEvent(command);
            } else {
                System.out.println(DASHED_LINE + "Something seems off partner...\n" + DASHED_LINE);
            }
        }

        // Closing Statement
        System.out.println(DASHED_LINE + "It's been a pleasure partner.\n" +
                "Hope to see you around these parts again soon!\n" + DASHED_LINE);

    }
}
