package properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.AreaUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;

class AreaTest {

	Area initial;
	double initialValue;
	AreaUnits initialUnit;
	SIUnits factor;
	double delta = 1e-3;
	static int testNum = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		testNum++;
		initialValue = 120;
		initialUnit = AreaUnits.SQMETER;
		factor = SIUnits.KILO;
		initial = Area.setAt(initialValue, initialUnit);
	}

	@Test
	void testToString() throws SIFactorException, NonSIException, InvalidInputException {
		System.out.println("Test #" + testNum);
		
		System.out.println(initial);
		
		initial.addSIFactor(factor);
		System.out.println(initial);
		
		initial.removeSIFactor();
		System.out.println(initial);
		
		initial.addSIFactor(factor);
		initial.replaceSIFactor(SIUnits.CENTI);
		System.out.println(initial);
		
		initial = initial.changeUnits(AreaUnits.SQFEET);
		System.out.println(initial);
		
		initial = initial.changeUnits(AreaUnits.SQYARD);
		System.out.println(initial);
		
		initial = initial.changeUnits(AreaUnits.HECTARE);
		System.out.println(initial);
		
		initial = initial.changeUnits(AreaUnits.ACRE);
		System.out.println(initial);
		
		initial = Area.getInSIUnits(initial);
		System.out.println(initial);
		
		initial.addSIFactor(factor);
		System.out.println(initial);
		
		initial = Area.getInSIUnits(initial);
		System.out.println(initial);
		
	}

	@Test
	void testAddSIFactor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testRemoveSIFactor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testReplaceSIFactor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetValueInAreaUnits() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented"); // TODO
	}

}
