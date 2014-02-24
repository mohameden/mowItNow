package org.mowitnow.mower.model;

public class Position {

    private final int x;

    private final int y;

    private final Direction direction;

    public Position(final int x, final int y, final Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(' ');
        sb.append(y);
        sb.append(' ');
        sb.append(direction.getCode());
        return sb.toString();
    }
}
