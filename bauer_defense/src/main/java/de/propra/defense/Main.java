package de.propra.defense;

import static de.propra.defense.ui.GamePanel.H;
import static de.propra.defense.ui.GamePanel.TSZ;
import static de.propra.defense.ui.GamePanel.W;


import de.propra.defense.ui.GamePanel;
import de.propra.defense.units.enemies.Bug;
import de.propra.defense.units.enemies.Crow;
import de.propra.defense.units.guards.Frog;
import de.propra.defense.units.overlord.Jens;
import de.propra.defense.units.stationaries.Plant;
import de.propra.defense.units.stationaries.Scarecrow;

import java.awt.GridLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JFrame;

public class Main {

  Random random = new Random();

  private Set<Unit> units = new HashSet<>();

  private boolean running = true;

  public static void main(String[] args) {
    Main main = new Main();
    main.start();
  }

  private void start() {
    GamePanel window = setupGamePanel();

    units.addAll(List.of(
        new Frog(window, 3, 6, "default_frog"),
        new Scarecrow(window, 9, 7, "default_scarecrow"),
        new Bug(window, 9, 28, "bug_red"),
        new Bug(window, 4, 22, "bug_green"),
        new Crow(window, 12, 26, "default_crow"),
        new Plant(window, 12, 2, "default_plant"),
        new Plant(window, 12, 3, "default_plant"),
        new Plant(window, 13, 2, "default_plant"),
        new Jens(window, 8, 15, "default_jens"),
        new Plant(window, 13, 3, "default_plant")));

    initializePositions();

    while (running) {
      allUnitsAct();
      removeDeadUnits(window);
      window.repaint();
      sleep();
      checkGameEnd();
    }


  }

  private GamePanel setupGamePanel() {
    GamePanel window = new GamePanel("Bauer Defence");
    window.setSize(W * TSZ, H * TSZ);
    window.setLayout(new GridLayout(H, W));
    window.setResizable(false);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    return window;
  }

  private void initializePositions() {
    for (Unit unit : units) {
      unit.placeUnit(unit.getRow(), unit.getCol());
    }
  }

  private void allUnitsAct() {
    units.stream().filter(Unit::isAlive).forEach(u -> u.act(units));
  }

  private void checkGameEnd() {
    long unitCount = units.stream().filter(Unit::isLiving).count();
    if (unitCount == 0) {
      System.out.println("Jens hat gewonnen");
      running = false;
      return;
    }
    /*
    long plantCount = units.stream().filter(Unit::isLivingPlant).count();
    if (plantCount == 0) {
      System.out.println("Spiel verloren");
      running = false;
      return;
    }
    */
    long enemyCount = units.stream().filter(Unit::isLivingEnemy).count();
    if (enemyCount == 0) {
      System.out.println("Spiel gewonnen");
      running = false;
      return;
    }

  }

  private void sleep() {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void removeDeadUnits(GamePanel window) {
    units.stream()
        .filter(Unit::isDead)
        .forEach(u -> window.removeUnit(u.getRow(), u.getCol()));

    units = units.stream().filter(Unit::isAlive).collect(Collectors.toSet());
  }


}
