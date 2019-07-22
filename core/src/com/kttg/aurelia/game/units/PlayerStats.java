package com.kttg.aurelia.game.units;

public class PlayerStats {
    static float armor = 1, speedModifier = 0, maxHealth = 100;
    static String weapon = "Basic";

    public static float getMaxHealth(){
        return maxHealth;
    }
    public static float getArmor(){
        return armor;
    }
    public static String getWeapon(){
        return weapon;
    }

}
