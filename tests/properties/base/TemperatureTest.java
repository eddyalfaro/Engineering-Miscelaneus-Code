package properties.base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.TemperatureUnits;
import exceptions.InvalidInputException;

class TemperatureTest {

	Temperature initial;
	double initialValue;
	TemperatureUnits initialUnit;
	double delta = 1e-3;
	static int testNum = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		testNum++;
		initialValue = 25;
		initialUnit = TemperatureUnits.CELCIUS;
		initial = Temperature.setAt(initialValue, initialUnit);
	}

	@Test
	void testGetInSIUnitsDoubleTemperatureUnits() throws InvalidInputException {
		System.out.println("Test #" + testNum);
		initial = Temperature.getInSIUnits(initialValue, TemperatureUnits.CELCIUS);
		double result = TemperatureUnits.CELCIUS.changeUnits(initialValue, TemperatureUnits.getISU());
		assertEquals(result, initial.getValue());
		assertEquals(String.format("%.3f%s", result, TemperatureUnits.getISU().toString()), initial.toString());
		System.out.println(initial);
		
		initial = Temperature.getInSIUnits(initialValue, TemperatureUnits.FARENHEIT);
		result = TemperatureUnits.FARENHEIT.changeUnits(initialValue, TemperatureUnits.getISU());
		assertEquals(result, initial.getValue());
		assertEquals(String.format("%.3f%s", result, TemperatureUnits.getISU().toString()), initial.toString());
		System.out.println(initial);
		
		initial = Temperature.getInSIUnits(initialValue, TemperatureUnits.RANKINE);
		result = TemperatureUnits.RANKINE.changeUnits(initialValue, TemperatureUnits.getISU());
		assertEquals(result, initial.getValue());
		assertEquals(String.format("%.3f%s", result, TemperatureUnits.getISU().toString()), initial.toString());
		System.out.println(initial);
		System.out.println();
	}

	@Test
	void testGetInSIUnitsTemperature() throws InvalidInputException {
		System.out.println("Test #" + testNum);
		initial = Temperature.getInSIUnits(initial);
		double result = initialUnit.changeUnits(initialValue, TemperatureUnits.getISU());
		assertEquals(result, initial.getValue());
		assertEquals(String.format("%.3f%s", result, TemperatureUnits.getISU().toString()), initial.toString());
		System.out.println(initial);
		System.out.println();
	}

	@Test
	void testSetTempAt() {
		System.out.println("Test #" + testNum);
		assertEquals(initialValue, initial.getValue());
		assertEquals(String.format("%.3f%s", initialValue, initialUnit), initial.toString());
		System.out.println(initial);
		System.out.println();
	}

	@Test
	void testGetValueInTemperatureUnits() throws InvalidInputException {
		System.out.println("Test #" + testNum);
		double result = initial.getValueIn(TemperatureUnits.CELCIUS);
		assertEquals(initialValue, result);
		
		result = initial.getValueIn(TemperatureUnits.FARENHEIT);
		assertEquals(initialUnit.changeUnits(initialValue, TemperatureUnits.FARENHEIT), result);
		
		result = initial.getValueIn(TemperatureUnits.KELVIN);
		assertEquals(initialUnit.changeUnits(initialValue, TemperatureUnits.KELVIN), result);
		
		result = initial.getValueIn(TemperatureUnits.RANKINE);
		assertEquals(initialUnit.changeUnits(initialValue, TemperatureUnits.RANKINE), result);
		System.out.println("No print out");
		System.out.println();
	}

	@Test
	void testChangeUnitsTemperatureUnits() throws InvalidInputException {
		System.out.println("Test #" + testNum);
		Temperature temp = initial;
		initial = initial.changeUnits(TemperatureUnits.CELCIUS);
		assertFalse(temp == initial);
		assertEquals(initialValue, initial.getValue());
		System.out.println(initial);
		
		temp = initial;
		initial = initial.changeUnits(TemperatureUnits.FARENHEIT);
		assertFalse(temp == initial);
		assertEquals(initialUnit.changeUnits(initialValue, TemperatureUnits.FARENHEIT), initial.getValue());
		System.out.println(initial);
		
		temp = initial;
		initial = initial.changeUnits(TemperatureUnits.KELVIN);
		assertFalse(temp == initial);
		assertEquals(initialUnit.changeUnits(initialValue, TemperatureUnits.KELVIN), initial.getValue());
		System.out.println(initial);
		
		temp = initial;
		initial = initial.changeUnits(TemperatureUnits.RANKINE);
		assertFalse(temp == initial);
		assertEquals(initialUnit.changeUnits(initialValue, TemperatureUnits.RANKINE), initial.getValue());
		System.out.println(initial);
		System.out.println();
	}

	@Test
	void testCompareToPropertyOfTemperatureUnits() throws InvalidInputException {
		System.out.println("Test #" + testNum);
		Temperature temp1 = Temperature.setAt(0, TemperatureUnits.CELCIUS);
		Temperature temp2 = Temperature.setAt(32, TemperatureUnits.FARENHEIT);		
		assertEquals(0, temp1.compareTo(temp2));
		System.out.println(temp1);
		System.out.println(temp2);
		
		temp2 = Temperature.setAt(15, TemperatureUnits.KELVIN);
		assertEquals(1, temp1.compareTo(temp2));
		System.out.println(temp2);
		
		temp2 = Temperature.setAt(30, TemperatureUnits.CELCIUS);
		temp2 = temp2.changeUnits(TemperatureUnits.RANKINE);
		assertEquals(-1, temp1.compareTo(temp2));
		System.out.println(temp2);
		
		System.out.println();
	}

}
