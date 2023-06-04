package package02;

public class Knife extends SuperWeapon{
    public Knife(){
        name = "Knife";
        damage = new java.util.Random().nextInt(3);;
    }
}
