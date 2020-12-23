package properties;

import constants.SIUnits;
import constants.TemperatureUnits;
import exceptions.InvalidInputException;
import exceptions.SIFactorException;
import properties.abstracts.SingleUnitProperty;

public class Temperature extends SingleUnitProperty<TemperatureUnits>{
	
	public static final Temperature OIL_SC_FARENHEIT = new Temperature(60.0, TemperatureUnits.FARENHEIT);
	public static final Temperature STP_SC_FARENHEIT = new Temperature(32.0, TemperatureUnits.FARENHEIT);
	
	private static final String ERROR1 = "Invalid temperature value";
		
	private Temperature(double value, TemperatureUnits unit) {
		super(value, unit);
	}
	
	public static Temperature getInSIUnits(double value, TemperatureUnits units) throws InvalidInputException {
		return setAt(value, units).changeUnits(TemperatureUnits.getISU());
	}
	
	public static Temperature getInSIUnits(Temperature temp) throws InvalidInputException {
		return temp.changeUnits(TemperatureUnits.getISU());
	}
	
	public static Temperature setAt(double value, TemperatureUnits units) throws InvalidInputException {
		if (units.changeUnits(value, TemperatureUnits.KELVIN) <= 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Temperature(value, units);
	}
	
	public Temperature getInSIUnits() {
		Temperature temp = null;
		
		try {
			temp = getInSIUnits(this);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public double getValueIn(TemperatureUnits units) throws InvalidInputException {
		double value = getValue();		
		double result = super.unit.changeUnits(value, units);				
		return result;
	}

	@Override
	public Temperature changeUnits(TemperatureUnits newUnits) throws InvalidInputException {
		double newValue = getValueIn(newUnits);
		return setAt(newValue, newUnits);
	}
	
	public void addSIFactor(SIUnits factor) {
		return;
	}
	
	public void removeSIFactor() {
		return;
	}
	
	public void replaceSIFactor(SIUnits newFactor) {
		return;
	}

	@Override
	public int compareTo(SingleUnitProperty<TemperatureUnits> o) {
		Double thisValue = this.getValue();
		Double oValue = null;
		int result;
		
		try {
			oValue = o.getValueIn(super.unit);
			result = thisValue.compareTo(oValue);
		} catch (InvalidInputException|SIFactorException e) {
			result = INPUT_EXCEPTION;
		} catch (NullPointerException e) {
			result = NULL_EXCEPTION;
		}
		
		return result;
	}

}
