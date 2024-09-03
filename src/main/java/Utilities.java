import java.util.Hashtable;

public class Utilities {
    public static Hashtable<String, String> getCommandArgument(String line) {
        Hashtable<String, String> args = new Hashtable<>();
        String[] commandParams = (line.substring(line.indexOf(" ") + 1).split("(?=/)|(?<=/)"));
        boolean isArgument = false;
        for (String s : commandParams) {
            s = s.trim();
            if (s.equals("/")) {
                isArgument = true;
            } else if (isArgument) {
                args.put(s.substring(0, s.indexOf(' ')), s.substring(s.indexOf(' ')).trim());
                isArgument = false;
            }
        }
        return args;
    }
}
