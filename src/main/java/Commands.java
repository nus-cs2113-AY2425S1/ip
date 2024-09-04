package main.java;

public class Commands {

    private static int MAX_LENGTH = 100;
    public String[] lines = new String[MAX_LENGTH];
    public int length = 0;

    private boolean isExited = false;

    public Commands(String[] lines, int length) {
        this.lines = lines;
        this.length = length;
    }

    public Commands() {
    }

    public void add(String nextLine) {
        if (nextLine .equals("exit")){
            this.isExited = true;

        }
        this.lines[length] = nextLine;
        length++;

    }
    public void echo() {
        System.out.println(lines[length-1]);
    }

    public boolean isAcceptingCommands () {
        return !isExited;
    }
}
