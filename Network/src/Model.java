/**
 * Created with IntelliJ IDEA.
 * User: rmetri
 * Date: 11/19/13
 * Time: 8:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Model {
    private int number, guesses, result;

    public Model(){

    }

    public void setNumber(int num){
        number = num;
    }

    public void setGuesses(int num){
        guesses = num;
    }

    public int getNumber(){
        return number;
    }

    public int getGuesses(){
        return guesses;
    }

    public int getResult(){
        return result;
    }

    public int guess(int _guess){
        if(_guess > number)
            result = 1;
        else if(_guess < number)
            result = -1;
        else
            result = 0;
        guesses++;
        return result;
    }
}
