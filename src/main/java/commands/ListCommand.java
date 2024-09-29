package commands;



public class ListCommand extends Command {
    boolean isExit;
    public ListCommand() {
        this.isExit = false;
    }

    @Override
    public String execute() {
        StringBuilder string = new StringBuilder("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            string.append("\t\t").append(i + 1).append(".").append(tasks.get(i));
        }
        return string.toString();
    }

}
