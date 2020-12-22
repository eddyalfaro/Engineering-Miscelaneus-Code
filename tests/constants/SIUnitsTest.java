package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.interfaces.SIFactor;
import exceptions.NonSIFactorException;

class SIUnitsTest {
	
	SIFactor factor;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAddSIFactor() {
		double value = 1000.0;
		double result;
		factor = SIUnits.KILO;
		
		result = factor.addSIFactor(value);
		assertEquals(1.0, result);
		
		result = factor.removeSIFactor(result);
		assertEquals(value, result);
		
	}

	@Test
	void testRemoveSIFactor() {
		double value = 1000.0;
		double result;
		factor = SIUnits.KILO;
		
		result = factor.addSIFactor(value);
		result = factor.removeSIFactor(result);
		assertEquals(value, result);
	}

	@Test
	void testChangeSIFactor() throws NonSIFactorException {
		double value = 1000.0;
		double result;
		factor = SIUnits.KILO;
		
		result = factor.addSIFactor(value);
		result = factor.changeTo(result, SIUnits.MEGA);
		
		assertEquals(1e-3, result);
		
		result = SIUnits.MEGA.changeTo(result, SIUnits.KILO);
		assertEquals(1.0, result);
		
		result = SIUnits.KILO.changeTo(result, SIUnits.CENTI);
		assertEquals(1000e2, result);
		
		result = SIUnits.CENTI.removeSIFactor(result);
		assertEquals(value, result);
	}
	
	//TODO add test cases
	@Test
	void testPoweredAddSIFactor() {
		
	}
	
	@Test
	void testPoweredRemoveSIFactor() {
		
	}
	
	@Test
	void testPoweredChangeSIFactor() throws NonSIFactorException {
		
	}

}
