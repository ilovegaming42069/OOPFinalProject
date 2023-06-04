package package01;

import package02.Knife;

public class Story {
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    public Story(Game game, UI ui, VisibilityManager vm) {
        this.game = game;
        this.ui = ui;
        this.vm = vm;
    }
    public void defaultSetup(){
        //Default HP
        player.hp = 10;
        ui.hpNumberLabel.setText(""+player.hp);
        //Default current weapon
        player.currentWeapon = new Knife();
        ui.weaponLabel.setText(player.currentWeapon.name);
    }
    public void selectPosition(String nextPosition){
        switch (nextPosition) {
            case "castleDoor" -> castleDoor();
            case "openDoor" -> openDoor();
            case "punchDoor" -> punchDoor();
            case "crossRoad" -> crossRoad();
            case "north" -> north();
            case "west" -> west();
            case "east" -> east();
        }
    }
    public void castleDoor(){
        //Game display
        ui.mainTextArea.setText("You are in front of a huge castle.\nThe front door is locked.\n\nWhat do you do?");
        ui.choice1.setText("Open the door");
        ui.choice2.setText("Punch the door");
        ui.choice3.setText("Cross the road");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "openDoor";
        game.nextPosition2 = "punchDoor";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }
    private void openDoor(){
        //Game display
        ui.mainTextArea.setText("The door is locked.");
        ui.choice1.setText("Continue");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "castleDoor";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void punchDoor(){
        //Game display
        ui.mainTextArea.setText("You punched the door.\nYour knuckles are now in pain\n\n(You HP is reduced by 3)");
        player.hp = player.hp - 3;
        player.hpChecker();
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText("Continue");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "castleDoor";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void crossRoad(){
        //Game display
        ui.mainTextArea.setText("You are on a crossroad.\nIf you go south, you will go back to the castle");
        ui.choice1.setText("Go north");
        ui.choice2.setText("Go west");
        ui.choice3.setText("Go east");
        ui.choice4.setText("Go south");
        //Game logic
        game.nextPosition1 = "north";
        game.nextPosition2 = "west";
        game.nextPosition3 = "east";
        game.nextPosition4 = "castleDoor";
    }
    private void north(){
        //Game display
        ui.mainTextArea.setText("You found a river.\nYou drank some water the river.\n\n(You HP is recovered by 2)");
        player.hp = player.hp + 2;
        player.hpChecker();
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText("Go south");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void west(){
        //Game display
        ui.mainTextArea.setText("You found a river.\nYou drank some water the river.\n\n(You HP is recovered by 2)");
        player.hp = player.hp + 2;
        player.hpChecker();
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText("Go south");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void east(){

    }
}

