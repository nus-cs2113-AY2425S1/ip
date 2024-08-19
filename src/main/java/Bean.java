public class Bean {
    final static String line = "____________________________________________________________________\n";

    public static void greet() {
        String logo = "  ┏━┓\n" +
                "  ┃ ┃\n" +
                "  ┃ ┗━━┳━━━┳━━━━┳━━━┓\n" +
                "  ┃  ┏┓┃ ┃━┫ ┏┓ ┃ ┏┓ ┓ ┏━━━━━━┓\n" +
                "  ┃  ┗┛┃ ┃━┫ ┏┓ ┃ ┃┃ ┃ ┃• ᴗ • ┫\n" +
                "  ┗━━ ━┻━━━┻━┛┗━┻━┛┗━┛ ┗━━━━━━┛\n";

        System.out.println(line +
                logo +
                "Howdy, mate! :) I am Bean, your personal assistant.\nLet me know how I can help you out!\n" +
                line
        );
    }

    public static void exit() {
        System.out.println(line +
                " Bye bye, see you again... :(\n" +
                line);
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
