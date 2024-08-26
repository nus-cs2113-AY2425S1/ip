import java.util.Scanner;

public class Buddy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hi Daddy, Im Shrek");
        printShrekFace2();
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in); //Scanner object named "scanner"
        Task[] tasks = new Task[100]; //Array of "task" object to store the tasks
        int taskCount = 0; //Counter for the number of tasks
        String input;

        while (true)
        {
            input = scanner.nextLine();

            if (input.equals("bye")) //end loop when user inputs "bye"
            {
                break;
            }
            else if (input.equals("list")) //display tasks when user inputs "list"
            {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are your tasks Daddy:");
                for (int i = 0; i < taskCount; i++)
                {
                    //print each line in proper format
                    System.out.println("     " + (i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________");
            }
            else if (input.startsWith("mark")) //mark task and print acknowledge message when user marks a task
            {
                int taskNumber = Integer.parseInt(input.split(" ")[1])-1; //get the integer in the text. minus 1 to get correct index in the "tasks" array
                tasks[taskNumber].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("I have marked this task as done daddy:");
                System.out.println("     [" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].getDescription());
                System.out.println("____________________________________________________________");
            }
            else if (input.startsWith("unmark")) //unmark task and print acknowledge message when user unmarks a task
            {
                int taskNumber = Integer.parseInt(input.split(" ")[1])-1; //get the integer in the text
                tasks[taskNumber].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("I have marked this task as not done daddy:");
                System.out.println("     [" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].getDescription());
                System.out.println("____________________________________________________________");
            }
            else //store whatever user input in "tasks" array
            {
                tasks[taskCount] = new Task(input);
                taskCount ++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________"); //first line after "bye"
        System.out.println(" Bye Daddy. I will miss you (>ᴗ•) !");
        printShrekFace();
        System.out.println("____________________________________________________________"); //second line after "bye"

    }

    public static void printShrekFace() {
        System.out.println("⢀⡴⠑⡄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠸⡇⠀⠿⡀⠀⠀⠀⣀⡴⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⠑⢄⣠⠾⠁⣀⣄⡈⠙⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⢀⡀⠁⠀⠀⠈⠙⠛⠂⠈⣿⣿⣿⣿⣿⠿⡿⢿⣆⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⢀⡾⣁⣀⠀⠴⠂⠙⣗⡀⠀⢻⣿⣿⠭⢤⣴⣦⣤⣹⠀⠀⠀⢀⢴⣶⣆ ");
        System.out.println("⠀⠀⢀⣾⣿⣿⣿⣷⣮⣽⣾⣿⣥⣴⣿⣿⡿⢂⠔⢚⡿⢿⣿⣦⣴⣾⠁⠸⣼⡿ ");
        System.out.println("⠀⢀⡞⠁⠙⠻⠿⠟⠉⠀⠛⢹⣿⣿⣿⣿⣿⣌⢤⣼⣿⣾⣿⡟⠉⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⣾⣷⣶⠇⠀⠀⣤⣄⣀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠉⠲⣽⡻⢿⣿⣿⣿⣿⣿⣿⣷⣜⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣶⣮⣭⣽⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀ ");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⠿⠿⠿⠿⠛⠉");
    }

    public static void printShrekBody() {
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡴⠖⠋⠉⠀⠀⠉⠒⠶⣤⡀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣷⣤⡀⠀⠀⢀⣼⣿⣷⣤⣀⠀⠀⠀⠀⠀⣀⣼⣿⡂⠀⠀⠀⣴⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⠿⢿⣿⣆⣀⣼⣟⣯⣩⣹⣿⣷⣀⠀⣰⣿⣟⣋⡩⢿⡦⣴⣾⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⡿⣿⡟⢿⣿⣿⠆⣿⣿⣿⣟⢻⣷⡏⢿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠟⠛⠿⠿⢟⢚⠋⠀⠀⠈⠛⠿⠿⠾⠗⠈⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣿⡏⢀⠀⢀⣾⣿⣾⣧⡀⠀⠀⣀⣄⣦⡀⠀⠀⢸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⢿⣿⣿⡛⠛⠛⠛⠛⠛⠛⠉⠉⠉⠻⢷⣢⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣄⣾⢏⣿⠿⣧⣤⣀⠀⠐⠀⣀⣀⣠⡤⠄⠙⢺⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⡿⣍⢻⡜⡼⣫⢝⡲⠯⠽⠭⠭⠭⠁⠀⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⡹⣮⡓⢮⡕⡯⢮⣱⠂⡄⢀⠀⠀⠀⠀⠀⠀⠀⡰⠀⠈⠒⠠⢄⣀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣧⡳⣝⡿⣎⡶⡙⣆⠁⠀⠀⠀⠀⠀⠀⠀⠀⣠⠎⠁⠀⠀⠀⠀⠀⠈⠉⠉⠑⠢⢤⣀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣮⣷⢻⡿⣷⣞⣦⣄⠀⠀⡀⢀⣀⣠⠞⢁⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠢⡀⠀⠀");
        System.out.println("⠀⠀⠀⠴⠚⠙⠉⠛⣿⣿⣿⠿⡉⠍⠉⠛⢿⣷⣿⣽⣿⣿⠿⠿⠛⠛⠉⠉⠁⢠⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢆⠀");
        System.out.println("⠂⡄⠄⠀⠀⠀⠀⠀⠚⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆");
        System.out.println("⡑⢄⠠⠀⠀⠀⢀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈");
        System.out.println("⠌⡂⠠⠀⡐⢠⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠄⠀⠀⠀⠀⠀⠀⠀⠄⢂");
        System.out.println("⡘⡤⣑⠢⣜⠢⣉⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣷⣮⡔⡠⢌⡘⠤⡘⢤⠣");
        System.out.println("⡳⣖⣭⣳⣾⡳⢄⢣⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣀⠂⠄⠀⠀⠀⠀⠀⠀⠀⢀⠀⢄⡒⢌⣣⣿⣿⣿⣽⡷⣍⠚⢤⡙⢆⠃");
        System.out.println("⣿⣿⣾⣿⣿⣿⣏⣦⢣⢎⡐⠠⠀⠠⠀⠀⠀⠀⠀⠀⢀⠸⣿⣿⣯⡲⣄⢂⢄⡐⢠⠰⣈⠶⣘⢦⣹⣮⣿⣿⠋⠀⠀⠀⠀⠐⠂⠑⠂⠀");
        System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⡝⣥⢃⡆⡤⢠⠀⡄⡐⢎⡍⠃⣿⣿⣿⣿⣦⣍⡶⣌⣦⢧⣝⣾⣽⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠉⢿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣷⣽⣶⣧⣿⣴⣽⣶⡶⠀⢿⣿⣿⣿⣿⣿⢿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠤⠀⢀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⢺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠋⠀⠀⠀⠀⠈⠙⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢺⡀⢂⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⢬⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡷⢾⠷⠤⢄⠀⣶⡌⠠⠀⠰⠶⢭⡻⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣷⢂⡁⣦⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⢦⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⡭⠙⠂⠄⠀⠀⠀⢀⠀⣤⠐⠄⠀⠀⠀⠀⢻⡓⠿⢻⠻⣝⢮⣳⢯⣿⣿⣿⣦⣃⢿⡆⠐⠀⠄⡀⠀");
        System.out.println("⠘⣦⣿⡿⠈⢿⣿⣿⣿⣿⣿⣧⡛⢿⣭⡓⣌⣆⣏⠀⠸⠂⢷⡠⠀⠀⠀⣀⢈⡄⡘⠀⠁⠉⣎⠷⣽⡞⣯⣿⢿⣿⣟⣾⣿⣮⣙⠄⠀⠀");
        System.out.println("⣱⢾⣿⠃⠀⠈⢿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⡿⢿⣷⣦⡀⢸⠿⠿⠿⠷⢿⣾⣦⣥⡀⣌⠲⣜⡟⢧⣿⡷⠁⠈⢻⣿⣿⣿⣿⠇⡀⠀⠀");
        System.out.println("⣿⣿⠇⠀⠀⠀⠈⣿⣿⡜⢤⠉⠙⣿⡧⡀⢄⠀⠀⠀⠌⠙⠀⠄⠀⠀⠀⠀⡀⠛⠿⠟⠛⠙⠢⢙⢻⡟⠀⠀⠀⠀⠹⣿⣿⡿⠀⠀⠀⠀");
        System.out.println("⡿⠃⠀⠀⠀⠀⠀⣿⡿⢿⣈⡇⢀⣿⣷⡍⢦⠡⠄⠠⠀⠰⣏⠀⠀⠀⠀⢆⠱⣉⠶⠀⠀⠀⣁⠂⣽⠁⠀⠀⠀⠀⠀⣿⠿⠁⠀⠀⠀⠀");
        System.out.println("⠁⠀⠀⠀⠀⠀⢺⡿⣜⡹⡌⢧⣈⣿⣿⣿⣧⡿⠤⡄⡄⠸⣷⣸⣄⢠⠩⣌⡕⣎⠄⠀⠀⠐⠤⢣⡿⠀⠀⠀⠀⠀⢀⡿⢀⠐⠀⠀⠀⠠");
        System.out.println("⠀⡀⠀⠀⠀⠀⣿⢣⢇⡳⢜⡀⢘⠷⣯⡍⢩⠙⠋⢠⣿⣷⠉⠛⠻⠎⡟⡴⣫⠜⠀⠀⢀⡑⣎⣷⡇⠀⠀⠀⠀⢀⣼⠁⢂⠐⢀⠈⠄⡙");
        System.out.println("⡐⠄⡀⠀⠀⠀⣿⣯⢜⣱⢎⠴⣩⢞⡵⣿⡒⣆⡀⠀⠉⠙⡀⢀⢀⠰⣍⢳⡝⠎⠀⠀⠠⣶⢿⣿⠀⠀⠀⠀⠀⠀⣿⡆⠂⠌⠀⠈⠀⠀");
        System.out.println("⡘⠤⠐⠀⠀⠸⣿⣿⣾⢿⡛⢯⡱⢿⣾⡽⣗⢦⡹⣐⠠⡑⡘⢌⡎⡱⢎⠥⠒⡀⠀⣤⣹⣷⣿⡇⠀⠀⠀⠀⠀⠀⣿⡇⡈⠔⠠⢂⠁⣚");
        System.out.println("⣡⢃⠌⡀⢠⣰⣿⣿⣿⣎⡷⢢⠝⣯⢿⣽⣿⢮⡵⢪⡱⣥⡙⢦⡚⢵⢫⡼⢁⠠⣱⣾⣿⣿⡟⠀⠀⠀⠀⠀⠀⠸⣷⣿⠹⣮⣑⠠⢎⡴");
        System.out.println("⢦⡉⢆⠐⢣⣿⣯⢿⣿⣿⡹⣏⢜⡳⣿⢾⣿⣧⣟⣧⢳⡖⣽⢣⣞⠯⡖⢥⢂⢧⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⢿⣿⡇⡘⠍⡳⣦⣟");
        System.out.println("⡇⠞⣬⣊⣿⣿⡿⢸⣿⣿⣧⡍⣎⣿⢻⣯⣿⢮⢷⣞⡷⣹⣞⣧⡟⣽⡙⣮⣹⣿⣿⠟⣹⡿⣿⣷⡄⠀⠀⠀⠀⠀⢸⣿⡇⢌⠹⣔⠳⣾");
        System.out.println("⣭⣋⢿⣿⣿⣿⣷⣁⠺⣿⣿⣯⢽⣾⣿⣿⣽⣫⢷⣏⣿⢳⣻⣼⣹⢶⡹⣶⣿⣿⢏⣺⣵⣿⣿⣿⣻⠀⠀⠀⠀⠀⢠⣟⣧⠀⡳⣌⡿⣿");
        System.out.println("⡖⡭⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣿⣿⣯⣟⣿⣾⣽⣯⡷⣏⣷⣯⣿⣷⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⢸⡿⠸⡗⣵⢯⣿⣿");
        System.out.println("⡽⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⣸⢇⢣⡹⣞⣿⣿⡏");
        System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠂⠤⣴⣿⢼⠶⠽⣿⣿⡿⠀");
    }

    public static void printShrekFace2() {
        System.out.println("⠀⠀⠀⠀⠀⣀⡤⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⡔⠋⠀⠀⠀⢠⡑⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⡜⠀⠀⠀⠀⠀⢸⡇⠈⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⢸⠀⠀⠀⠀⠀⠀⣨⡇⠀⠸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠤⠴⠒⠋⠉⠁⠈⠉⠓⠒⠤⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⢸⡀⠀⠀⠀⠀⣼⣿⡟⠀⠀⢡⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠢⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠹⠿⢿⣿⣭⡉⠭⠀⠀⠀⠀⠱⡄⠀⠀⠀⠀⠀⢀⠎⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠙⠦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠈⠉⠙⠓⢶⡀⠀⠀⠀⠘⢦⡀⠀⠀⡰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⣀⣠⢄⢍⠒⠤⠀⠀⠀⠀⠀⠀⠈⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⢦⠄⠀⠀⠙⠢⠜⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣼⣿⠾⠿⢿⣿⣿⣶⡀⠀⠐⠀⡀⠀⠀⠀⠙⣄⠀⠀⢀⢴⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢣⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡾⠛⠋⠉⠁⠀⠀⠈⠙⢿⣿⣿⣷⣜⡀⠀⠀⠀⠀⠀⢨⠣⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⢿⣯⣝⡄⠀⠒⠀⠀⠀⠀⠙⢄⠀⠀⢀⢴⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣀⡀⠀⠈⢻⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠱⡴⠉⡼⢸⠀⠘⠆⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣾⡿⠿⢿⣿⢷⣄⠀⠀⠈⠋⠀⠀⠀⠀⠀⣠⣦⡴⣤⣜⣄⡇⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡏⢼⣿⣿⣿⣞⣿⠈⣿⠀⠀⠀⠀⠀⠀⠀⠀⠠⡟⠛⠻⣿⣿⣿⠷⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠠⣑⣾⣿⣿⣯⡾⠏⣠⡟⠀⠀⠀⠀⠀⠀⢀⣴⢋⣁⡤⣭⣵⣌⣹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⠿⢿⣿⣷⡶⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⢷⣿⣿⣳⡟⢈⡿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⠯⣷⡿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡞⢀⣶⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠱⠸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⠁⣘⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢡⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⣶⣶⣶⣾⣿⣿⣿⡿⡿⠋⢀⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⣿⣿⣿⣿⣿⡿⠋⡾⠁⠀⡞⠰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⠿⢿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⢠⠎⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⣿⣿⣿⣿⣿⡇⣠⣿⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠷⣦⣤⣤⣤⣤⢴⠏⠀⢸⠀⠀⠀⠀⠀⠀⢀⣀⣠⠤⠤");
        System.out.println("⣿⣿⢿⣿⣿⠀⣾⣟⠀⢐⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠤⠔⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠀⢠⣮⠖⠒⠚⠉⠉⠉⠁⠀⠀⠀⠀");
        System.out.println("⣿⣿⣿⣿⣿⡋⣼⣯⠀⠈⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡘⡆⠀⠁⠀⠀⠀⠀⠀⠀⠐⠀");
        System.out.println("⣾⣿⣿⣿⡏⠀⢀⣻⡆⠀⢹⡂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣇⣧⠀⢀⠀⠈⠀⠀⠐⠂⠀⠀");
        System.out.println("⣻⣿⣿⣿⣿⠀⡈⠸⣷⠀⠘⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠳⢶⣤⣤⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠋⢿⣿⣿⢸⠞⠽⠦⠠⢆⠀⢒⠀⠀⠀");
        System.out.println("⣿⣿⣿⣿⣿⡇⠐⠐⢿⣧⠀⠘⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠛⠛⠿⠷⠶⣴⣄⣀⣀⣀⣠⡤⠤⠤⠶⠞⠁⣠⣼⣿⣿⣿⣁⢁⣀⡌⠀⣐⡐⠀⣀⠀");
        System.out.println("⣿⣿⣿⣿⣿⣷⢀⠀⠈⣼⣧⡀⠸⢷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣾⡙⣇⣆⣴⣤⣒⣆⠤⡠");
        System.out.println("⣿⣿⣿⣿⣿⣿⡐⠀⢄⠐⢿⣿⡀⠈⢻⡈⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢿⣿⣿⣿⣿⣿⣯⣿⣷⣆⣛⣟⣿⣠⠈⢈");
        System.out.println("⣿⣿⣿⣿⣿⣿⣷⠈⢆⠆⠠⢿⢱⣦⠀⠻⣄⠠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣏⣸⣿⣿⣿⣿⣿⣿⣷⣽⣿⣿⣿⣿⣓⡶⢶");
        System.out.println("⣿⣿⣿⡿⣿⣿⣿⣧⠈⢄⠀⠀⠀⡹⣿⣀⠈⠳⣼⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢎⣁⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣃⡘");
        System.out.println("⣿⡏⠘⣆⣿⣿⣿⣿⡎⠀⠀⡀⢀⠀⠀⠛⣦⠀⠈⠛⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠋⡌⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣌");
        System.out.println("⣿⠀⠘⣿⣿⣞⣿⣿⣿⡄⠀⠁⠀⠀⡐⡐⡹⡄⠀⠀⠀⠙⢦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⠘⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯");
        System.out.println("⣿⣿⣷⣿⣿⣿⣿⣿⢿⣿⣦⡀⠀⠀⠇⠀⠢⠛⢶⠷⣆⡀⠀⠈⠛⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⢔⡍⢐⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣻");
        System.out.println("⣿⣿⣿⣿⣹⣿⣿⣷⣾⣿⢿⣷⡄⠈⠠⠀⠀⠀⠀⠀⠀⠉⠳⠿⠶⠶⡿⣷⣤⣀⣀⣀⣀⡀⢀⣀⣀⠀⠤⠔⠒⠉⠁⡠⠳⣥⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⣭⣿⣿⣿⣿⣿⡟⣿⣿⣿⣾⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠤⠸⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("⣿⣿⣿⣿⢿⣿⣷⣿⣿⣿⣿⣽⡟⣿⣷⣬⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");

    }

}

