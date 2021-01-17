package properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.MassUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.base.Mass;

class MassTest {
	
	Mass initial;
	double initialValue;
	MassUnits initialUnit;
	SIUnits factor;
	double delta = 1e-3;
	static int testNum = 0;

	@BeforeEach
	void setUp() throws Exception {
		testNum++;
		initialValue = 800;
		initialUnit = MassUnits.KILOGRAM;
		factor = SIUnits.MILI;
		initial = Mass.setAt(initialValue, initialUnit);
	}

	@Test
	void testAddSIFactor() throws SIFactorException, InvalidInputException, NonSIException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		initial.removeSIFactor();
		assertTrue(!initial.hasSIFactor());
		System.out.println(initial);
		
		initial.addSIFactor(factor);
		System.out.println(initial);
		assertTrue(initial.hasSIFactor());
		assertEquals(800e6, initial.getValue(), delta);
		
		initial.removeSIFactor();
		assertTrue(!initial.hasSIFactor());
		
		initial.addSIFactor(SIUnits.KILO);	
		System.out.println(initial);
		assertTrue(initial.hasSIFactor());
		assertEquals(initialValue, initial.getValue(), delta);
		
		initial.removeSIFactor();
		assertTrue(!initial.hasSIFactor());
		
		initial.addSIFactor(SIUnits.MEGA);
		assertTrue(initial.hasSIFactor());
		System.out.println(initial);
		assertEquals(800e-3, initial.getValue(), delta);
		
		initial = Mass.setAt(initialValue, MassUnits.POUND);
		System.out.println(initial);
		assertTrue(!initial.hasSIFactor());
		assertThrows(SIFactorException.class, () -> initial.removeSIFactor());
		System.out.println();
	}

	@Test
	void testRemoveSIFactor() throws SIFactorException, InvalidInputException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		initial.removeSIFactor();
		assertTrue(!initial.hasSIFactor());
		assertEquals(800e3, initial.getValue(), delta);
		System.out.println(initial);
		
		initial = Mass.setAt(initialValue, MassUnits.POUND);
		assertThrows(SIFactorException.class, () -> initial.removeSIFactor());
		System.out.println(initial);
		
		initial = Mass.setAt(initialValue, SIUnits.MILI, MassUnits.GRAM);
		assertTrue(initial.hasSIFactor());
		System.out.println(initial);
		
		initial.removeSIFactor();
		assertTrue(!initial.hasSIFactor());
		assertEquals(800e-3, initial.getValue(), delta);
		System.out.println(initial);
		System.out.println();
	}

	@Test
	void testReplaceSIFactor() throws SIFactorException, NonSIException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		initial.replaceSIFactor(SIUnits.MEGA);
		System.out.println(initial);
		assertEquals(800e-3, initial.getValue(), delta);
		
		initial.replaceSIFactor(SIUnits.KILO);
		System.out.println(initial);
		assertEquals(initialValue, initial.getValue(), delta);
		
		initial.removeSIFactor();
		System.out.println(initial);
		assertEquals(800e3, initial.getValue(), delta);
		
		initial.addSIFactor(SIUnits.MEGA);
		assertEquals(800e-3, initial.getValue(), delta);
		System.out.println(initial);
		System.out.println();
	}

	@Test
	void testGetInSIUnitsDouble() throws InvalidInputException, SIFactorException, NonSIException {
		System.out.println("Test #" + testNum);
		Mass temp = Mass.getInSIUnits(initialValue);
		System.out.println(temp);
		
		assertEquals("800.000kg", temp.toString());
		assertTrue(temp.hasSIFactor());
		
		temp.removeSIFactor();
		System.out.println(temp);
		assertTrue(!temp.hasSIFactor());
		assertEquals(800e3, temp.getValue(), delta);
		System.out.println(temp);
		
		temp.addSIFactor(SIUnits.MILI);
		assertTrue(temp.hasSIFactor());
		assertEquals(800e6, temp.getValue(), delta);
		System.out.println(temp);
		System.out.println();
	}

	@Test
	void testSetAtDoubleSIUnitsMassUnits() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		assertThrows(InvalidInputException.class, () -> Mass.setAt(initialValue, factor, initialUnit));
		assertThrows(InvalidInputException.class, () -> Mass.setAt(-10, factor, MassUnits.GRAM));
		assertThrows(InvalidInputException.class, () -> Mass.setAt(-10, factor, MassUnits.POUND));
		
		initial = Mass.setAt(900, factor, MassUnits.GRAM);
		assertTrue(initial.hasSIFactor());
		assertEquals(900, initial.getValue());
		System.out.println(initial);
		
		initial.removeSIFactor();
		System.out.println(initial);
		assertTrue(!initial.hasSIFactor());
		assertEquals(900e-3, initial.getValue(), delta);
		System.out.println();
	}

	@Test
	void testGetValueInMassUnits() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		assertEquals(initialValue, initial.getValueIn(MassUnits.KILOGRAM), delta);
		assertEquals(800e3, initial.getValueIn(MassUnits.GRAM), delta);
		assertEquals(0.88184905, initial.getValueIn(MassUnits.ENGLISH_TON), delta);
		assertEquals(0.8, initial.getValueIn(MassUnits.METRIC_TON), delta);
		assertEquals(1763.6981, initial.getValueIn(MassUnits.POUND), delta);
		assertEquals(54.81741, initial.getValueIn(MassUnits.SLUG), delta);
		System.out.println("No Print out");
		System.out.println();
	}

	@Test
	void testChangeUnitsMassUnits() throws InvalidInputException, SIFactorException {
		System.out.println("Test #" + testNum);
		System.out.println(initial);
		
		Mass temp = initial.changeUnits(MassUnits.SLUG);
		System.out.println(temp);
		assertEquals(54.81741, temp.getValue(), delta);
		
		temp = initial.changeUnits(MassUnits.GRAM);
		System.out.println(temp);
		assertEquals(800e3, temp.getValue(), delta);
		
		temp = initial.changeUnits(MassUnits.ENGLISH_TON);
		System.out.println(temp);
		assertEquals(0.88184905, temp.getValue(), delta);
		
		temp = initial.changeUnits(MassUnits.METRIC_TON);
		System.out.println(temp);
		assertEquals(0.8, temp.getValue(), delta);
		
		temp = initial.changeUnits(MassUnits.POUND);
		System.out.println(temp);
		assertEquals(1763.6981, temp.getValue(), delta);
		System.out.println();
	}

	@Test
	void testCompareTo() throws InvalidInputException, SIFactorException {
		System.out.println("Test CompareTo #" + testNum);
		
		Mass temp1 = Mass.setAt(2, MassUnits.GRAM);
		Mass temp2 = Mass.setAt(250, MassUnits.POUND);
		System.out.println(temp1);
		System.out.println(temp2);
		
		int result = temp1.compareTo(temp2);
		assertEquals(-1, result);
		
		result = temp2.compareTo(temp1);
		assertEquals(1, result);
		System.out.println();
	}

}
