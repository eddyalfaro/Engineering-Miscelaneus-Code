package properties;

import constants.SIUnits;
import constants.TemperatureUnits;
import exceptions.InvalidInputException;
import properties.abstracts.Property;

public class Temperature extends Property<TemperatureUnits>{
	
	public static final Temperature OIL_SC_FARENHEIT = setTempAt(60.0, TemperatureUnits.FARENHEIT);
	public static final Temperature STP_SC_FARENHEIT = setTempAt(32.0, TemperatureUnits.FARENHEIT);
	
	private Temperature(double value, TemperatureUnits unit) {
		super(value, unit);
	}
	
	public static Temperature getInSIUnits(double value, TemperatureUnits units) throws InvalidInputException {
		return setTempAt(value, units).changeUnits(TemperatureUnits.getISU());
	}
	
	public static Temperature getInSIUnits(Temperature temp) throws InvalidInputException {
		return temp.changeUnits(TemperatureUnits.getISU());
	}
	
	public static Temperature setTempAt(double value, TemperatureUnits units) {
		return new Temperature(value, units);
	}

	@Override
	public double getValueIn(TemperatureUnits units) throws InvalidInputException {
		double value = getValue();		
		double result = super.getUnits().changeUnits(value, units);				
		return result;
	}

	@Override
	public Temperature changeUnits(TemperatureUnits newUnits) throws InvalidInputException {
		double newValue = getValueIn(newUnits);
		return setTempAt(newValue, newUnits);
	}
	
	public void addSIFactor(SIUnits factor) {
		return;
	}
	
	public void removeSIFactor() {
		return;
	}
	
	public void replaceSIFactor() {
		return;
	}

	@Override
	public int compareTo(Property<TemperatureUnits> o) {
		Double thisValue = this.getValue();
		Double oValue = o.getValue();
		
		if (this.getUnits() != o.getUnits()) {
			try {
				oValue = o.getUnits().changeUnits(oValue, this.getUnits());
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}
		
		return thisValue.compareTo(oValue);
	}

}
