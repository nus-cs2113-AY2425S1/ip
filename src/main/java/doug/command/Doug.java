package doug.command;

import java.util.Scanner;
import java.util.ArrayList;
import doug.tasks.*;
import  java.io.File;
import  java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Doug {

    // Declaring Magic Literal
    private static final String DASHED_LINE = "______________________________________________\n";

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int counter = 0;
    private static boolean saidBye = false;

    public static void checkOutOfBounds(int listIndex) throws DougException {
        if (listIndex > counter || listIndex <= 0) {
            throw new DougException(DASHED_LINE + "No can do bud, your input is invalid!\n" + DASHED_LINE);
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
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        System.out.print(DASHED_LINE);
    }

    public static void doMark(String command) throws DougException {
        command = command.replace("mark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = Integer.parseInt(command);

        try {
            checkOutOfBounds(listIndex);
            tasks.get(listIndex - 1).markAsDone();
            try {
                saveTasks();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as done");
            System.out.print(tasks.get(listIndex - 1).toString() + "\n" + DASHED_LINE);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void doUnmark(String command) throws DougException {
        command = command.replace("unmark", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = Integer.parseInt(command);

        try {
            checkOutOfBounds(listIndex);
            tasks.get(listIndex - 1).markAsNotDone();
            try {
                saveTasks();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(DASHED_LINE + "Sure thing partner, I'll mark it as not done");
            System.out.print(tasks.get(listIndex - 1).toString() + "\n" + DASHED_LINE);
        } catch (DougException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void doDelete(String command) throws DougException{
        if (counter <= 0) {
            System.out.println(DASHED_LINE + "Your list is empty pal, ain't nothing to rid off.\n" + DASHED_LINE);
            return;
        }

        command = command.replace("delete", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }
        int listIndex = Integer.parseInt(command);

        try {
            checkOutOfBounds(listIndex);

            Task removedTask = tasks.get(listIndex - 1);
            tasks.remove(listIndex - 1);
            counter--;

            saveTasks();

            System.out.println(DASHED_LINE + "I've deleted: " + removedTask + " for you.");
            System.out.println("Your list is now " + counter + " tasks long partner\n" + DASHED_LINE);
        } catch (DougException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void doToDo(String command) throws DougException{
        command = command.replace("todo", "").trim();
        if (command.isEmpty()) {
            throw new DougException(DASHED_LINE + "It ain't clear to me what you wanna do pal...\n" + DASHED_LINE);
        }

        Todo todoTask = new Todo(command);
        tasks.add(todoTask);
        counter++;

        try {
            saveTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "I've added: " + todoTask + " for you.");
        System.out.println("Your list is now " + counter + " tasks long partner\n" + DASHED_LINE);
    }

    public static void doDeadline(String command) throws DougException {
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
        tasks.add(deadlineTask);
        counter++;

        try {
            saveTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "I've added: " + deadlineTask + " for you.");
        System.out.println("Your list is now " + counter + " tasks long partner\n" + DASHED_LINE);
    }

    public static void doEvent(String command) throws DougException {
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
        tasks.add(eventTask);
        counter++;

        try {
            saveTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DASHED_LINE + "I've added: " + eventTask + " for you.");
        System.out.println("Your list is now " + counter + " tasks long partner\n" + DASHED_LINE);
    }

    public static void sayWelcome() {
        String hat = "                                    %,                         ,#.\n";
        for (int i = 0; i < 51; i++) {
            System.out.println(hat);
        }
        String face = """
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

        System.out.println(face);


        // Welcome statement
        System.out.println(DASHED_LINE + "They call me Doug Dimmadome in these parts.\n" + DASHED_LINE);
    }


    public static void saveTasks() throws IOException {
        FileWriter writer = new FileWriter("./data/tasks.txt");
        for (int i = 0; i < counter; i++) {
            writer.write(tasks.get(i).saveString() + System.lineSeparator());
        }
        writer.close();
    }


    public static void loadToDo(String line) {
        boolean isMarked = false;
        if (line.contains("| X |")) {
            isMarked = true;
            line = line.replaceFirst("T \\| X \\|", "").trim();
        } else {
            line = line.replaceFirst("T \\|   \\|", "").trim();
        }

        Todo todoTask = new Todo(line);
        tasks.add(todoTask);
        counter++;

        if (isMarked) {
            todoTask.markAsDone();
        }
    }

    public static void loadDeadline(String line) {
        boolean isMarked = false;
        if (line.contains("| X |")) {
            isMarked = true;
            line = line.replaceFirst("D \\| X \\|", "").trim();
        } else {
            line = line.replaceFirst("D \\|   \\|", "").trim();
        }

        int indexOfFirstBoundary = line.indexOf("| ");
        String taskName = line.substring(0, indexOfFirstBoundary).trim();

        line = line.replaceFirst(taskName, "");
        String taskDeadline = line.replaceFirst("\\| ", "").trim();

        Deadline deadlineTask = new Deadline(taskName, taskDeadline);
        tasks.add(deadlineTask);
        counter++;

        if (isMarked) {
            deadlineTask.markAsDone();
        }
    }

    public static void loadEvent(String line) {
        boolean isMarked = false;
        if (line.contains("| X |")) {
            isMarked = true;
            line = line.replaceFirst("E \\| X \\|", "").trim();
        } else {
            line = line.replaceFirst("E \\|   \\|", "").trim();
        }

        int indexOfFirstBoundary = line.indexOf("| ");
        String taskName = line.substring(0, indexOfFirstBoundary).trim();

        line = line.replaceFirst(taskName, "");
        line = line.replaceFirst("\\| ", "").trim();
        int indexOfSecondBoundary = line.indexOf("| ");
        String taskFrom = line.substring(0, indexOfSecondBoundary);

        line = line.replaceFirst(taskFrom, "");
        String taskTo = line.replaceFirst("\\| ", "").trim();

        Event eventTask = new Event(taskName, taskFrom, taskTo);
        tasks.add(eventTask);
        counter++;

        if (isMarked) {
            eventTask.markAsDone();
        }
    }


    public static void loadTasks() throws FileNotFoundException{
        File tasksFile = new File("./data/tasks.txt"); 	// create a File for the given file path
        Scanner reader = new Scanner(tasksFile); 	// create a Scanner using the File as the source
        boolean isEmpty = true;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            isEmpty = false;
            if(line.startsWith("T")) {
                loadToDo(line);
            } else if (line.startsWith("D")) {
                loadDeadline(line);
            } else if (line.startsWith("E")) {
                loadEvent(line);
            }
        }
        if (isEmpty) {
            System.out.println("YEEHAW PARTNER, I see this is your first rodeo.\n" + "Now what can I do for ya?\n"
                    + DASHED_LINE);
            return;
        }
        System.out.println("Welcome back partner, YEEHAW!\n" + "Now what can I do for ya?\n" + DASHED_LINE);
        doList();
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
        } else if (command.startsWith("delete")) {
            doDelete(command);
        } else {
            throw new DougException(DASHED_LINE + "Something seems off partner...\n" + DASHED_LINE);
        }
    }


    public static void main(String[] args) {

        sayWelcome();

        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("YEEHAW PARTNER, I see this is your first rodeo.\n" + "Now what can I do for ya?\n"
                    + DASHED_LINE);
        }

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
