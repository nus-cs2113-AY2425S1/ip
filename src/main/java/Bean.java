public class Bean {
    final static String line = "\n____________________________________________________________________\n";

    public static void greet() {
        String logo = "  ┏━┓\n" +
                "  ┃ ┃\n" +
                "  ┃ ┗━━┳━━━┳━━━━┳━━━┓\n" +
                "  ┃  ┏┓┃ ┃━┫ ┏┓ ┃ ┏┓ ┓ ┏━━━━━━┓\n" +
                "  ┃  ┗┛┃ ┃━┫ ┏┓ ┃ ┃┃ ┃ ┃• ᴗ • ┫\n" +
                "  ┗━━ ━┻━━━┻━┛┗━┻━┛┗━┛ ┗━━━━━━┛\n";

        System.out.println(line +
                logo +
                "  Howdy, mate! :) I am Bean, your personal assistant.\n  Let me know how I can help you out!\n" +
                line
        );
    }

    public static void exit() {
        System.out.println("  Bye bye, glad I could help! See you soon? :'(\n" +
                line);
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
