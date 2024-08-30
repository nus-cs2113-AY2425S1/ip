package main.java;

public class Commands {

    private static int MAX_LENGTH = 100;
    public String[] lines = new String[MAX_LENGTH];
    public int length = 0;

    public Commands(String[] lines, int length) {
        this.lines = lines;
        this.length = length;
    }

    public Commands() {
    }

    public void add(String nextLine) {
        this.lines[length] = nextLine;
        length++;

    }
    public void echo() {
        System.out.println(lines[length]);
    }
}
