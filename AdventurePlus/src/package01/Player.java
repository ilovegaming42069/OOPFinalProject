package package01;

import package02.SuperWeapon;

public class Player {
    public int hp, maxhp = 15, minhp = 0;
    public SuperWeapon currentWeapon;
    public void hpChecker(){
        if(hp>maxhp){
            hp = maxhp;
        }
        else if(hp<minhp){
            hp = minhp;
        }
    }
}
