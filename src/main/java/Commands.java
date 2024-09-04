

public class Commands {

    private static final int MAX_LENGTH = 100;
    private String[] lines = new String[MAX_LENGTH];

    public int length = 0;

    private boolean isExited = false;

    public Commands(String[] lines, int length) {
        this.lines = lines;
        this.length = length;
    }
    public Commands() {

    }

    public void accept(String nextLine) {
        if (nextLine.equals("exit")){
            this.isExited = true;
        } else if (nextLine.equals("list")) {
            this.list();
        } else {
            this.lines[length] = nextLine;
            this.echo();
            length++;
        }

    }

    private void list() {
        for (int i = 0; i < length; i++) {
            System.out.println( i +": "+ lines[i]);
        }
    }

    private void echo() {
        System.out.println("added: " + lines[length]);
    }

    public boolean isAcceptingCommands () {
        return !isExited;
    }
}
