/**
 * Created by Rick on 3/29/2017.
 */
public enum State {
    MENU("menu"),
    GAME("game");

    private String name;

    private State(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
