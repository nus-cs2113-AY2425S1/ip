public class Quinn {
    public static void main(String[] args) {
        new Quinn().run();
    }

    public void run() {
        greet();
        exit();
    }

    public void greet() {
        String logo = "\t" + "  QQQ   U   U III N   N N   N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  NN  N NN  N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  N N N N N N " + System.lineSeparator()
                + "\t" + " Q   Q  U   U  I  N  NN N  NN " + System.lineSeparator()
                + "\t" + "  QQQ    UUU  III N   N N   N " + System.lineSeparator()
                + "\t" + "    Q                       " + System.lineSeparator()
                + "\t" + "     QQ                     " + System.lineSeparator();

        String welcomeMessage = "\t" + "Hello! I'm Quinn, your Personal Assistant ChatBot."
                + System.lineSeparator()
                + System.lineSeparator()
                + logo
                + System.lineSeparator()
                + "\t" + "What can I do for you?";

        printResponse(welcomeMessage);
    }

    public void exit() {
        String exitMessage = "\t" + "Bye. Hope to see you again soon!";
        printResponse(exitMessage);
    }

    public void printResponse(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void printLine() {
        String horizontalLine = "\t" + "________________________________________________________";
        System.out.println(horizontalLine);
    }
}
