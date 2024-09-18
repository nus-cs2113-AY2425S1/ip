import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Im Shrek");
        printShrekFace2();
        System.out.println("what are you doing in my swamp!??");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in); //Scanner object named "scanner"
        ArrayList<Task> tasks = new ArrayList<>(); //Array of "task" object to store the tasks
        //   int taskCount = 0; //Counter for the number of tasks
        String input;

        while (true) {
            input = scanner.nextLine();

            try
            {
                if (input.equals("bye")) //end loop when user inputs "bye"
                {
                    break;
                }
                else if (input.equals("list")) //display tasks when user inputs "list"
                {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here is your list of nonsense, now get out of my swamp:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("     " + (i + 1) + "." + tasks.get(i)); //print each line in proper format
                    }
                    System.out.println("____________________________________________________________");
                }
                else if (input.startsWith("mark")) //mark task and print acknowledge message when user marks a task
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text. minus 1 to get correct index in the "tasks" array
                    tasks.get(taskNumber).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("It’s done. Now, get lost before I toss you out of my swamp!");
                    System.out.println("     [" + tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription());
                    System.out.println("____________________________________________________________");
                }
                else if (input.startsWith("unmark")) //unmark task and print acknowledge message when user unmarks a task
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text
                    tasks.get(taskNumber).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("It’s unmarked. Gonna change it again? Make up your mind, or get out of my swamp!");
                    System.out.println("     [" + tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription());
                    System.out.println("____________________________________________________________");
                }
                else if (input.startsWith("todo"))
                {
                    String description = input.substring(5); //at index 5 of the string, the description starts
                    tasks.add(new Todo(description)); //put new task todo object in task array
                    printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
                }
                else if (input.startsWith("deadline"))
                {
                    String[] parts = input.substring(9).split(" /by ");
                    tasks.add(new Deadline(parts[0], parts[1]));
                    printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
                }
                else if (input.startsWith("event"))
                {
                    String[] description = input.substring(6).split(" /from ");
                    String[] time = description[1].split(" /to ");
                    tasks.add(new Event(description[0], time[0], time[1]));
                    printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
                }
                else if(input.startsWith("delete"))
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text. minus 1 to get correct index in the "tasks" array
                    Task taskToRemove = tasks.get(taskNumber);
                    System.out.println("____________________________________________________________");
                    System.out.println("fine ill remove this task donkey:");
                    System.out.println("   " + taskToRemove);
                    System.out.println("____________________________________________________________");
                    tasks.remove(taskNumber);
                }
                else //store whatever user input in "tasks" array
                {
                    tasks.add(new Task(input));
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + input);
                    System.out.println("____________________________________________________________");
                }
            }
            catch (NumberFormatException e){ //catch when trying to convert string to number. but i think wont happen.
                System.out.println("wrong format, enter a valid task");
            }
            catch (ArrayIndexOutOfBoundsException e){ //
                System.out.println("what are you typing donkey? specify the time/task right or get out of my swamp");
            }
            catch (NullPointerException e){ //when access nullptr, eg when marking task 6 but only got 5 task
                System.out.println("cant mark whats not in my swamp. pick an existing task donkey");
            }
            catch (StringIndexOutOfBoundsException e){ //access index in string outside valid range. happen for T/D/E commands
                System.out.println("you didnt finish your sentence donkey! specify the time/task right or get out of my swamp");
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("that task is not even in my swamp donkey! Try another task. ");
            }
        }

        System.out.println("____________________________________________________________"); //first line after "bye"
        System.out.println(" Finally, you’re leaving. Now I can have some peace in my swamp.");
        printShrekFace();
        System.out.println("____________________________________________________________"); //second line after "bye"

    }

    public static void printAddedTask(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" ive added this task to your pile of nonsense:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in my swamp.");
        System.out.println("____________________________________________________________");
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

