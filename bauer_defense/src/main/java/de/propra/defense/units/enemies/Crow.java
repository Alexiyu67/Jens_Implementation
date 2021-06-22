package de.propra.defense.units.enemies;

import de.propra.defense.Unit;
import de.propra.defense.ui.GamePanel;

import java.util.Optional;
import java.util.Set;

public class Crow extends Unit {

    public Crow(GamePanel game, int row, int col, String skin) {
        super(game, row, col, 8, 3, skin);
    }

    public boolean isLivingEnemy() {
        return hitpoints > 0;
    }

    public boolean isLivingCrow() {
        return hitpoints > 0;
    }


    public void act(Set<Unit> units) {
        Optional<Unit> nearest =
        units.stream().filter(Unit::isLivingPlant).min((o1, o2) -> distance(this, o1, o2));
        if (nearest.isPresent()) {
            Unit plant = nearest.get();
            if (isNeighbor(plant, 1)) {
                attack(plant);
                full += 0.21;
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
