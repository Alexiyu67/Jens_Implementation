package de.propra.defense.units.overlord;

import de.propra.defense.Unit;
import de.propra.defense.ui.GamePanel;

import java.util.Optional;
import java.util.Set;

// Jens zerstört alles

public class Jens extends Unit {
    public Jens(GamePanel game, int row, int col, String skin) {
        super(game, row, col, 100, 100, skin);
    }

    public boolean isLivingEnemy() {
        return hitpoints > 0;
    }

    public boolean isLivingJens() {
        return hitpoints > 0;
    }

    public boolean isLiving() {
        return false;
    }

    public void act(Set<Unit> units) {
        Optional<Unit> nearest =
                units.stream().filter(Unit::isLiving).min((o1, o2) -> distance(this, o1, o2));
        if (nearest.isPresent()) {
            Unit plant = nearest.get();
            if (isNeighbor(plant, 1)) {
                attack(plant);
            }
            else {
                double decide = Math.random();
                if (decide > 0.9) {
                    moveRandomly();
                }
                else {
                    moveTowards(plant);
                }
            }
        }
    }
}
