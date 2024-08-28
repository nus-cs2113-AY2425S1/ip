import java.util.Scanner;

public class Blossom {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        String logo =
                """
                         _______     .---.       ,-----.       .-'''-.    .-'''-.     ,-----.    ,---.    ,---.\s
                        \\  ____  \\   | ,_|     .'  .-,  '.    / _     \\  / _     \\  .'  .-,  '.  |    \\  /    |\s
                        | |    \\ | ,-./  )    / ,-.|  \\ _ \\  (`' )/`--' (`' )/`--' / ,-.|  \\ _ \\ |  ,  \\/  ,  |\s
                        | |____/ / \\  '_ '`) ;  \\  '_ /  | :(_ o _).   (_ o _).   ;  \\  '_ /  | :|  |\\_   /|  |\s
                        |   _ _ '.  > (_)  ) |  _`,/ \\ _/  | (_,_). '.  (_,_). '. |  _`,/ \\ _/  ||  _( )_/ |  |\s
                        |  ( ' )  \\(  .  .-' : (  '\\_/ \\   ;.---.  \\  :.---.  \\  :: (  '\\_/ \\   ;| (_ o _) |  |\s
                        | (_{;}_) | `-'`-'|___\\ `"/  \\  ) / \\    `-'  |\\    `-'  | \\ `"/  \\  ) / |  (_,_)  |  |\s
                        |  (_,_)  /  |        \\'. \\_/``".'   \\       /  \\       /   '. \\_/``".'  |  |      |  |\s
                        /_______.'   `--------`  '-----'      `-...-'    `-...-'      '-----'    '--'      '--'""";



        Scanner input = new Scanner(System.in);
        System.out.println(logo + "\n" +"Hello, I'm Blossom! ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String line = input.nextLine();
        if(!line.equalsIgnoreCase("bye")) {
                while(input.hasNext()) {
                    System.out.println(horizontalLine);
                    System.out.println(line);
                    System.out.println(horizontalLine);
                }
        } else {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontalLine);
            input.close(); 
        }
    }
}
