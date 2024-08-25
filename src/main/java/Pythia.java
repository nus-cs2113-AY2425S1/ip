public class Pythia {
    public static String botName = "Pythia";
    
    public static void main(String[] args) {
        IO.init();
        IO.greet();

        boolean byeSaid = false;
        while (!byeSaid) {
            String question = IO.getQuestion();
            switch (question) {
                case "bye": IO.sayBye();
                            byeSaid = true;
                            break;
                default: IO.echo(question); break;
            }
        }
    }
}
