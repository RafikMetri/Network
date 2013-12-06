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
public class Setup extends JPanel implements PropertyChangeListener, ActionListener{
    private PropertyChangeSupport changer = new PropertyChangeSupport(this);
    private Model model = new Model();
    private Game game = new Game(model);
    private JLabel label, resultLabel;
    private int result;

    public Setup(int num){

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(300, 250));

        label = new JLabel();
        label.setText("" + num);
        resultLabel = new JLabel();
        resultLabel.setText("result: ");

        model.setGuesses(0);
        game.setGuessLabel("Number of guesses: " + model.getGuesses());

        panel.add(label);
        panel.add(resultLabel);

        add(panel);
    }

    public void set(int _guess){
        result = model.guess(_guess);
        game.setAnswerLabel("Your set was: " + result);
        game.setGuessLabel("Number of guesses: " + model.getGuesses());
    }

    public Model getModel(){
        return model;
    }

    public Game getGame(){
        return game;
    }

    public void setResultLabel(int num){
        resultLabel.setText("result: " + num);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getNewValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        set(Integer.parseInt(game.getTextFieldGame().getText()));

        game.setAnswerLabel("Your set was: " + result);
        game.setGuessLabel("Number of guesses: " + model.getGuesses());
    }
}
