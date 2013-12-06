/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 12/4/13
 * Time: 9:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int num;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Server server = new Server();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Client client = new Client();
            }
        }).start();
    }
}
