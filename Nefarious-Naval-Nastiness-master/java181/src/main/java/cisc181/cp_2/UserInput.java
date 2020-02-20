package cisc181.cp_2;

/**
 * Created by Katie on 4/30/2019.
 */

public abstract interface UserInput<G extends Game> {
    public abstract <T extends Action<G>> T  getUserNextMove(G game);
}
