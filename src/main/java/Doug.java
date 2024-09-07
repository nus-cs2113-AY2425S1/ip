import java.util.Scanner;

public class Doug {

    // Declaring Magic Literals
    private static final String DASHED_LINE = "______________________________________________\n";
    static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int counter = 0;

    private static boolean saidBye = false;


    public static void checkOutOfBounds(int listIndex) throws DougException {
        if (listIndex > counter || listIndex <= 0) {
            throw new DougException(DASHED_LINE + "No can do bud, your input is invalid!\n" + DASHED_LINE);
        }
    }

    public static void checkListFull() throws DougException {
        if (counter >= MAX_TASKS) {
            throw new DougException(DASHED_LINE + "Sorry partner, the list is full!\n" + DASHED_LINE);
        }
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

        try {
            checkOutOfBounds(listIndex);

            tasks[listIndex - 1].markAsDone();
            System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as done");
            System.out.print(tasks[listIndex - 1].toString() + "\n" + DASHED_LINE);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void doUnmark(String command) {
        int listIndex = Integer.parseInt(command.substring(7));

        try {
            checkOutOfBounds(listIndex);

            tasks[listIndex - 1].markAsNotDone();
            System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as not done");
            System.out.print(tasks[listIndex - 1].toString() + "\n" + DASHED_LINE);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void doToDo(String command) throws DougException{
        checkListFull();

        command = command.replace("todo", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }

        Todo todoTask = new Todo(command);
        tasks[counter] = todoTask;
        counter++;

        System.out.println(DASHED_LINE + "I've added: " + todoTask + " for you.");
        System.out.println("Your list is now " + counter + " tasks long partner\n" + DASHED_LINE);
    }

    public static void doDeadline(String command) throws DougException {
        checkListFull();

        command = command.replace("deadline", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do here pal...\n" + DASHED_LINE);
        }
        if (!command.contains("/by")) {
            throw new DougException(DASHED_LINE + "You're missing the \"/by\" keyword.\n" + DASHED_LINE);
        }

        int indexOfSlash = command.indexOf("/by");
        String taskName = command.substring(0, indexOfSlash).trim();
        if (taskName.isEmpty()) {
            throw new DougException(DASHED_LINE + "Didn't name your deadline task did you bud?\n" + DASHED_LINE);
        }

        command = command.replace(taskName, "");
        String taskDeadline = command.replace("/by", "").trim();
        if (taskDeadline.isEmpty()) {
            throw new DougException(DASHED_LINE + "Forgot to mention when it's due by, huh bud?\n" + DASHED_LINE);
        }

        Deadline deadlineTask = new Deadline(taskName, taskDeadline);
        tasks[counter] = deadlineTask;
        counter++;

        System.out.println(DASHED_LINE + "I've added: " + deadlineTask + " for you.");
        System.out.println("Your list is now " + counter + " tasks long partner\n" + DASHED_LINE);
    }

    public static void doEvent(String command) throws DougException {
        checkListFull();

        command = command.replace("event", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do here pal...\n" + DASHED_LINE);
        }
        if (!command.contains("/from")) {
            throw new DougException(DASHED_LINE + "You're missing the \"/from\" keyword.\n" + DASHED_LINE);
        }
        if (!command.contains("/to")) {
            throw new DougException(DASHED_LINE + "You're missing the \"/to\" keyword.\n" + DASHED_LINE);
        }

        int indexOfFirstSlash = command.indexOf("/from");
        String taskName = command.substring(0, indexOfFirstSlash).trim();
        if (taskName.isEmpty()) {
            throw new DougException(DASHED_LINE + "Didn't name your event did you bud?\n" + DASHED_LINE);
        }

        command = command.replace(taskName, "");
        command = command.replace("/from", "").trim();
        int indexOfSecondSlash = command.indexOf("/to");
        String taskStart = command.substring(0, indexOfSecondSlash).trim();
        if (taskStart.isEmpty()) {
            throw new DougException(DASHED_LINE + "Forgot to mention when " + taskName
                    + " starts, huh bud?\n" + DASHED_LINE);
        }

        command = command.replace(taskStart, "");
        String taskEnd = command.replace("/to", "").trim();
        if (taskEnd.isEmpty()) {
            throw new DougException(DASHED_LINE + "Forgot to mention when " + taskName
                    + " ends, huh bud?\n" + DASHED_LINE);
        }

        Event eventTask = new Event(taskName, taskStart, taskEnd);
        tasks[counter] = eventTask;
        counter++;

        System.out.println(DASHED_LINE + "I've added: " + eventTask + " for you.");
        System.out.println("Your list is now " + counter + " tasks long partner\n" + DASHED_LINE);
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

    public static void readInputs(String command) throws DougException{

        if (command.equals("bye")) {
            saidBye = true;
        } else if (command.equals("list")){
            doList();
        } else if (command.startsWith("mark")) {
            doMark(command);
        } else if (command.startsWith("unmark")) {
            doUnmark(command);
        } else if (command.startsWith("todo")) {
            doToDo(command);
        } else if (command.startsWith("deadline")) {
            doDeadline(command);
        } else if (command.startsWith("event")) {
            doEvent(command);
        } else {
            throw new DougException(DASHED_LINE + "Something seems off partner...\n" + DASHED_LINE);
        }
    }


    public static void main(String[] args) {

        //sayWelcome();
        Scanner input = new Scanner(System.in);

        while (!saidBye) {
            try {
                String command = input.nextLine().trim();
                readInputs(command);
            } catch (DougException e) {
                System.out.println(e.getMessage());
            }
        }

        // Closing Statement
        System.out.println(DASHED_LINE + "It's been a pleasure partner.\n" +
                "Hope to see you around these parts again soon!\n" + DASHED_LINE);

    }
}
