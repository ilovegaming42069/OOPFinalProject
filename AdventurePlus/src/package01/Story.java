package package01;

import package02.*;

import java.util.Objects;

public class Story {
    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    SuperMonster monster = new Goblin();

    boolean castleKey;

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
        ui.weaponTypeLabel.setText(player.currentWeapon.name);
        //Default inventory
        castleKey = false;
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
            case "fight" -> fight();
            case "playerAttack" -> playerAttack();
            case "monsterAttack" -> monsterAttack();
            case "win" -> win();
            case "lose" -> lose();
            case "toTitle" -> toTitle();
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
        if(castleKey){
            ending();
        }
        else{
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
        if(player.hp>0){
            game.nextPosition1 = "castleDoor";
        } else {
            game.nextPosition1 = "lose";
        }
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
        if(player.hp!=player.maxhp){
            ui.mainTextArea.setText("You found a river.\nYou drank some water the river.\n\n(You HP is recovered by 2)");
        }else{
            ui.mainTextArea.setText("You found a river.\nYou're not thirsty.");
        }
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
        if(Objects.equals(player.currentWeapon.name, "Long Sword")){
            westAlternative();
        }
        else{
            //Game display
            ui.mainTextArea.setText("You walked into a forest.\nYou found a long sword\n\n(Long sword added to inventory)");
            player.currentWeapon = new LongSword();
            ui.weaponTypeLabel.setText(player.currentWeapon.name);
            ui.choice1.setText("Go east");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");
            //Game logic
            game.nextPosition1 = "crossRoad";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }
    private void east(){
        if(monster.hp <= 0){
            eastAlternative();
        }
        else{
            //Game display
            ui.mainTextArea.setText("You encounter a "+ monster.name + "!");
            ui.choice1.setText("Fight");
            ui.choice2.setText("Run away");
            ui.choice3.setText("");
            ui.choice4.setText("");
            //Game logic
            game.nextPosition1 = "fight";
            game.nextPosition2 = "crossRoad";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }
    private void fight(){
        //Game display
        ui.mainTextArea.setText(monster.name + " HP: " + monster.hp + "\n\nWhat do you do?");
        ui.choice1.setText("Attack");
        ui.choice2.setText("Run away");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void playerAttack(){
        //Game display
        int damage = new java.util.Random().nextInt(player.currentWeapon.damage);
        ui.mainTextArea.setText("You attacked the " + monster.name + " and gave " + damage + " damage!");
        monster.hp = monster.hp - damage;
        ui.choice1.setText("Continue");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        if(monster.hp>0){
            game.nextPosition1 = "monsterAttack";
        } else {
            game.nextPosition1 = "win";
        }
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void monsterAttack(){
        //Game display
        int damage = new java.util.Random().nextInt(monster.attack);
        ui.mainTextArea.setText("The " + monster.name + " attacked you and gave " + damage + " damage!");
        player.hp = player.hp - damage;
        player.hpChecker();
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText("Continue");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        if(player.hp>0){
            game.nextPosition1 = "fight";
        } else {
            game.nextPosition1 = "lose";
        }
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void win(){
        //Game display
        ui.mainTextArea.setText("You defeated the monster!\nThe monster dropped something\n\n(Castle Key added to your inventory)");
        castleKey = true;
        ui.choice1.setText("Go west");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void lose(){
        //Game display
        ui.mainTextArea.setText("You are dead!\n\n(GAME OVER)");
        ui.choice1.setText("Restart");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "toTitle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void ending(){
        //Game display
        ui.mainTextArea.setText("You unlocked the door.\nYou pushed the door forward, and it slowly creaks open.\n\n(TO BE CONTINUED)");
        ui.choice1.setText("Restart");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "toTitle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void westAlternative(){
        ui.mainTextArea.setText("You walked into a forest.\nYou found nothing.");
        ui.choice1.setText("Go east");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void eastAlternative(){
        ui.mainTextArea.setText("The goblin is dead.\n\nThere is nothing left to do.");
        ui.choice1.setText("Go west");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        //Game logic
        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    private void toTitle(){
        defaultSetup();
        vm.showTitleScreen();
    }
}

