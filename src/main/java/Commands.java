

public class Commands {

    private static final int MAX_LENGTH = 100;
    private Task[] tasks = new Task[MAX_LENGTH];

    public int length = 0;

    private boolean isExited = false;

    public Commands() {

    }

    public void accept(String nextLine) {

        int index = (nextLine.indexOf(' '));
        String commandKeyword = (index == -1)? nextLine : nextLine.substring(0, index).trim();

        final String commandText = (index == -1)? nextLine : nextLine.substring(index).trim();
        switch (commandKeyword) {
        case "exit" -> this.isExited = true;
        case "list" -> this.list();
        case "add" -> {
            this.tasks[length] = new Task(nextLine.substring(index));
            this.echo();
            length++;
        }
        case "mark" -> {
            this.tasks[Integer.parseInt(commandText)].setDone(true);
            echo(Integer.parseInt(commandText));
        }
        case "unmark" -> {
            this.tasks[Integer.parseInt(commandText)].setDone(false);
            echo(Integer.parseInt(commandText));
        }
        }

    }


    private void list() {
        for (int i = 0; i < length; i++) {
            System.out.println( i +": "+ tasks[i]);
        }
    }

    private void echo() {
        System.out.println("added: " + tasks[length]);
    }
    private void echo( int index) {
        System.out.println(tasks[index]);
    }

    public boolean isAcceptingCommands () {
        return !isExited;
    }

}
