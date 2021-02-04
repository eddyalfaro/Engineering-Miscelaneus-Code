package properties.base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.AreaUnits;
import constants.SIUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;

class VolumeTest {

	Volume initial;
	double initialValue;
	VolumeUnits initialUnit;
	SIUnits factor;
	double delta = 1e-3;
	static int testNum = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		testNum++;
		initialValue = 120;
		initialUnit = VolumeUnits.CUBICMETER;
		factor = SIUnits.KILO;
		initial = Volume.setAt(initialValue, initialUnit);
	}

	@Test
	void testToString() throws SIFactorException, NonSIException, InvalidInputException {
		System.out.println("Test #" + testNum);
		
		System.out.println(initial);
		
		initial.addSIFactor(SIUnits.CENTI);
		System.out.println(initial);
		
		initial = initial.changeUnits(VolumeUnits.OIL_BARREL);
		System.out.println(initial);
		
		initial = initial.changeUnits(VolumeUnits.LITER);
		System.out.println(initial);
		
		initial.addSIFactor(SIUnits.MILI);
		System.out.println(initial);
	}

	@Test
	void testPrintUnits() {
		fail("Not yet implemented"); // TODO
	}

}
