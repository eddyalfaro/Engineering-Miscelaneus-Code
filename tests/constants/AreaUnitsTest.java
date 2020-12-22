package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class AreaUnitsTest {
	
	UnitChanger changer;
	double original = 1.0;
	AreaUnits[] units = AreaUnits.values();
	double delta = 1;

	@Test
	void testToString() {
		assertEquals("acre", AreaUnits.ACRE.toString());
		assertEquals("hectare", AreaUnits.HECTARE.toString());
		assertEquals("sqft", AreaUnits.SQFEET.toString());
		assertEquals("sqin", AreaUnits.SQINCH.toString());
		assertEquals("sqm", AreaUnits.SQMETER.toString());
		assertEquals("sqmile", AreaUnits.SQMILE.toString());
		assertEquals("sqyd", AreaUnits.SQYARD.toString());
	}

	@Test
	void testChangeUnits() throws InvalidInputException {
		changer = AreaUnits.SQMETER;		
		assertEquals(1.0, changer.changeUnits(original,units[0]), delta);
		assertEquals(1550.003, changer.changeUnits(original,units[1]), delta);
		assertEquals(10.76391, changer.changeUnits(original,units[2]), delta);
		assertEquals(1.19599, changer.changeUnits(original,units[3]), delta);
		assertEquals(3.8610e-7, changer.changeUnits(original,units[4]), delta);
		assertEquals(1e-4, changer.changeUnits(original,units[5]), delta);
		assertEquals(2.471052e-4, changer.changeUnits(original,units[6]), delta);
		
		changer = AreaUnits.SQYARD;		
		assertEquals(0.8361274, changer.changeUnits(original,units[0]), delta);
		assertEquals(1296.0, changer.changeUnits(original,units[1]), delta);
		assertEquals(9.0, changer.changeUnits(original,units[2]), delta);
		assertEquals(1.0, changer.changeUnits(original,units[3]), delta);
		assertEquals(3.8228306E-7, changer.changeUnits(original,units[4]), delta);
		assertEquals(8.361274E-5, changer.changeUnits(original,units[5]), delta);
		assertEquals(2.066114E-4, changer.changeUnits(original,units[6]), delta);
		
		changer = AreaUnits.ACRE;		
		assertEquals(4046.86, changer.changeUnits(original,units[0]), delta);
		assertEquals(6272646.0, changer.changeUnits(original,units[1]), delta);
		assertEquals(43560.04, changer.changeUnits(original,units[2]), delta);
		assertEquals(4840.004, changer.changeUnits(original,units[3]), delta);
		assertEquals(1.562501E-3, changer.changeUnits(original,units[4]), delta);
		assertEquals(0.404686, changer.changeUnits(original,units[5]), delta);
		assertEquals(1.0, changer.changeUnits(original,units[6]), delta);
		
	}
	
	@Test
	void testGetAreaUnits() {
		assertTrue(AreaUnits.SQMETER == AreaUnits.getAreaUnit(LenghtUnits.METER));
		assertTrue(AreaUnits.SQFEET == AreaUnits.getAreaUnit(LenghtUnits.FOOT));
		assertTrue(AreaUnits.SQINCH == AreaUnits.getAreaUnit(LenghtUnits.INCH));
		assertTrue(AreaUnits.SQYARD == AreaUnits.getAreaUnit(LenghtUnits.YARD));
		assertTrue(AreaUnits.SQMILE == AreaUnits.getAreaUnit(LenghtUnits.MILE));
	}
}
