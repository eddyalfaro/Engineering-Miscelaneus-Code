package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class LenghtUnitsTest {

	UnitChanger changer;
	double original = 1.0;
	LenghtUnits[] units = LenghtUnits.values();
	double delta = 1e-3;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testIsSI() {
		assertTrue(LenghtUnits.METER.isSI());
		assertFalse(LenghtUnits.CENTIMETER.isSI());
		assertFalse(LenghtUnits.FOOT.isSI());
		assertFalse(LenghtUnits.INCH.isSI());
		assertFalse(LenghtUnits.MILE.isSI());
		assertFalse(LenghtUnits.YARD.isSI());
	}

	@Test
	void testToString() {
		assertEquals("m", LenghtUnits.METER.toString());
		assertEquals("cm", LenghtUnits.CENTIMETER.toString());
		assertEquals("ft", LenghtUnits.FOOT.toString());
		assertEquals("in", LenghtUnits.INCH.toString());
		assertEquals("mile", LenghtUnits.MILE.toString());
		assertEquals("yd", LenghtUnits.YARD.toString());
	}

	@Test
	void testChangeUnits() throws InvalidInputException {
		changer = LenghtUnits.METER;
		original = 11.2;
		assertEquals(original, changer.changeUnits(original, units[0]), delta);
		assertEquals(11.2e2, changer.changeUnits(original, units[1]), delta);
		assertEquals(36.74540682415, changer.changeUnits(original, units[2]), delta);
		assertEquals(440.9448818898, changer.changeUnits(original, units[3]), delta);
		assertEquals(12.24846894138, changer.changeUnits(original, units[4]), delta);
		assertEquals(0.006959357353058, changer.changeUnits(original, units[5]), delta);
	}

}
