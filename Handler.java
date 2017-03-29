/**
 * Created by Rick on 3/29/2017.
 */
import java.awt.CardLayout;

import javax.swing.JPanel;

/*
 * Class that handles changing the different states of the game and gives reference to EatHealthy
 * so that other classes may have an easier time accessing its variables and components.
 */
public class Handler {

    private EatHealthy game;
    private CardLayout cl;

    public Handler(EatHealthy game) {
        this.game = game;
        cl = new CardLayout();
    }

    public void changeGameState(State name) {
        JPanel tempStates = game.getGUIPanel();
        cl.show(tempStates, name.toString());
    }

    public EatHealthy getGame() {
        return game;
    }

    public CardLayout getLayout() {
        return cl;
    }

    public User getUser(){
        return game.getUser();
    }
}