package org.mowitnow.mower.model;

public enum MowerAction {

    LEFT('G') {
        @Override
        public void execute(final Mower mower) {
            final Position oldPosition = mower.getPosition();
            final Position newPosition = new Position(oldPosition.getX(),
                    oldPosition.getY(), oldPosition.getDirection().getLeft());
            mower.moveTo(newPosition);
        }
    },
    RIGHT('D') {
        @Override
        public void execute(final Mower mower) {
            final Position oldPosition = mower.getPosition();
            final Position newPosition = new Position(oldPosition.getX(),
                    oldPosition.getY(), oldPosition.getDirection().getRight());
            mower.moveTo(newPosition);
        }
    },
    MOVE('A') {
        @Override
        public void execute(final Mower mower) {
            final Position oldPosition = mower.getPosition();
            final Position newPosition = new Position(
                    oldPosition.getX() + oldPosition.getDirection().getxAddOn(),
                    oldPosition.getY() + oldPosition.getDirection().getyAddOn(),
                    oldPosition.getDirection());
            mower.moveTo(newPosition);
        }
    };

    private final char code;

    private MowerAction(final char code) {
        this.code = code;
    }

    public abstract void execute(Mower mover);

    public char getCode() {
        return code;
    }

    public static MowerAction findByCode(final char code) {
        for (final MowerAction command : MowerAction.values()) {
            if (command.code == code) {
                return command;
            }
        }
        throw new IllegalArgumentException("unknown Action code: " + code);
    }
}
