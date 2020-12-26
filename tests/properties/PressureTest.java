package properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.PressureUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;

class PressureTest {
	
	Pressure initial;
	double initialValue;
	PressureUnits initialUnit;
	SIUnits factor;
	double delta = 1e-3;
	static int testNum = 0;

	@BeforeEach
	void setUp() throws Exception {
		testNum++;
		initialValue = 150000;
		initialUnit = PressureUnits.PASCAL;
		factor = SIUnits.KILO;
		initial = Pressure.setAt(initialValue, initialUnit);
	}

	@Test
	void testGetInSIUnitsDouble() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		Pressure temp = Pressure.getInSIUnits(initialValue);
		assertEquals(initialValue, temp.getValue());
		assertEquals("150000.000Pa", temp.toString());
		assertFalse(temp.hasSIFactor());
		System.out.println(temp);
		
		assertEquals(1.480385, temp.getValueIn(PressureUnits.ATMOSPHERE), delta);
		System.out.println();
	}

	@Test
	void testGetInSIUnitsPressure() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		Pressure temp = Pressure.setAt(1.5, PressureUnits.ATMOSPHERE);
		temp = Pressure.getInSIUnits(temp);
		assertEquals(151987.5, temp.getValue(), delta);
		assertEquals("151987.500Pa", temp.toString());
		assertFalse(temp.hasSIFactor());
		System.out.println(temp);
		
		temp = Pressure.setAt(150, factor, initialUnit);
		temp = Pressure.getInSIUnits(temp);
		assertEquals(150000.0, temp.getValue(), delta);
		assertEquals("150000.000Pa", temp.toString());
		assertFalse(temp.hasSIFactor());
		System.out.println(temp);
		
		System.out.println();
	}

	@Test
	void testSetAtDoublePressureUnits() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		Pressure temp = Pressure.setAt(initialValue, initialUnit);
		assertEquals(initialValue, temp.getValue());
		assertEquals("150000.000Pa", temp.toString());
		assertFalse(temp.hasSIFactor());
		System.out.println(temp);
		
		assertEquals(1.480385, temp.getValueIn(PressureUnits.ATMOSPHERE), delta);
		System.out.println();
	}

	@Test
	void testSetAtDoubleSIUnitsPressureUnits() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		Pressure temp = Pressure.setAt(150, factor, initialUnit);
		assertEquals(150, temp.getValue());
		assertEquals("150.000kPa", temp.toString());
		assertTrue(temp.hasSIFactor());
		System.out.println(temp);
		
		assertEquals(1.480385, temp.getValueIn(PressureUnits.ATMOSPHERE), delta);
		assertEquals(150000.00, temp.getValueIn(initialUnit));
		System.out.println();
	}

	@Test
	void testChangeUnitsPressureUnits() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		Pressure temp = Pressure.setAt(132.43, factor, initialUnit);
		
		Pressure test = temp.changeUnits(PressureUnits.ATMOSPHERE);
		assertEquals(1.306982, test.getValue(), delta);
		assertEquals("1.307atm", test.toString());
		assertFalse(test.hasSIFactor());
		System.out.println(test);
		
		test = temp.changeUnits(PressureUnits.PSI);
		assertEquals(19.20735, test.getValue(), delta);
		assertEquals("19.208psi", test.toString());
		assertFalse(test.hasSIFactor());
		System.out.println(test);
		
		test = temp.changeUnits(PressureUnits.BARYE);
		System.out.println(test);
		System.out.println(temp);
		System.out.println();		
	}

	@Test
	void testGetValueInPressureUnits() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		Pressure temp = Pressure.setAt(12, PressureUnits.ATMOSPHERE);
		double value = temp.getValueIn(PressureUnits.ATMOSPHERE);
		System.out.println(temp.changeUnits(PressureUnits.ATMOSPHERE));
		assertEquals(12.0, value, delta);
		
		value = temp.getValueIn(PressureUnits.BAR);
		System.out.println(temp.changeUnits(PressureUnits.BAR));
		assertEquals(12.159, value, delta);
		
		value = temp.getValueIn(PressureUnits.BARYE);
		System.out.println(temp.changeUnits(PressureUnits.BARYE));
		assertEquals(12159000.00, value, delta);
		
		value = temp.getValueIn(PressureUnits.PSI);
		System.out.println(temp.changeUnits(PressureUnits.PSI));
		assertEquals(176.3514, value, delta);

		value = temp.getValueIn(PressureUnits.PASCAL);
		System.out.println(temp.changeUnits(PressureUnits.PASCAL));
		assertEquals(1215900.0, value, delta);
		
		value = temp.getValueIn(PressureUnits.TORR);
		System.out.println(temp.changeUnits(PressureUnits.TORR));
		assertEquals(9120.0, value, delta);
		System.out.println();
	}

	@Test
	void testCompareTo() throws InvalidInputException {
		System.out.println("Test #" + testNum);
		Pressure temp1 = Pressure.setAt(12, initialUnit);
		Pressure temp2 = Pressure.setAt(12, SIUnits.KILO, initialUnit);
		
		int result = temp1.compareTo(temp2);
		assertEquals(-1, result);
		
		result = temp2.compareTo(temp1);
		assertEquals(1, result);
		
		temp2 = Pressure.setAt(12, initialUnit);
		result = temp1.compareTo(temp2);
		
		temp1 = Pressure.setAt(150, PressureUnits.ATMOSPHERE);
		result = temp2.compareTo(temp1);
		assertEquals(-1, result);
		
		result = temp1.compareTo(temp2);
		assertEquals(1, result);
		
		temp2 = null;
		result = temp1.compareTo(temp2);
		assertEquals(-111, result);
		System.out.println("No Print Out");
		System.out.println();
	}

	@Test
	void testAddSIFactor() throws SIFactorException, InvalidInputException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		Pressure temp = Pressure.getInSIUnits(initial);
		initial.addSIFactor(factor);
		System.out.println(temp);
		
		assertEquals(150.0, initial.getValue(), delta);
		assertThrows(SIFactorException.class, () -> initial.addSIFactor(SIUnits.CENTI));
		System.out.println();
	}

	@Test
	void testRemoveSIFactor() throws SIFactorException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		initial.addSIFactor(factor);
		assertEquals(150.0, initial.getValue(), delta);
		System.out.println(initial);
		
		initial.removeSIFactor();
		assertEquals(initialValue, initial.getValue(), delta);
		System.out.println(initial);
		
		assertThrows(SIFactorException.class, () -> initial.removeSIFactor());
		System.out.println();
	}

	@Test
	void testReplaceSIFactor() throws SIFactorException, NonSIException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		assertThrows(SIFactorException.class, () -> initial.replaceSIFactor(factor));
		
		initial.addSIFactor(factor);
		System.out.println(initial);
		assertEquals(150.0, initial.getValue(), delta);
		
		initial.replaceSIFactor(SIUnits.MEGA);
		System.out.println(initial);
		assertEquals(0.150, initial.getValue(), delta);
		
		initial.replaceSIFactor(SIUnits.MILI);
		System.out.println(initial);
		assertEquals(150e6, initial.getValue(), delta);
		System.out.println();
	}

}
