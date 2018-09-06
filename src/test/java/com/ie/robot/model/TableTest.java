package com.ie.robot.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.ie.robot.model.Table;

class TableTest {

	@Test
	void testIsValidXY() {
		Table t = new Table(5, 5);

		// X
		Assert.assertEquals(true, t.isValidX(0));
		Assert.assertEquals(true, t.isValidX(4));
		Assert.assertEquals(false, t.isValidX(-1));
		Assert.assertEquals(false, t.isValidX(9));

		// Y
		Assert.assertEquals(true, t.isValidY(0));
		Assert.assertEquals(true, t.isValidY(4));
		Assert.assertEquals(false, t.isValidY(-1));
		Assert.assertEquals(false, t.isValidY(9));

		// XY
		Assert.assertEquals(false, t.isValidXY(-1, -1));
		Assert.assertEquals(false, t.isValidXY(-1, 0));
		Assert.assertEquals(false, t.isValidXY(-1, 4));
		Assert.assertEquals(false, t.isValidXY(-1, 9));

		Assert.assertEquals(false, t.isValidXY(0, -1));
		Assert.assertEquals(true, t.isValidXY(0, 0));
		Assert.assertEquals(true, t.isValidXY(0, 4));
		Assert.assertEquals(false, t.isValidXY(0, 9));

		Assert.assertEquals(false, t.isValidXY(4, -1));
		Assert.assertEquals(true, t.isValidXY(4, 0));
		Assert.assertEquals(true, t.isValidXY(4, 4));
		Assert.assertEquals(false, t.isValidXY(4, 9));

		Assert.assertEquals(false, t.isValidXY(9, -1));
		Assert.assertEquals(false, t.isValidXY(9, 0));
		Assert.assertEquals(false, t.isValidXY(9, 4));
		Assert.assertEquals(false, t.isValidXY(9, 9));

	}

}
