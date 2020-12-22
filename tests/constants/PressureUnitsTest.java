package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class PressureUnitsTest {
	
	UnitChanger changer;
	double original = 1.0;
	PressureUnits[] units = PressureUnits.values();
	double delta = 1e-3;

	@BeforeEach
	void setUp() throws Exception {		
	}

	@Test
	void testGetForceUnits() {
		assertTrue(ForceUnits.POUNDFORCE == PressureUnits.PSI.getForceUnits());
		assertTrue(null == PressureUnits.BARYE.getForceUnits());
		assertTrue(null == PressureUnits.ATMOSPHERE.getForceUnits());
		assertTrue(null == PressureUnits.PASCAL.getForceUnits());
		assertTrue(null == PressureUnits.BAR.getForceUnits());
		assertTrue(null == PressureUnits.TORR.getForceUnits());
	}

	@Test
	void testGetAreaUnits() {
		assertTrue(AreaUnits.SQINCH == PressureUnits.PSI.getAreaUnits());
		assertTrue(null == PressureUnits.BARYE.getAreaUnits());
		assertTrue(null == PressureUnits.ATMOSPHERE.getAreaUnits());
		assertTrue(null == PressureUnits.PASCAL.getAreaUnits());
		assertTrue(null == PressureUnits.BAR.getAreaUnits());
		assertTrue(null == PressureUnits.TORR.getAreaUnits());
	}

	@Test
	void testGetPressureUnits() {
		assertTrue(PressureUnits.PASCAL == PressureUnits.BARYE.getPressureUnits());
		assertTrue(null == PressureUnits.PSI.getPressureUnits());
		assertTrue(null == PressureUnits.ATMOSPHERE.getPressureUnits());
		assertTrue(null == PressureUnits.PASCAL.getPressureUnits());
		assertTrue(null == PressureUnits.BAR.getPressureUnits());
		assertTrue(null == PressureUnits.TORR.getPressureUnits());
	}

	@Test
	void testGetSIFactor() {
		assertTrue(SIUnits.DECI == PressureUnits.BARYE.getSIFactor());
		assertTrue(null == PressureUnits.PSI.getSIFactor());
		assertTrue(null == PressureUnits.ATMOSPHERE.getSIFactor());
		assertTrue(null == PressureUnits.PASCAL.getSIFactor());
		assertTrue(null == PressureUnits.BAR.getSIFactor());
		assertTrue(null == PressureUnits.TORR.getSIFactor());
	}

	@Test
	void testIsSI() {
		assertTrue(PressureUnits.PASCAL.isSI());
		assertFalse(PressureUnits.ATMOSPHERE.isSI());
		assertFalse(PressureUnits.PSI.isSI());
		assertFalse(PressureUnits.BARYE.isSI());
		assertFalse(PressureUnits.BAR.isSI());
		assertFalse(PressureUnits.TORR.isSI());
	}

	@Test
	void testChangeUnits() throws InvalidInputException {
		original = 36.6;
		changer = PressureUnits.PASCAL;
		assertEquals(original, changer.changeUnits(original, units[0]), delta);
		assertEquals(0.000366, changer.changeUnits(original, units[1]), delta);
		assertEquals(0.0003612139156181, changer.changeUnits(original, units[2]), delta);
		assertEquals(0.2745225758697, changer.changeUnits(original, units[3]), delta);
		assertEquals(0.005308381200918, changer.changeUnits(original, units[4]), delta);
		assertEquals(366, changer.changeUnits(original, units[5]), delta);
		
		original = 11.2;
		changer = PressureUnits.ATMOSPHERE;
		assertEquals(1134840, changer.changeUnits(original, units[0]), delta);
		assertEquals(11.3484, changer.changeUnits(original, units[1]), delta);
		assertEquals(original, changer.changeUnits(original, units[2]), delta);
		assertEquals(8511.999999998, changer.changeUnits(original, units[3]), delta);
		assertEquals(164.5946262855, changer.changeUnits(original, units[4]), delta);
		assertEquals(11348400, changer.changeUnits(original, units[5]), delta);
	}

	@Test
	void testToString() {
		assertEquals("Pa", PressureUnits.PASCAL.toString());
		assertEquals("atm", PressureUnits.ATMOSPHERE.toString());
		assertEquals("psi", PressureUnits.PSI.toString());
		assertEquals("dyne/sqcm", PressureUnits.BARYE.toString());
		assertEquals("bar", PressureUnits.BAR.toString());
		assertEquals("torr", PressureUnits.TORR.toString());
	}

}
