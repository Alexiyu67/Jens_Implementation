package de.propra.defense.units.guards;

import de.propra.defense.Unit;
import de.propra.defense.ui.GamePanel;

import java.util.Optional;
import java.util.Set;

public class Frog extends Unit {

    public Frog(GamePanel game, int row, int col, String skin) {
        super(game, row, col, 1, 2, skin);
    }

    public void act(Set<Unit> units) {
        Optional<Unit> nearest =
                units.stream().filter(Unit::isLivingBug).min((o1, o2) -> distance(this, o1, o2));
        if (nearest.isPresent()) {
            Unit bug = nearest.get();
            if (isNeighbor(bug, 1)) {
                attack(bug);
                full += 0.21;
            }
            else {
                if (delay == (int)full) {
                    moveTowards(bug);
                    delay = 0;
                }
                else {
                    delay++;
                }
            }
        }
    }
}
