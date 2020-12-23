package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class VolumeUnitsTest {

	UnitChanger changer;
	double original = 1.0;
	VolumeUnits[] units = VolumeUnits.values();
	double delta = 1e-3;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetVolumeUnit() {
		assertTrue(VolumeUnits.CUBICMETER == VolumeUnits.getVolumeUnit(LenghtUnits.METER));
		assertTrue(VolumeUnits.CUBICFEET == VolumeUnits.getVolumeUnit(LenghtUnits.FOOT));
		assertTrue(VolumeUnits.CUBICINCHES == VolumeUnits.getVolumeUnit(LenghtUnits.INCH));
		assertTrue(VolumeUnits.CUBICYARD == VolumeUnits.getVolumeUnit(LenghtUnits.YARD));
		assertTrue(VolumeUnits.CUBICMILE == VolumeUnits.getVolumeUnit(LenghtUnits.MILE));
	}

	@Test
	void testGetLengthUnit() {
		assertTrue(LenghtUnits.METER == VolumeUnits.CUBICMETER.getLengthUnit());
		assertTrue(LenghtUnits.FOOT == VolumeUnits.CUBICFEET.getLengthUnit());
		assertTrue(LenghtUnits.INCH == VolumeUnits.CUBICINCHES.getLengthUnit());
		assertTrue(LenghtUnits.YARD == VolumeUnits.CUBICYARD.getLengthUnit());
		assertTrue(LenghtUnits.MILE == VolumeUnits.CUBICMILE.getLengthUnit());
		assertTrue(null == VolumeUnits.US_GALLON.getLengthUnit());
		assertTrue(null == VolumeUnits.OIL_BARREL.getLengthUnit());
		assertTrue(null == VolumeUnits.IMPERIAL_GALLON.getLengthUnit());
		assertTrue(null == VolumeUnits.LITER.getLengthUnit());
		
	}

	@Test
	void testIsSI() {
		assertTrue(VolumeUnits.CUBICMETER.isSI());
		assertFalse(VolumeUnits.CUBICFEET.isSI());
		assertFalse(VolumeUnits.CUBICINCHES.isSI());
		assertFalse(VolumeUnits.CUBICMILE.isSI());
		assertFalse(VolumeUnits.CUBICYARD.isSI());
		assertFalse(VolumeUnits.US_GALLON.isSI());
		assertFalse(VolumeUnits.OIL_BARREL.isSI());
		assertFalse(VolumeUnits.IMPERIAL_GALLON.isSI());
		assertFalse(VolumeUnits.LITER.isSI());
	}

	@Test
	void testToString() {
		assertEquals("c.m", VolumeUnits.CUBICMETER.toString());
		assertEquals("c.ft", VolumeUnits.CUBICFEET.toString());
		assertEquals("c.in", VolumeUnits.CUBICINCHES.toString());
		assertEquals("c.mile", VolumeUnits.CUBICMILE.toString());
		assertEquals("c.yd", VolumeUnits.CUBICYARD.toString());
		assertEquals("Gal", VolumeUnits.US_GALLON.toString());
		assertEquals("Bbl", VolumeUnits.OIL_BARREL.toString());
		assertEquals("Imp. gal", VolumeUnits.IMPERIAL_GALLON.toString());
		assertEquals("L", VolumeUnits.LITER.toString());
	}

	@Test
	void testChangeUnits() throws InvalidInputException {
		original = 1;
		changer = VolumeUnits.CUBICMETER;		
		assertEquals(original, changer.changeUnits(original, units[0]), delta);
		assertEquals(35.314667, changer.changeUnits(original, units[1]), delta);
		assertEquals(61023.744, changer.changeUnits(original, units[2]), delta);
		assertEquals(1.3079506, changer.changeUnits(original, units[3]), delta);
		assertEquals(2.40251e-10, changer.changeUnits(original, units[4]), delta);
		assertEquals(1000.00, changer.changeUnits(original, units[5]), delta);
		assertEquals(264.17205, changer.changeUnits(original, units[6]), delta);
		assertEquals(6.2898108, changer.changeUnits(original, units[7]), delta);
		assertEquals(219.96925, changer.changeUnits(original, units[8]), delta);
		
		original = 12;
		changer = VolumeUnits.OIL_BARREL;		
		assertEquals(1.9078475, changer.changeUnits(original, units[0]), delta);
		assertEquals(67.375, changer.changeUnits(original, units[1]), delta);
		assertEquals(116424.0, changer.changeUnits(original, units[2]), delta);
		assertEquals(2.4953704, changer.changeUnits(original, units[3]), delta);
		assertEquals(3.8144673704415E-11, changer.changeUnits(original, units[4]), delta);
		assertEquals(1907.8475, changer.changeUnits(original, units[5]), delta);
		assertEquals(504.0, changer.changeUnits(original, units[6]), delta);
		assertEquals(original, changer.changeUnits(original, units[7]), delta);
		assertEquals(419.66779, changer.changeUnits(original, units[8]), delta);
	}

}
