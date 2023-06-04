package package02;

public class LongSword extends SuperWeapon {

    public LongSword() {
        name = "Long Sword";
        damage = new java.util.Random().nextInt(12);;
    }
}