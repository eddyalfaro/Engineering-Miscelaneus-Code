package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class MassUnitsTest {

	UnitChanger changer;
	Double original = 1.0;
	Double delta = 1e-5;
	

	@Test
	void testChangeUnits() throws InvalidInputException {
		changer = MassUnits.GRAM;
		double[] gramFactors = {1.0 , 0.0022046226, 0.00006852177, 0.000001, 0.000001102311};
		MassUnits[] units = {MassUnits.GRAM, MassUnits.POUND, MassUnits.SLUG, MassUnits.METRIC_TON, MassUnits.ENGLISH_TON};
		assertEquals(gramFactors[0], changer.changeUnits(original,units[0]));
		assertEquals(gramFactors[1], changer.changeUnits(original, units[1]));
		assertEquals(gramFactors[2], changer.changeUnits(original,units[2]));
		assertEquals(gramFactors[3], changer.changeUnits(original, units[3]));
		assertEquals(gramFactors[4], changer.changeUnits(original, units[4]));
		
		changer = MassUnits.KILOGRAM;
		double[] kilogramFactors = {1.0e3 , 0.0022046226e3, 0.00006852177e3, 0.000001e3, 0.000001102311e3};
		assertEquals(kilogramFactors[0], changer.changeUnits(original,units[0]), delta);
		assertEquals(kilogramFactors[1], changer.changeUnits(original, units[1]), delta);
		assertEquals(kilogramFactors[2], changer.changeUnits(original,units[2]), delta);
		assertEquals(kilogramFactors[3], changer.changeUnits(original, units[3]), delta);
		assertEquals(kilogramFactors[4], changer.changeUnits(original, units[4]), delta);
		
		changer = MassUnits.ENGLISH_TON;
		double[] englishTonFactors = {907184.7, 2000, 62.1619, 0.9071847, 1};
		assertEquals(englishTonFactors[0], changer.changeUnits(original, units[0]));
		assertEquals(englishTonFactors[1], changer.changeUnits(original,units[1]));
		assertEquals(englishTonFactors[2], changer.changeUnits(original, units[2]));
		assertEquals(englishTonFactors[3], changer.changeUnits(original, units[3]));
		assertEquals(englishTonFactors[4], changer.changeUnits(original, units[4]));
	}

	@Test
	void testToString() {
		assertEquals("g", MassUnits.GRAM.toString());
		assertEquals("lb", MassUnits.POUND.toString());
		assertEquals("sl", MassUnits.SLUG.toString());
		assertEquals("metric ton", MassUnits.METRIC_TON.toString());
		assertEquals("english ton", MassUnits.ENGLISH_TON.toString());
		assertEquals("kg", MassUnits.KILOGRAM.toString());
	}

}
