package task;

public class UserManual {

    public static void printUserManual() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                USER MANUAL              ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Commands Available");
        System.out.println("1. [todo]: 'todo <tasks>'");
        System.out.println("2. [deadline]: 'deadline <tasks> /by <date/time>'");
        System.out.println("3. [event]: 'event <tasks> /from <time> /to <time>'");
        System.out.println("4. [list all tasks]: 'list'");
        System.out.println("5. [mark task as done]: 'mark <task number>'");
        System.out.println("6. [unmark a task as not done]: 'unmark <task number>'");
        System.out.println("7. [delete task]: 'delete <task number>'");
        System.out.println("8. [exit program: 'bye'");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
