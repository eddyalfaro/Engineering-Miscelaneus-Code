package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class TimeUnitsTest {
	
	UnitChanger changer;
	double original = 1.0;
	TimeUnits[] units = TimeUnits.values();

	@Test
	void testToString() {
		assertEquals("s", TimeUnits.SECONDS.toString());
		assertEquals("h", TimeUnits.HOURS.toString());
		assertEquals("d", TimeUnits.DAYS.toString());
		assertEquals("m", TimeUnits.MINUTES.toString());		
	}

	@Test
	void testChangeUnits() throws InvalidInputException {
		changer = TimeUnits.SECONDS;		
		assertEquals(1.0, changer.changeUnits(original,units[0]));
		assertEquals(1.0/60.0, changer.changeUnits(original,units[1]));
		assertEquals(1.0/3600, changer.changeUnits(original,units[2]));
		assertEquals(1.0/(3600*24), changer.changeUnits(original,units[3]));
		
		changer = TimeUnits.DAYS;		
		assertEquals(3600*24.0, changer.changeUnits(original,units[0]));
		assertEquals(60*24.0, changer.changeUnits(original,units[1]));
		assertEquals(24.0, changer.changeUnits(original,units[2]));
		assertEquals(1.0, changer.changeUnits(original,units[3]));
		
	}

}
