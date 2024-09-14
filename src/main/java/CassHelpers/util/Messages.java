package CassHelpers.util;

public class Messages {

    protected final static int SEPARATOR_LENGTH = 75;

    public static void drawLine(){
        for(int i=0;i<SEPARATOR_LENGTH;i+=1){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void displayIntroductionArt() {
        System.out.println("   ___                                               _                   ");
        System.out.println("  / __|   __ _     ___     ___    __ _    _ _     __| |     _ _   __ _   ");
        System.out.println(" | (__   / _` |   (_-<    (_-<   / _` |  | ' \\   / _` |    | '_| / _` |  ");
        System.out.println("  \\___|  \\__,_|   /__/_   /__/_  \\__,_|  |_||_|  \\__,_|   _|_|_  \\__,_|  ");
        System.out.println("_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| ");
        System.out.println("`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ");
    }

    public static void displayIntroduction(){
        drawLine();
        displayIntroductionArt();
        drawLine();
        System.out.println(" Hello! I'm Cassandra");
        System.out.println(" What can I do for you?");
        drawLine();
    }
}
