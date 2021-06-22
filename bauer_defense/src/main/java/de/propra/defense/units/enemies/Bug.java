package de.propra.defense.units.enemies;

import de.propra.defense.Unit;
import de.propra.defense.ui.GamePanel;

import java.util.Optional;
import java.util.Set;



public class Bug extends Unit {

    public Bug(GamePanel game, int row, int col, String skin) {
        super(game, row, col, 10, 1, skin);
    }

    public boolean isLivingBug() {
        return hitpoints > 0;
    }

    public boolean isLivingEnemy() {
        return hitpoints > 0;
    }

    public void act(Set<Unit> units) {
        Optional<Unit> nearestPlant =
                units.stream().filter(Unit::isLivingPlant).min((o1, o2) -> distance(this, o1, o2));
        if (nearestPlant.isPresent()) {
            Unit plant = nearestPlant.get();
            if (isNeighbor(plant, 1)) {
                attack(plant);
            }
            else {
                double decide = Math.random();
                if (decide > 0.8) {
                    moveRandomly();
                }
                else {
                    moveTowards(plant);
                }
            }
        }
    }
}
