package properties;

import constants.PressureUnits;
import constants.SIUnits;
import constants.interfaces.SIFactor;
import exceptions.InvalidInputException;
import exceptions.SIFactorExeception;

import properties.abstracts.SingleUnitProperty;

public class Pressure extends SingleUnitProperty<PressureUnits>{
	
	private static final String ERROR1 = "Negative pressure";
			
	private Pressure(double value, PressureUnits unit) {
		super(value, unit);
	}
	
	private Pressure(double value, SIUnits factor, PressureUnits unit) {
		super(value, factor, unit);
	}
	
	public static Pressure getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, PressureUnits.getISU());
	}
	
	public static Pressure getInSIUnits(Pressure pressure) throws InvalidInputException, SIFactorExeception {
		return pressure.changeUnits(PressureUnits.getISU());
	}
	
	public static Pressure setAt(double value, PressureUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Pressure(value, unit);		
	}
	
	public static Pressure setAt(double value, SIUnits factor, PressureUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Pressure(value, factor, unit);	
	}
		
	@Override
	public Pressure changeUnits(PressureUnits newUnit) throws InvalidInputException, SIFactorExeception {
		double newValue = this.getValueIn(newUnit);
		return setAt(newValue, newUnit);
	}

	@Override
	public double getValueIn(PressureUnits units) throws InvalidInputException, SIFactorExeception{		
		SIUnits temp = (SIUnits) factor;
		if (hasSIFactor()) {
			removeSIFactor();
		}
		
		double result = super.unit.changeUnits(getValue(), units);
		
		if (temp != null) {
			addSIFactor(temp);	
		}
		
		return result;
	}

	@Override
	public int compareTo(SingleUnitProperty<PressureUnits> o) {
		Pressure temp1 = null;
		Pressure temp2 = null;
		Double value = null;
		int result;
		
		if (!(o instanceof Pressure)) {
			return INPUT_EXCEPTION;
		}
		
		try {
			temp1 = getInSIUnits(this);			
			temp2 = getInSIUnits((Pressure) o);
			value = temp1.getValue();
			result = value.compareTo(temp2.getValue());
		} catch (InvalidInputException | SIFactorExeception e) {
			return INPUT_EXCEPTION;
		} catch (NullPointerException e) {
			return NULL_EXCEPTION;
		}
		
		return result;
	}
	
}
