public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        MainMenu menu = new MainMenu();

        Thread t1 = new Thread(menu);
        t1.start();

    }
}