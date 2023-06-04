package package01;

public class VisibilityManager {
    UI ui;
    public VisibilityManager(UI ui){
        this.ui = ui;
    }
    public void showTitleScreen(){
        //Show Title Screen
        ui.titlePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);
        //Hide Game Screen
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);
    }
    public void titleToTown(){
        //Hide Title Screen
        ui.titlePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);
        //Show Game Screen
        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
    }
}
