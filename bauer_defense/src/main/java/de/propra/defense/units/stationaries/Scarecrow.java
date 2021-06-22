package de.propra.defense.units.stationaries;

import de.propra.defense.Unit;
import de.propra.defense.ui.GamePanel;

import java.util.Optional;
import java.util.Set;

public class Scarecrow extends Unit {

    public Scarecrow(GamePanel game, int row, int col, String skin) {
        super(game, row, col, 3, 8, skin);
    }

    public boolean isStationary() {
        return true;
    }

    public void act(Set<Unit> units) {
        Optional<Unit> nearest =
        units.stream().filter(Unit::isLivingCrow).min((o1, o2) -> distance(this, o1, o2));
        if (nearest.isPresent()) {
            Unit plant = nearest.get();
            if (isNeighbor(plant, 3)) {
                attack(plant);
                full += 0.21;
            }
        }
    }
}