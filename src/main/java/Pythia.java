public class Pythia {
    public static String botName = "Pythia";

    public static void printAnswer(String text) {
        String lineSeparator = "-------------------------------";
        System.out.print(lineSeparator + "\n" + text);
    }
    
    public static void main(String[] args) {
        String helloMsg =   "Welcome, seeker. I am " + botName + ".\n" +
                            "What brings you here?\n";
        printAnswer(helloMsg);
        String byeMsg = "Your path is set. Until we meet again, may your tasks be light.\n";
        printAnswer(byeMsg);
    }
}
