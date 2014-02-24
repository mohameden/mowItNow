package org.mowitnow.mower.model;

public class Mower {

	private final Lawn lawn;

	private Position position;

	public Mower(final Lawn lawn, final Position position) {
		this.lawn = lawn;
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void moveTo(final Position position) {
		if (lawn.containsPosition(position)) {
			this.position = position;
		}
	}

	public String showPosition() {
		return position.toString();
	}

	public String executeCommand(final String command) {
		for (int i = 0; i < command.length(); i++) {
			final char c = command.charAt(i);
			final MowerAction action = MowerAction.findByCode(c);
			action.execute(this);
		}
		return position.toString();
	}
}
