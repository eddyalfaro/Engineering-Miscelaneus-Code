package properties.base;

import constants.SIUnits;
import constants.TimeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;

public class Time extends PropertyOne<TimeUnits>{

	private Time(double value, TimeUnits unit) {
		super(value, unit);
	}
	
	private Time(double value, SIUnits factor, TimeUnits unit) {
		super(value, factor, unit);
	}
	
	public static Time setAt(double value, TimeUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Time(value, unit);
	}
	
	public static Time miliseconds(double value) throws InvalidInputException {
		Time temp = null;
		
		try {
			temp = setAt(value, SIUnits.MILI, TimeUnits.SECONDS);
		} catch (NonSIException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	public static Time nanoseconds(double value) throws InvalidInputException {
		Time temp = null;
		
		try {
			temp = setAt(value, SIUnits.NANO, TimeUnits.SECONDS);
		} catch (NonSIException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	public static Time setAt(double value, SIUnits factor, TimeUnits unit) throws InvalidInputException, NonSIException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (!(unit.isSI())){
			throw new NonSIException(ERROR2);
		}
		return new Time(value, factor, unit);
	}
	
	public static Time getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, TimeUnits.getISU());
	}
	
	public static Time getInSIUnits(Time vol) throws InvalidInputException, SIFactorException {
		if (vol == null) {
			throw new NullPointerException();
		}
		return vol.changeUnits(TimeUnits.getISU());
	}

	@Override
	public int compareTo(PropertyOne<TimeUnits> o) {
		if (!(o instanceof Time)) {
			return INPUT_EXCEPTION;
		}
		Double tempThis = null;
		Double tempO = null;
		
		try {
			tempThis = this.getValueIn(TimeUnits.getISU());
			tempO = o.getValueIn(TimeUnits.getISU());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} 
		
		if (tempO == null || tempThis == null) {
			return NULL_EXCEPTION;
		}
		
		return tempThis.compareTo(tempO);
	}

	@Override
	public double getValueIn(TimeUnits units) throws InvalidInputException, SIFactorException {
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
	public Time changeUnits(TimeUnits newUnits) throws InvalidInputException, SIFactorException {
		double tempVal = this.getValueIn(newUnits);
		return setAt(tempVal, newUnits);
	}

}
