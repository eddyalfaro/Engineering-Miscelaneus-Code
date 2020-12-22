package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

class TemperatureUnitsTest {
	
	UnitChanger changer;
	double original = 0.0;
	
	@Test
	void testToString() {
		assertEquals("K", TemperatureUnits.KELVIN.toString());
		assertEquals("C", TemperatureUnits.CELCIUS.toString());
		assertEquals("F", TemperatureUnits.FARENHEIT.toString());
		assertEquals("R", TemperatureUnits.RANKINE.toString());
	}

	@Test
	void testChangeUnits() throws InvalidInputException {
		changer = TemperatureUnits.KELVIN;		
		assertEquals(-273.15, changer.changeUnits(original, TemperatureUnits.CELCIUS));
		assertEquals(0.0, changer.changeUnits(original, TemperatureUnits.KELVIN));
		assertEquals(-459.67, changer.changeUnits(original, TemperatureUnits.FARENHEIT), 0.01);
		assertEquals(0.0, changer.changeUnits(original, TemperatureUnits.RANKINE), 0.01);
		
		changer = TemperatureUnits.CELCIUS;		
		assertEquals(0.0, changer.changeUnits(original, TemperatureUnits.CELCIUS));
		assertEquals(273.15, changer.changeUnits(original, TemperatureUnits.KELVIN));
		assertEquals(32.0, changer.changeUnits(original, TemperatureUnits.FARENHEIT));
		assertEquals(491.67, changer.changeUnits(original, TemperatureUnits.RANKINE));
		
		changer = TemperatureUnits.FARENHEIT;		
		assertEquals(-17.77, changer.changeUnits(original, TemperatureUnits.CELCIUS), 0.01);
		assertEquals(255.37, changer.changeUnits(original, TemperatureUnits.KELVIN), 0.01);
		assertEquals(0.0, changer.changeUnits(original, TemperatureUnits.FARENHEIT));
		assertEquals(459.67, changer.changeUnits(original, TemperatureUnits.RANKINE));
		
		changer = TemperatureUnits.RANKINE;		
		assertEquals(-273.15, changer.changeUnits(original, TemperatureUnits.CELCIUS));
		assertEquals(0.0, changer.changeUnits(original, TemperatureUnits.KELVIN));
		assertEquals(-459.67, changer.changeUnits(original, TemperatureUnits.FARENHEIT));
		assertEquals(0.0, changer.changeUnits(original, TemperatureUnits.RANKINE));
		
		original = -13.05;
		changer = TemperatureUnits.CELCIUS;		
		assertEquals(-13.05, changer.changeUnits(original, TemperatureUnits.CELCIUS), 0.01);
		assertEquals(260.10, changer.changeUnits(original, TemperatureUnits.KELVIN), 0.01);
		assertEquals(8.51, changer.changeUnits(original, TemperatureUnits.FARENHEIT), 0.01);
		assertEquals(468.18, changer.changeUnits(original, TemperatureUnits.RANKINE), 0.01);
		
		original = -13.05;
		changer = TemperatureUnits.FARENHEIT;		
		assertEquals(-25.02, changer.changeUnits(original, TemperatureUnits.CELCIUS), 0.01);
		assertEquals(248.12, changer.changeUnits(original, TemperatureUnits.KELVIN), 0.01);
		assertEquals(-13.05, changer.changeUnits(original, TemperatureUnits.FARENHEIT), 0.01);
		assertEquals(446.62, changer.changeUnits(original, TemperatureUnits.RANKINE), 0.01);
		
		original = 26.01;
		changer = TemperatureUnits.CELCIUS;		
		assertEquals(26.01, changer.changeUnits(original, TemperatureUnits.CELCIUS), 0.01);
		assertEquals(299.16, changer.changeUnits(original, TemperatureUnits.KELVIN), 0.01);
		assertEquals(78.81, changer.changeUnits(original, TemperatureUnits.FARENHEIT), 0.01);
		assertEquals(538.48, changer.changeUnits(original, TemperatureUnits.RANKINE), 0.01);
		
		original = 26.01;
		changer = TemperatureUnits.FARENHEIT;		
		assertEquals(-3.32, changer.changeUnits(original, TemperatureUnits.CELCIUS), 0.01);
		assertEquals(269.82, changer.changeUnits(original, TemperatureUnits.KELVIN), 0.01);
		assertEquals(26.01, changer.changeUnits(original, TemperatureUnits.FARENHEIT), 0.01);
		assertEquals(485.68, changer.changeUnits(original, TemperatureUnits.RANKINE), 0.01);
	}

}
