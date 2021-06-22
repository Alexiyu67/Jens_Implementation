package de.propra.defense.units.stationaries;

import de.propra.defense.Unit;
import de.propra.defense.ui.GamePanel;


public class Plant extends Unit {
    public Plant(GamePanel game, int row, int col, String skin) {
        super(game,row, col, 5, 0, skin);
    }

    public boolean isLivingPlant() {
        return hitpoints > 0;
    }

    public boolean isStationary() {
        return true;
    }
}
