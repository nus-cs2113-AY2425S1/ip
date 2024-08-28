import java.util.Scanner;

public class JeM {
    public static void main(String[] args) {
        String logo = "      _         __  __ \n"
                + "     | |       |  \\/  |\n"
                + "     | |  ___  | |\\/| |\n"
                + " _   | | / _ \\ | |  | |\n"
                + "| |__| ||  __/ | |  | |\n"
                + " \\____/  \\___| |_|  |_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");

        System.out.println(" Hello! I'm JeM, Your e-Assistant");
        System.out.println(" Personal To-Do list bot! ");
        System.out.println(" Just type out your tasks you have to complete and i will make a list of them for you");
        System.out.println(" Type 'List' to see the current list of tasks, and type 'Delete <task number>' to delete that task");
        System.out.println(" Finally, type bye to end the chat!");

        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        storage storage = new storage();

        while(true){
            String line = scanner.nextLine();
            Task task = new Task(line);

            if (line.equals("Bye") || line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("List") || line.equals("list")) {
                storage.storageList();
            } else if (line.contains("delete") || line.contains("Delete")){
                String[] lineParts = line.split(" ");
                int index = Integer.parseInt(lineParts[1]);
                storage.storageDelete(index);
                storage.storageList();
            } else if (line.contains("unmark") || line.contains("Unmark")){
                String[] lineParts = line.split(" ");
                int index = Integer.parseInt(lineParts[1]);
                storage.storageUnmark(index);
                storage.storageList();
            } else if (line.contains("mark") || line.contains("Mark")){
                String[] lineParts = line.split(" ");
                int index = Integer.parseInt(lineParts[1]);
                storage.storageMark(index);
                storage.storageList();
            } else {
                System.out.println("added: " + line);
                storage.storageInsert(task);
            }

        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
