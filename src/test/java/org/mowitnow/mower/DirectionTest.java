package org.mowitnow.mower;

import org.mowitnow.mower.model.Direction;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class DirectionTest {
	private static final String DIRECTION_ADD_ON = "DirectionAddOn";
	private static final String DIRECTION_CODES = "DirectionCodes";

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void should_not_find_direction_and_fail() {
		Direction.findByCode('G');
	}

	@Test
	public void should_find_NORTH_direction() {
		final Direction direction = Direction.findByCode('N');
		AssertJUnit.assertEquals(Direction.NORTH, direction);
	}

	@Test(dataProvider = DIRECTION_CODES)
	public void find_a_direction_and_its_nears(final String code,
			final String name, final String right, final String left) {
		final Direction direction = Direction.findByCode(code.charAt(0));
		AssertJUnit.assertEquals(Direction.valueOf(name), direction);
		AssertJUnit
				.assertEquals(Direction.valueOf(right), direction.getRight());
		AssertJUnit.assertEquals(Direction.valueOf(left), direction.getLeft());
	}

	@Test(dataProvider = DIRECTION_ADD_ON)
	public void find_direction_test_AddOn(final String code, final String name,
			final int xAddOn, final int yAddOn) {
		final Direction direction = Direction.findByCode(code.charAt(0));
		AssertJUnit.assertEquals(Direction.valueOf(name), direction);
		AssertJUnit.assertEquals(xAddOn, direction.getxAddOn());
		AssertJUnit.assertEquals(yAddOn, direction.getyAddOn());
	}

	@DataProvider(name = DIRECTION_CODES)
	public Object[][] directionsProvider() {
		return new Object[][] { { "N", "NORTH", "EST", "WEST" },
				{ "S", "SOUTH", "WEST", "EST" },
				{ "E", "EST", "SOUTH", "NORTH" },
				{ "W", "WEST", "NORTH", "SOUTH" } };
	}

	@DataProvider(name = DIRECTION_ADD_ON)
	public Object[][] AddOnProvider() {
		return new Object[][] { { "N", "NORTH", 0, 1 },
				{ "S", "SOUTH", 0, -1 }, { "E", "EST", 1, 0 },
				{ "W", "WEST", -1, 0 } };
	}
}
