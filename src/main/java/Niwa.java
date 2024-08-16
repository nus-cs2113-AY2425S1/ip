public class Niwa {
    public static void main(String[] args) {
        String name = "Niwa";
        String logo = " _   _\n"
                + "| \\ | | (_)  _       _  ___\n"
                + "|  \\| | | | | | __  // //| |\n"
                + "| |\\  | | | | |/  |// //_| |\n"
                + "|_| \\_|_|_| |__/|__/ //  |_|";

        printGreet(name, logo); //Print greeting lines
    }
    public static void printHorizontalLine (int numDash)
    {
        for (int i = 0; i<numDash; i++) {
            System.out.print("_"); //Print "_" numDash times
        }
        System.out.print("\n");
    }
    public static void printGreet(String name, String logo) {
        System.out.println(logo);
        printHorizontalLine(40);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }
}
