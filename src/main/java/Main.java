public class Main extends Fenix{
    public static void main(String[] args) {
        Fenix fenix = new Fenix();
        FileHandler fileHandler = new FileHandler();
        Storage storage = new Storage(fileHandler, fenix.parser, fenix.taskHandler);
        storage.loadAllInfo();
        fenix.run();
        storage.writeAllTasks();
    }
}
