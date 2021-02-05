package properties.rates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.MassUnits;
import constants.SIUnits;
import constants.TimeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.base.Mass;
import properties.base.Time;

class MassRateTest {

	double delta = 1e-3;
	double result;
	static int testNum = 0;

	@BeforeEach
	void setUp() throws Exception {
		testNum++;
	}

	@Test
	void testRemoveSIFactor() throws InvalidInputException, NonSIException, SIFactorException {
		System.out.println("Test #" + testNum + " :: Remove SIFactor");
		SIUnits factor1 = SIUnits.MILI;
		SIUnits factor2 = SIUnits.MILI;
		
		MassUnits unit1 = MassUnits.GRAM;
		TimeUnits unit2 = TimeUnits.SECONDS;
		
		double value = 145.3;
		
		MassRate test1 = (MassRate) MassRate.MASS_RATE.setAt(value, unit1, unit2, factor1, factor2);
		System.out.println(test1);
		
		test1.removeSIFactor(true, false);
		assertTrue(null == test1.getFactor1());
		assertEquals(145.3e-3, test1.getValue(), delta);
		System.out.println(test1);
		
		test1.removeSIFactor(false, true);
		assertTrue(null == test1.getFactor2());
		assertEquals(145.3, test1.getValue(), delta);
		System.out.println(test1);
		
		MassRate test2 = MassRate.MASS_RATE.setAt(value);
		System.out.println(test2);
		
		test2.removeSIFactor(true, false);
		assertTrue(null == test2.getFactor1());
		assertTrue(MassUnits.GRAM == test2.getUnit1());
		assertEquals(145.3e3, test2.getValue(), delta);
		System.out.println(test2);
		
		System.out.println();
	}

	@Test
	void testSetAtDouble() throws InvalidInputException {
		System.out.println("Test #" + testNum + " :: International metric system constructor");
		
		MassUnits unit1 = MassUnits.KILOGRAM;
		TimeUnits unit2 = TimeUnits.SECONDS;
		
		double value = 145.3;
		MassRate test1 = (MassRate) MassRate.MASS_RATE.setAt(value);
		assertTrue(unit2 == test1.getUnit2());
		assertTrue(unit1 == test1.getUnit1());
		assertTrue(null == test1.getFactor1());
		assertTrue(null == test1.getFactor2());
		assertTrue(value == test1.getValue());
		
		double wValue = -1;
		assertThrows(InvalidInputException.class, () -> MassRate.MASS_RATE.setAt(wValue));
		System.out.println(test1);
		
		System.out.println();
	}

	@Test
	void testAddSIFactor() throws InvalidInputException, SIFactorException, NonSIException {
		System.out.println("Test #" + testNum + " :: Adding SIFactors");
		
		double value = 0.04;
		MassRate test1 = MassRate.MASS_RATE.setAt(value);
		System.out.print(test1 + " == ");
		
		test1.addSIFactor(SIUnits.MILI, false, true);
		System.out.print("" + test1 + '\n' +  test1 + " == ");
		assertTrue(SIUnits.MILI == test1.getFactor2());
		assertEquals(4e-5, test1.getValue(), delta);
		System.out.print("" + test1 + '\n' +  test1 + " == ");
		
		test1.removeSIFactor(true, false);
		System.out.print("" + test1 + '\n' +  test1 + " == ");
		
		test1.addSIFactor(SIUnits.MEGA, true, false);
		assertTrue(SIUnits.MEGA == test1.getFactor1());
		assertEquals(4e-8, test1.getValue(), delta);
		System.out.print("" + test1 + '\n' +  test1 + " == ");
		
		test1.removeSIFactor(false, true);
		System.out.print("" + test1 + '\n' +  test1 + " == ");
		
		test1.addSIFactor(SIUnits.HECTO, false, true);
		assertTrue(SIUnits.HECTO == test1.getFactor2());
		assertEquals(4e-3, test1.getValue(), delta);
		System.out.print("" + test1 + '\n');
		
		System.out.println();
	}

	@Test
	void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSetAtDoubleT1TimeUnits() throws InvalidInputException {
		System.out.println("Test #" + testNum + " :: General use constructor");
		
		MassUnits unit1 = MassUnits.POUND;
		TimeUnits unit2 = TimeUnits.SECONDS;
		
		double value = 145.3;
		MassRate test1 = (MassRate) MassRate.MASS_RATE.setAt(value, unit1, unit2);
		assertTrue(unit2 == test1.getUnit2());
		assertTrue(unit1 == test1.getUnit1());
		assertTrue(null == test1.getFactor1());
		assertTrue(null == test1.getFactor2());
		assertTrue(value == test1.getValue());
		
		double wValue = -1;
		assertThrows(InvalidInputException.class, () -> MassRate.MASS_RATE.setAt(wValue, unit1, unit2));
		
		System.out.println(test1);
		
		MassRate test2 = MassRate.MASS_RATE.setAt(value, unit1, unit2);
		assertTrue(unit2 == test2.getUnit2());
		assertTrue(unit1 == test2.getUnit1());
		assertTrue(null == test2.getFactor1());
		assertTrue(null == test2.getFactor2());
		assertTrue(value == test2.getValue());
		System.out.println(test2);
		
		MassUnits kunit1 = MassUnits.KILOGRAM;
		MassRate test3 = MassRate.MASS_RATE.setAt(value, kunit1, unit2);
		assertTrue(unit2 == test3.getUnit2());
		assertTrue(kunit1 == test3.getUnit1());
		assertTrue(null == test3.getFactor1());
		assertTrue(null == test3.getFactor2());
		assertTrue(value == test3.getValue());
		System.out.println(test3);	
		System.out.println();
	}

	@Test
	void testSetAtDoubleT1TimeUnitsSIUnitsSIUnits() throws InvalidInputException, NonSIException, SIFactorException {
		System.out.println("Test #" + testNum + " :: constructor with international factors");
		SIUnits factor1 = SIUnits.MILI;
		SIUnits factor2 = SIUnits.MILI;
		
		MassUnits unit1 = MassUnits.GRAM;
		TimeUnits unit2 = TimeUnits.SECONDS;
		
		double value = 145.3;
		
		MassRate test1 = (MassRate) MassRate.MASS_RATE.setAt(value, unit1, unit2, factor1, factor2);
		assertTrue(unit2 == test1.getUnit2());
		assertTrue(unit1 == test1.getUnit1());
		assertTrue(factor1 == test1.getFactor1());
		assertTrue(factor2 == test1.getFactor2());
		assertTrue(value == test1.getValue());
		
		double wValue = -1;
		assertThrows(InvalidInputException.class, () -> MassRate.MASS_RATE.setAt(wValue, unit1, unit2, factor1, factor2));
		
		MassUnits wUnit1 = MassUnits.POUND;
		assertThrows(NonSIException.class, () -> MassRate.MASS_RATE.setAt(value, wUnit1, unit2, factor1, factor2));
		
		TimeUnits wUnit2 = TimeUnits.HOURS;
		assertThrows(NonSIException.class, () -> MassRate.MASS_RATE.setAt(value, unit1, wUnit2, factor1, factor2));
		
		System.out.println(test1);
		
		MassRate test2 = MassRate.MASS_RATE.setAt(value, unit1, unit2, factor1, null);
		assertTrue(unit2 == test2.getUnit2());
		assertTrue(unit1 == test2.getUnit1());
		assertTrue(factor1 == test2.getFactor1());
		assertTrue(null == test2.getFactor2());
		assertTrue(value == test2.getValue());
		System.out.println(test2);
		
		MassUnits kunit1 = MassUnits.KILOGRAM;
		MassRate test3 = MassRate.MASS_RATE.setAt(value, kunit1, unit2, null, null);
		assertTrue(unit2 == test3.getUnit2());
		assertTrue(kunit1 == test3.getUnit1());
		assertTrue(null == test3.getFactor1());
		assertTrue(null == test3.getFactor2());
		assertTrue(value == test3.getValue());
		System.out.println(test3);	
		System.out.println();
	}

	@Test
	void testCalculate() throws InvalidInputException, NonSIException {
		System.out.println("Test #" + testNum + " :: Calculating rate from mass and time values");
		
		Mass mass = Mass.setAt(0.55, SIUnits.MILI, MassUnits.GRAM);
		Time time = Time.setAt(0.025, SIUnits.CENTI, TimeUnits.SECONDS);
		
		MassRate rate = MassRate.MASS_RATE.calculate(mass, time);
		
		assertTrue(SIUnits.MILI == rate.getFactor1());
		assertTrue(SIUnits.CENTI == rate.getFactor2());
		assertTrue(TimeUnits.SECONDS == rate.getUnit2());
		assertTrue(MassUnits.GRAM == rate.getUnit1());
		assertEquals(22.0, rate.getValue(), delta);
		System.out.println(rate);
		
		System.out.println();
	}

	@Test
	void testGetValueInT1TimeUnits() throws InvalidInputException, NonSIException, SIFactorException {
		System.out.println("Test #" + testNum + " :: Get value in different units");
		
		SIUnits factor2 = SIUnits.MILI;
		
		MassUnits unit1 = MassUnits.KILOGRAM;
		TimeUnits unit2 = TimeUnits.SECONDS;
		
		double value = 145.3;
		MassRate test1 = MassRate.MASS_RATE.setAt(value);
		result = test1.getValueIn(MassUnits.POUND, TimeUnits.SECONDS);
		assertEquals(320.3317, result, delta);
		System.out.println(test1.getValue() + " kg/s" + " == " + result + " lb/s");
		
		result = test1.getValueIn(MassUnits.KILOGRAM, TimeUnits.HOURS);
		assertEquals(523080.0, result, delta);
		System.out.println(test1.getValue() + " kg/s" + " == " + result + " kg/h");
		
		result = test1.getValueIn(MassUnits.METRIC_TON, TimeUnits.HOURS);
		assertEquals(523.0800, result, delta);
		System.out.println(test1.getValue() + " kg/s" + " == " + result + " M.ton/h");
		System.out.println();
		
		MassRate test2 = MassRate.MASS_RATE.setAt(value, MassUnits.POUND, TimeUnits.MINUTES);
		result = test2.getValueIn(MassUnits.SLUG, TimeUnits.MINUTES);
		assertEquals(4.5160, result, delta);
		System.out.println(test2.getValue() + " lb/min" + " == " + result + " slg/min");
		
		result = test2.getValueIn(MassUnits.POUND, TimeUnits.HOURS);
		assertEquals(8718.0, result, delta);
		System.out.println(test2.getValue() + " lb/min" + " == " + result + " lb/h");
		
		result = test2.getValueIn(MassUnits.SLUG, TimeUnits.HOURS);
		assertEquals(270.964, result, delta);
		System.out.println(test2.getValue() + " lb/min" + " == " + result + " slg/h");
		System.out.println();
		
		double nVal = 0.32;
		
		MassRate test3 = MassRate.MASS_RATE.setAt(nVal, unit1, unit2, null, factor2);
		result = test3.getValueIn(MassUnits.POUND, unit2);
		assertEquals(0.70547, result, delta);
		System.out.println(test3 + " == " + result + " lb/ms");
		
		result = test3.getValueIn(unit1, TimeUnits.MINUTES);
		assertEquals(19.2e3, result, delta);
		System.out.println(test3 + " == " + result + " kg/min");
		
		result = test3.getValueIn(MassUnits.METRIC_TON, TimeUnits.MINUTES);
		assertEquals(19.2, result, delta);
		System.out.println(test3 + " == " + result + " m.ton/min");
		System.out.println();
		
		MassRate test4 = MassRate.MASS_RATE.setAt(nVal, MassUnits.GRAM, unit2, SIUnits.KILO, factor2);
		result = test4.getValueIn(MassUnits.POUND, unit2);
		assertEquals(0.70547, result, delta);
		System.out.println(test4 + " == " + result + " lb/ms");
		
		result = test4.getValueIn(unit1, TimeUnits.MINUTES);
		assertEquals(19.2e3, result, delta);
		System.out.println(test4 + " == " + result + " kg/min");
		
		result = test4.getValueIn(MassUnits.METRIC_TON, TimeUnits.MINUTES);
		assertEquals(19.2, result, delta);
		System.out.println(test4 + " == " + result + " m.ton/min");
		
		System.out.println();
		
	}

	@Test
	void testGetInT1TimeUnits() {
		fail("Not yet implemented"); // TODO
	}

}
