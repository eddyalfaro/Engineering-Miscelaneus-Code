package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class ForceUnitsTest {

	UnitChanger changer;
	double original = 1.0;
	ForceUnits[] units = ForceUnits.values();
	double delta = 1e-3;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetLenghtUnits() {
		assertTrue(LenghtUnits.METER == ForceUnits.NEWTON.getLenghtUnits());
		assertTrue(LenghtUnits.CENTIMETER == ForceUnits.DYNES.getLenghtUnits());
		assertTrue(LenghtUnits.FOOT == ForceUnits.POUNDAL.getLenghtUnits());
		assertTrue(null == ForceUnits.POUNDFORCE.getLenghtUnits());
		assertTrue(null == ForceUnits.KILOPOND.getLenghtUnits());
	}

	@Test
	void testGetMassUnits() {
		assertTrue(MassUnits.KILOGRAM == ForceUnits.NEWTON.getMassUnits());
		assertTrue(MassUnits.GRAM == ForceUnits.DYNES.getMassUnits());
		assertTrue(MassUnits.POUND == ForceUnits.POUNDAL.getMassUnits());
		assertTrue(null == ForceUnits.POUNDFORCE.getMassUnits());
		assertTrue(null == ForceUnits.KILOPOND.getMassUnits());
	}

	@Test
	void testGetTimeUnits() {
		assertTrue(TimeUnits.SECONDS == ForceUnits.NEWTON.getTimeUnits());
		assertTrue(TimeUnits.SECONDS == ForceUnits.DYNES.getTimeUnits());
		assertTrue(TimeUnits.SECONDS == ForceUnits.POUNDAL.getTimeUnits());
		assertTrue(null == ForceUnits.POUNDFORCE.getTimeUnits());
		assertTrue(null == ForceUnits.KILOPOND.getTimeUnits());
	}

	@Test
	void testIsSI() {
		assertTrue(ForceUnits.NEWTON.isSI());
		assertFalse(ForceUnits.DYNES.isSI());
		assertFalse(ForceUnits.POUNDAL.isSI());
		assertFalse(ForceUnits.POUNDFORCE.isSI());
		assertFalse(ForceUnits.KILOPOND.isSI());
	}

	@Test
	void testToString() {
		assertEquals("N", ForceUnits.NEWTON.toString());
		assertEquals("dyn", ForceUnits.DYNES.toString());
		assertEquals("pdl", ForceUnits.POUNDAL.toString());
		assertEquals("lbf", ForceUnits.POUNDFORCE.toString());
		assertEquals("kp", ForceUnits.KILOPOND.toString());
	}

	@Test
	void testChangeUnits() throws InvalidInputException {
		changer = ForceUnits.NEWTON;
		assertEquals(1.0, changer.changeUnits(original, units[0]), delta);
		assertEquals(1.0e5, changer.changeUnits(original, units[1]), delta);
		assertEquals(0.1019716, changer.changeUnits(original, units[2]), delta);
		assertEquals(0.2248089, changer.changeUnits(original, units[3]), delta);
		assertEquals(7.233013, changer.changeUnits(original, units[4]), delta);
		
		changer = ForceUnits.POUNDFORCE;
		original = 45.6;
		assertEquals(202.8389056556, changer.changeUnits(original, units[0]), delta);
		assertEquals(20283890.56556, changer.changeUnits(original, units[1]), delta);
		assertEquals(20.68381207197, changer.changeUnits(original, units[2]), delta);
		assertEquals(original, changer.changeUnits(original, units[3]), delta);
		assertEquals(1467.136614171, changer.changeUnits(original, units[4]), delta);
	}

}
