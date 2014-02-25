package org.mowitnow.mower.model;

public enum Direction {
    NORTH('N', 0, 1) {
        @Override
        public Direction getRight() {
            return EST;
        }

        @Override
        public Direction getLeft() {
            return WEST;
        }
    },

    EST('E', 1, 0) {
        @Override
        public Direction getRight() {
            return SOUTH;
        }

        @Override
        public Direction getLeft() {
            return NORTH;
        }
    },
    SOUTH('S', 0, -1) {
        @Override
        public Direction getRight() {
            return WEST;
        }

        @Override
        public Direction getLeft() {
            return EST;
        }
    },
    WEST('W', -1, 0) {
        @Override
        public Direction getRight() {
            return NORTH;
        }

        @Override
        public Direction getLeft() {
            return SOUTH;
        }
    };

    private final char code;

    private final int xAddOn;

    private final int yAddOn;

    private Direction(final char code, final int xAddOn, final int yAddOn) {
        this.code = code;
        this.xAddOn = xAddOn;
        this.yAddOn = yAddOn;
    }

    public char getCode() {
        return code;
    }

    public int getxAddOn() {
        return xAddOn;
    }

    public int getyAddOn() {
        return yAddOn;
    }

    public abstract Direction getRight();

    public abstract Direction getLeft();

    public static Direction findByCode(final char code) {
        for (final Direction direction : Direction.values()) {
            if (direction.code == code) {
                return direction;
            }
        }
        throw new IllegalArgumentException("unknown direction code : " + code);
    }

    public static Direction findByCode(final String code) {
        assert code.length() == 1;
        return findByCode(code.charAt(0));
    }
}
