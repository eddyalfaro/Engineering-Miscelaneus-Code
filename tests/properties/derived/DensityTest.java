package properties.derived;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.AreaUnits;
import constants.MassUnits;
import constants.SIUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.base.Area;
import properties.base.Mass;
import properties.base.Volume;

class DensityTest {
	
	Density initial;
	double initialValue;
	double delta = 1e-3;
	static int testNum = 0;

	@BeforeEach
	void setUp() throws Exception {
		testNum++;
		initialValue = 1000;
		initial = Density.DENSITY.setAt(initialValue);
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
	void testToString() throws SIFactorException, NonSIException, InvalidInputException {
		System.out.println("Test #" + testNum);
		
		System.out.println(initial);
		initial.addSIFactor(SIUnits.CENTI, false, true);
		System.out.println(initial);
		
		initial.removeSIFactor(true, true);
		System.out.println(initial);
		
		initial = initial.getIn(MassUnits.POUND, VolumeUnits.US_GALLON);
		System.out.println(initial);
		
		initial = initial.getIn(MassUnits.KILOGRAM, VolumeUnits.LITER);
		System.out.println(initial);
		
		initial.addSIFactor(SIUnits.MILI, false, true);
		System.out.println(initial);
		
		initial.removeSIFactor(true, false);
		System.out.println(initial);
		
		initial = initial.getIn(MassUnits.POUND, VolumeUnits.US_GALLON);
		System.out.println(initial);
		
		Mass a = Mass.setAt(945.07, MassUnits.POUND);
		Volume b = Volume.setAt(113.25, VolumeUnits.US_GALLON);
		
		initial = Density.DENSITY.calculate(a, b);
		System.out.println(initial);
		
		initial = Density.DENSITY.setAt(12, MassUnits.GRAM, VolumeUnits.CUBICMETER, SIUnits.KILO, SIUnits.CENTI);
		System.out.println(initial);
		
		initial.removeSIFactor(true, true);
		System.out.println(initial);
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetAtDoubleMassUnitsVolumeUnits() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetAtDoubleMassUnitsVolumeUnitsSIUnitsSIUnits() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetAtDouble() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testCalculate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetValueInMassUnitsVolumeUnits() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testReplaceSIFactor() {
		fail("Not yet implemented"); // TODO
	}

}
