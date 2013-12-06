/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 12/4/13
 * Time: 8:11 AM
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class Client extends JFrame{

    private Model model = new Model();
    private Game game;
    private Random gen = new Random();
    private int result;

    public Client(){
        System.out.println("Connecting to server");
        try (Socket connection = new Socket("localhost", 8001);) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    createAndShowGUI();
                }
            });

            System.out.println("Connected");
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter print = new PrintWriter(connection.getOutputStream(), true);

            game = new Game(model);

            print.println("Sup...");
            String fromServer;

            int num = Integer.parseInt(reader.readLine());
            System.out.println(num + " YES!");

            while(game.getActionMade() == false){
                System.out.println(game.getActionMade());
                if(game.getActionMade() == true){
                    System.out.println(game.getActionMade());
                    model.setNumber(num);
                    result = game.getGuess();
                    System.out.println("result: " + result);
                }
            }

            print.println(result);


            while ((fromServer = reader.readLine()) != null){
                System.out.println("waiting");
                int response = Integer.parseInt(reader.readLine());
                System.out.println(response);
                game.setAnswerLabel("Your guess was: " + response);
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAndShowGUI(){
        JFrame frame = new JFrame("Frame2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window
        frame.add(game, BorderLayout.CENTER);

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }
}
