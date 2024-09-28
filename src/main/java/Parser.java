public class Parser {

    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    public static int parseTaskIndex(String input) throws KaiException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new KaiException("Please enter a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KaiException("You must specify a task number.");
        }
    }
}

