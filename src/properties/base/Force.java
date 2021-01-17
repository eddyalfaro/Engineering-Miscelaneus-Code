package properties.base;

import constants.ForceUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;

public class Force extends PropertyOne<ForceUnits>{

	private Force(double value , ForceUnits unit) {
		super(value, unit);
	}
	
	private Force(double value, SIUnits factor, ForceUnits unit) {
		super(value, factor, unit);
	}
	
	public static Force setAt(double value, ForceUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Force(value, unit);
	}
	
	public static Force setAt(double value, SIUnits factor, ForceUnits unit) throws InvalidInputException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (!(unit.isSI())){
			throw new SIFactorException(ERROR2);
		}
		return new Force(value, factor, unit);
	}
	
	public static Force getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, ForceUnits.getISU());
	}
	
	public static Force getInSIUnits(Force vol) throws InvalidInputException, SIFactorException {
		if (vol == null) {
			throw new NullPointerException();
		}
		return vol.changeUnits(ForceUnits.getISU());
	}

	@Override
	public int compareTo(PropertyOne<ForceUnits> o) {
		if (!(o instanceof Force)) {
			return INPUT_EXCEPTION;
		}
		Double tempThis = null;
		Double tempO = null;
		
		try {
			tempThis = this.getValueIn(ForceUnits.getISU());
			tempO = o.getValueIn(ForceUnits.getISU());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} 
		
		if (tempO == null || tempThis == null) {
			return NULL_EXCEPTION;
		}
		
		return tempThis.compareTo(tempO);
	}

	@Override
	public double getValueIn(ForceUnits units) throws InvalidInputException, SIFactorException {
		SIUnits temp = null;
		if (hasSIFactor()) {
			temp = (SIUnits) factor;
			removeSIFactor();
		}
		
		double result = super.unit.changeUnits(getValue(), units);
		
		if (temp != null) {
			try {
				addSIFactor(temp);
			} catch (NonSIException e) {
				e.printStackTrace();
			}	
		}
		
		return result;
	}

	@Override
	public Force changeUnits(ForceUnits newUnits) throws InvalidInputException, SIFactorException {
		double tempVal = this.getValueIn(newUnits);
		return setAt(tempVal, newUnits);
	}

}
