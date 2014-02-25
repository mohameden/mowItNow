package org.mowitnow.mower.model;

public class Lawn {

    private final int maxX;

    private final int maxY;

    public Lawn(final int maxX, final int maxY) {
        assert maxX >= 0 && maxY >= 0;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean containsPosition(final Position position) {
        return position.getX() >= 0 && position.getX() <= maxX
                && position.getY() >= 0 && position.getY() <= maxY;
    }
}
