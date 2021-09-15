public class Main {
    private static volatile boolean toggle = false;
    public static final int TIME_STOP_GAME = 1000;
    public static final int TIME_STOP_USER = 2000;
    public static final int NUMBER_TOGGLE = 5;

    public static void main(String[] args) {
        Game game = new Game();
        game.setName("game");
        User user = new User();
        user.setName("user");
        user.start();
        game.start();
        try {
            user.join();
        } catch (InterruptedException e) {
            e.getMessage();
        }
        game.interrupt();
    }

    static class Game extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(TIME_STOP_GAME);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
                if (toggle) {
                    toggle = false;
                    System.out.println(Thread.currentThread().getName() + ": тумблер выключен!");
                }
            }
        }
    }

    static class User extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < NUMBER_TOGGLE; i++) {
                try {
                    Thread.sleep(TIME_STOP_USER);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
                toggle = true;
                System.out.println(Thread.currentThread().getName() + ": тумблер включен!");
            }
        }
    }
}
