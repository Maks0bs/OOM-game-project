package com.oom.game.main.entities;

public interface ProgressiveCreature {
    void addExpPoints(int exp);
    int getLevel();
    void onLevelUp(int level);
    void displayProgress();
}
