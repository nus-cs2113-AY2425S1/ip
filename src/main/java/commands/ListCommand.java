package commands;

public class ListCommand extends Command {

    @Override
    public void execute() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0)

    }

    // user input is list: display past tasks
                    for (int i = 0; i < taskCount; i++) {
        System.out.println("\t\t" + (i + 1) + "." + tasks[i].toString());
    }
                    System.out.println("\t____________________________________________________________\n");
                    break;

}
