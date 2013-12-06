/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 12/4/13
 * Time: 8:11 AM
 * To change this template use File | Settings | File Templates.
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

public class Server extends JFrame{

    private int num, result;
    private Setup setup;
    private Game game;

    public Server(){
        try (ServerSocket server = new ServerSocket(8001);) {
            Random gen = new Random();
            num = gen.nextInt(200);

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    createAndShowGUI();
                }
            });

            server.setSoTimeout(1000 * 20); //seconds gives up
            System.out.println("Waiting for connection");

            while (true) {
                try (Socket client = server.accept();) {
                    System.out.println("Got example2 connection!");
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter print = new PrintWriter(client.getOutputStream(), true);


                    String response;
                    //    (inputLine = in.readLine()) != null)
                    while((response = reader.readLine()) != null){
                        System.out.println(response);
                        print.println(num);
                        result = Integer.parseInt(reader.readLine());
                        System.out.println(result);
                        setup.setResultLabel(result);
                        print.println(result);
                        break;
                    }

                    print.println(result);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createAndShowGUI(){
        JFrame frame = new JFrame("Frame1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setup = new Setup(num);
        game = setup.getGame();

        //Add content to the window
        frame.add(setup, BorderLayout.CENTER);

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }

    public Model getSetupModel(){
        return setup.getModel();
    }
}