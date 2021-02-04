package properties.base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.LenghtUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.base.Lenght;

class LenghtTest {

	Lenght initial;
	double initialValue;
	LenghtUnits initialUnit;
	SIUnits factor;
	double delta = 1e-3;
	static int testNum = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		testNum++;
		initialValue = 120;
		initialUnit = LenghtUnits.CENTIMETER;
		factor = SIUnits.KILO;
		initial = Lenght.setAt(initialValue, initialUnit);
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
	void testCompareTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testChangeUnitsLenghtUnits() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testToString() throws SIFactorException, NonSIException, InvalidInputException {
		System.out.println("Test #" + testNum);
		
		System.out.println(initial);
		
		initial.removeSIFactor();
		System.out.println(initial);
		
		initial.addSIFactor(factor);
		System.out.println(initial);
		
		initial.replaceSIFactor(SIUnits.CENTI);
		System.out.println(initial);
		
		initial = Lenght.setAt(initialValue, initialUnit);
		System.out.println(initial);
		
		Lenght temp = initial.changeUnits(LenghtUnits.METER);
		System.out.println(temp);
		
		temp = initial.changeUnits(LenghtUnits.FOOT);
		System.out.println(temp);
		
		temp = initial.changeUnits(LenghtUnits.INCH);
		System.out.println(temp);
		
		temp = initial.changeUnits(LenghtUnits.MILE);
		System.out.println(temp);
		
		temp = initial.changeUnits(LenghtUnits.YARD);
		System.out.println(temp);
		
		temp = Lenght.getInSIUnits(temp);
		System.out.println(temp);
		
		temp = initial.changeUnits(LenghtUnits.CENTIMETER);
		System.out.println(temp);
		
		temp.removeSIFactor();
		System.out.println(temp);
	}

}
