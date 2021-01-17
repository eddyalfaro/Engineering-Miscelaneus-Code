package properties.base;

import constants.PressureUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;

public class Pressure extends PropertyOne<PressureUnits>{	
			
	private Pressure(double value, PressureUnits unit) {
		super(value, unit);
	}
	
	private Pressure(double value, SIUnits factor, PressureUnits unit) {
		super(value, factor, unit);
	}
	
	public static Pressure getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, PressureUnits.getISU());
	}
	
	public static Pressure getInSIUnits(Pressure pressure) throws InvalidInputException, SIFactorException {
		if (pressure == null) {
			throw new NullPointerException();
		}
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
		if (!(unit.isSI())) {
			throw new InvalidInputException(ERROR2);
		}
		return new Pressure(value, factor, unit);	
	}
	
	public void addSIFactor(SIUnits factor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new NonSIException(ERROR2);
		}
		super.addSIFactor(factor);
	}
	
	public void removeSIFactor() throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		super.removeSIFactor();
	}
	
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		super.replaceSIFactor(newFactor);
	}
		
	@Override
	public Pressure changeUnits(PressureUnits newUnit) throws InvalidInputException, SIFactorException {
		double newValue = this.getValueIn(newUnit);
		return setAt(newValue, newUnit);
	}

	@Override
	public double getValueIn(PressureUnits units) throws InvalidInputException, SIFactorException{		
		SIUnits temp = (SIUnits) factor;
		if (hasSIFactor()) {
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
	public int compareTo(PropertyOne<PressureUnits> o) {
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
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} catch (NullPointerException e) {
			return NULL_EXCEPTION;
		}
		
		return result;
	}
	
}
