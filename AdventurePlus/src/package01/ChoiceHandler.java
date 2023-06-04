package package01;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ChoiceHandler implements ActionListener{
    Game game;
    public ChoiceHandler(Game game) {
        this.game = game;
    }
    public void actionPerformed(ActionEvent event){
        String yourChoice = event.getActionCommand();
        switch (yourChoice) {
            case "start" -> {
                game.vm.titleToTown();
                game.story.castleDoor();
            }
            case "c1" -> game.story.selectPosition(game.nextPosition1);
            case "c2" -> game.story.selectPosition(game.nextPosition2);
            case "c3" -> game.story.selectPosition(game.nextPosition3);
            case "c4" -> game.story.selectPosition(game.nextPosition4);
        }
    }
}