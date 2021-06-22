package de.propra.defense;

import de.propra.defense.ui.GamePanel;
import java.util.Optional;
import java.util.Set;

public class Unit {

  private final GamePanel game;
  protected int delay;
  protected double full = 0;
  private int row;
  private int col;
  private String skin;

  protected int hitpoints;
  private int strength;

  public Unit(GamePanel game, int row, int col, int hitpoints, int strength, String skin) {
    this.skin = skin;
    this.game = game;
    this.row = row;
    this.col = col;
    this.hitpoints = hitpoints;
    this.strength = strength;

  }

  public void placeUnit(int row, int col) {
    if (game.occupied(row, col)) return;
    game.removeUnit(this.row, this.col);
    game.placeUnit(skin, row, col);
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public boolean isStationary() {
    return false;
  }

  public boolean isLivingPlant() {
    return false;
  }

  public boolean isLivingBug() {
    return false;
  }

  public boolean isLivingEnemy() {
    return false;
  }

  public boolean isLivingCrow() {
    return false;
  }

  public boolean isLivingJens() {
    return false;
  }

  public boolean isLiving(){
    return hitpoints > 0;
  }

  public void act(Set<Unit> units) {}

  protected void moveRandomly() {
    placeUnit(randomWalk(row), randomWalk(col));
  }

  protected void moveTowards(Unit other) {
    int nextrow = row;
    int nextcol = col;
    if (row < other.row) nextrow++;
    if (row > other.row) nextrow--;
    if (col < other.col) nextcol++;
    if (col > other.col) nextcol--;
    placeUnit(nextrow, nextcol);
  }


  private int randomWalk(int r) {
    if (Math.random() < 0.25) return r - 1;
    if (Math.random() > 0.75) return r + 1;
    return r;
  }


  protected void attack(Unit other) {
    System.out
        .println(this.getClass().getSimpleName() + " hits " + other.getClass().getSimpleName() + " Remaining Hitpoints: " + other.hitpoints);
    other.hit(strength);
  }

  private void hit(int strength) {
    hitpoints -= strength;
  }

  protected boolean isNeighbor(Unit other, int dist) {
    return Math.abs(row - other.row) <= dist &&
        Math.abs(col - other.col) <= dist;
  }

  protected int distance(Unit me, Unit a, Unit b) {
    var d1 = distance(me, a);
    var d2 = distance(me, b);
    return Double.compare(d1, d2);
  }

  private double distance(Unit me, Unit b) {
    return Math.sqrt(square(me.row - b.row) + square(me.col - b.col));
  }

  private double square(int v) {
    return v * v;
  }

  public boolean isDead() {
    return hitpoints <= 0;
  }

  public boolean isAlive() {
    return hitpoints > 0;
  }
}
