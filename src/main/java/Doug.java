import java.util.Scanner;

public class Doug {

    private static final String dashedLine = "______________________________________________\n";
    private static int counter = 0;
    private static Task[] tasks = new Task[100];

    public static void doList() {
        if (counter == 0) {
            System.out.println(dashedLine + "Got nothing on your roster bud.");
            System.out.print(dashedLine);
            return;
        }

        System.out.println(dashedLine + "Here, let me lay out your tasks for you.");
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + "." + tasks[i].toString());
        }
        System.out.print(dashedLine);
    }

    public static void doMark(String command) {
        int listIndex = Integer.parseInt(command.substring(5));

        if (listIndex > counter || listIndex <= 0) {
            System.out.println(dashedLine + "No can do bud, your input is invalid!\n" + dashedLine);
            return;
        }

        tasks[listIndex - 1].markAsDone();
        System.out.println(dashedLine + "Sure thing partner, I'll mark it as done");
        System.out.print(tasks[listIndex - 1].toString() + "\n" + dashedLine);
    }

    public static void doUnmark(String command) {
        int listIndex = Integer.parseInt(command.substring(7));

        if (listIndex > counter || listIndex <= 0) {
            System.out.println(dashedLine + "No can do bud, your input is invalid!\n" + dashedLine);
            return;
        }

        tasks[listIndex - 1].markAsNotDone();
        System.out.println(dashedLine + "Sure thing partner, I'll mark it as not done");
        System.out.print(tasks[listIndex - 1].toString() + "\n" + dashedLine);
    }

    public static void doToDo(String command) {
        Todo t = new Todo(command.substring(5));
        tasks[counter] = t;
        counter++;
        System.out.println(dashedLine + "added: " + command + "\n" + dashedLine);
    }

    public static void doDeadline(String command) {
        int indexOfSlash = command.indexOf('/');
        String taskName = command.substring(9, indexOfSlash - 1);
        String taskDeadline = command.substring(indexOfSlash + 4);

        Deadline d = new Deadline(taskName, taskDeadline);
        tasks[counter] = d;
        counter++;
        System.out.println(dashedLine + "added: " + taskName + "\n" + dashedLine);
    }

    public static void doEvent(String command) {
        int indexOfFirstSlash = command.indexOf('/');
        int indexOfSecondSlash = command.indexOf('/', indexOfFirstSlash + 1);
        String taskName = command.substring(6, indexOfFirstSlash - 1);
        String taskStart = command.substring(indexOfFirstSlash + 6, indexOfSecondSlash - 1);
        String taskEnd = command.substring(indexOfSecondSlash + 4);

        Event e = new Event(taskName, taskStart, taskEnd);
        tasks[counter] = e;
        counter++;
        System.out.println(dashedLine + "added: " + taskName + "\n" + dashedLine);
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
                + "                                     .%*.,     .,  ./   .*    .%@@@@#.&@@@@,          (  /      .#.\n";

        System.out.println(face);


        // Welcome statement
        System.out.println(dashedLine + "Howdy partner! My name is Doug Dimmadome.\n"
                + "Now what can I do for ya?\n" + dashedLine);
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
                if (counter >= 100) {
                    System.out.println(dashedLine + "Sorry partner, the list is full!" + dashedLine);
                    continue;
                }
                doToDo(command);
            } else if (command.startsWith("deadline")) {
                if (counter >= 100) {
                    System.out.println(dashedLine + "Sorry partner, the list is full!" + dashedLine);
                    continue;
                }
                doDeadline(command);
            } else if (command.startsWith("event")) {
                if (counter >= 100) {
                    System.out.println(dashedLine + "Sorry partner, the list is full!\n" + dashedLine);
                    continue;
                }
                doEvent(command);
            } else {
                System.out.println(dashedLine + "Something seems off partner...\n" + dashedLine);
            }
        }

        // Closing Statement
        System.out.println(dashedLine + "It's been a pleasure partner.\n" +
                "Hope to see you around these parts again soon!\n" + dashedLine);

    }
}
