package org.mowitnow.mower;

import org.mowitnow.mower.model.Direction;
import org.mowitnow.mower.model.Lawn;
import org.mowitnow.mower.model.Mower;
import org.mowitnow.mower.model.Position;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class MowerTest {
	private static final String BASIC_COMMANDS = "BasicCommands";

	@Test(expectedExceptions = { AssertionError.class })
	public void should_not_create_Lawn_with_negative_dimensions() {
		new Lawn(-2, -2);
	}

	public void should_not_move_when_on_Lawn_bounds_only_direction_can_change() {
		final Lawn lawn = new Lawn(1, 1);
		final Position position = new Position(1, 1, Direction.NORTH);
		final Mower mower = new Mower(lawn, position);
		mower.executeCommand("AA");
		AssertJUnit.assertEquals(position, mower.getPosition());
		mower.executeCommand("G");
		AssertJUnit.assertNotSame(position, mower.getPosition());
		AssertJUnit.assertNotSame(position.getDirection(), mower.getPosition()
				.getDirection());
		AssertJUnit.assertEquals(position.getX(), mower.getPosition().getX());
		AssertJUnit.assertEquals(position.getY(), mower.getPosition().getY());

	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void should_fail_when_command_is_wrong() throws Exception {
		final Lawn lawn = new Lawn(2, 2);
		final Position position = new Position(1, 1, Direction.NORTH);
		final Mower mower = new Mower(lawn, position);
		Exception expected = null;
		try {
			mower.executeCommand("AB");
		} catch (final Exception e) {
			expected = e;
		}
		AssertJUnit
				.assertNotSame(
						"Motion is not transactionnal, the A command should be executed",
						position, mower.getPosition());
		throw expected;
	}

	@Test(dataProvider = BASIC_COMMANDS)
	public void basic_mower_test(final int x, final int y,
			final String direction, final String command, final String result) {
		final Lawn lawn = new Lawn(5, 5);
		final Position position = new Position(x, y,
				Direction.findByCode(direction));
		final Mower mower = new Mower(lawn, position);
		AssertJUnit.assertEquals(result, mower.executeCommand(command));
	}

	@DataProvider(name = BASIC_COMMANDS)
	public Object[][] directionsProvider() {
		return new Object[][] { { 1, 2, "N", "GAGAGAGAA", "1 3 N" },
				{ 3, 3, "E", "AADAADADDA", "5 1 E" } };
	}
}
