import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 11/17/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends JPanel implements PropertyChangeListener, ActionListener{
    private PropertyChangeSupport changer = new PropertyChangeSupport(this);
    private Model model;
    private JTextField textFieldSetup, textFieldGame;
    private JLabel answerLabel, guessLabel, currentGuess;
    boolean actionMade = false;
    private int result;

    public Game(Model _model){
        model = _model;
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(300, 250));

        guessLabel = new JLabel("Number of guesses: " + model.getGuesses());
        answerLabel = new JLabel("Your guess was: ");
        currentGuess = new JLabel("You guessed: ");

        textFieldGame = new JTextField("Enter your set here");
        textFieldGame.setPreferredSize(new Dimension(150, 18));

        JButton guessButton = new JButton("Guess");
        guessButton.setPreferredSize(new Dimension(75, 18));
        guessButton.addActionListener(this);

        panel.add(guessLabel);
        panel.add(textFieldGame);
        panel.add(guessButton);
        panel.add(answerLabel);
        add(panel);
    }

    public void guess(int _guess){
        result = model.guess(_guess);
        guessLabel.setText("Number of guesses: " + model.getGuesses());
    }

    public JTextField getTextFieldSetup(){
        if(textFieldSetup == null){
            textFieldSetup = new JTextField(15);
            textFieldSetup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        model.setNumber(Integer.parseInt(textFieldSetup.getText()));
                        System.out.println("new number: " + model.getNumber());
                    }
                    catch(Exception ex){
                        System.out.println(ex.toString());
                    }
                }
            });
        }
        return textFieldSetup;
    }

    public void setCurrentGuess(int num){
        currentGuess.setText("Your current guess: " + num);
    }

    public void setGuessLabel(String text){
        guessLabel.setText(text);
    }

    public void setAnswerLabel(String text){
        answerLabel.setText(text);
    }

    public JTextField getTextFieldGame(){
        return textFieldGame;
    }

    public int getResult(){
        return result;
    }

    public boolean getActionMade(){
        return actionMade;
    }

    public int getGuess(){
        return model.getResult();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getNewValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionMade = true;
        guess(Integer.parseInt(textFieldGame.getText()));

        //answerLabel.setText("Your guess was: " + result);
        guessLabel.setText("Number of guesses: " + model.getGuesses());
    }
}
