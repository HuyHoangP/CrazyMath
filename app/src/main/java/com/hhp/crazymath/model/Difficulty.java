package com.hhp.crazymath.model;

public class Difficulty {
    private int level;
    private int range;
    private int wrongRange;
    private int numKey;
    private int numNum;

    public Difficulty(int level) {
        this.level = level;
        numKey = 3;
        switch (level){
            case 1:
                range = 9;
                numNum = 2;
                wrongRange = 3;
                break;
            case 2:
                range = 9;
                numNum = 3;
                wrongRange = 3;
                break;
            case 3:
                range = 19;
                numNum = 2;
                wrongRange = 5;
                break;
            case 4:
                range = 19;
                numNum = 3;
                wrongRange = 5;
                break;
            case 5:
                range = 99;
                numNum = 2;
                wrongRange = 20;
                break;
            case 6:
                range = 99;
                numNum = 3;
                wrongRange = 20;
                break;
            case 7:
                range = 999;
                numNum = 3;
                numKey = 4;
                wrongRange = 200;
                break;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getRange() {
        return range;
    }

    public int getNumKey() {
        return numKey;
    }

    public int getNumNum() {
        return numNum;
    }

    public int getWrongRange() {
        return wrongRange;
    }
}
