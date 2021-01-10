package properties;

import constants.SIUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.abstracts.PropertyOne;

public class Volume extends PropertyOne<VolumeUnits>{
	
	private Volume(double value, VolumeUnits unit) {
		super(value, unit);
	}

	private Volume(double value, SIUnits factor, VolumeUnits unit) {
		super(value, factor, unit);
	}
	
	public static Volume setAt(double value, VolumeUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Volume(value, unit);
	}
	
	public static Volume setAt(double value, SIUnits factor, VolumeUnits unit) throws InvalidInputException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (!(unit.isSI())){
			throw new SIFactorException(ERROR2);
		}
		return new Volume(value, factor, unit);
	}
	
	public static Volume getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, VolumeUnits.getISU());
	}
	
	public static Volume getInSIUnits(Volume vol) throws InvalidInputException, SIFactorException {
		if (vol == null) {
			throw new NullPointerException();
		}
		return vol.changeUnits(VolumeUnits.getISU());
	}
	
	public void addSIFactor(SIUnits factor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new NonSIException(ERROR2);
		}
		this.hasSIFactor = true;
		this.factor = factor;
		
		this.value = factor.addSIFactor(value, VolumeUnits.LENGHT_DIMENSION_EXPONENT);
	}
	
	public void removeSIFactor() throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR3);
		}
		hasSIFactor = false;
		value = factor.removeSIFactor(value, VolumeUnits.LENGHT_DIMENSION_EXPONENT);
		factor = null;
	}
	
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR4);
		}
		removeSIFactor();
		addSIFactor(newFactor);
	}

	@Override
	public int compareTo(PropertyOne<VolumeUnits> o) {
		if (!(o instanceof Volume)) {
			return INPUT_EXCEPTION;
		}
		Double tempThis = null;
		Double tempO = null;
		
		try {
			tempThis = this.getValueIn(VolumeUnits.getISU());
			tempO = o.getValueIn(VolumeUnits.getISU());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} 
		
		if (tempO == null || tempThis == null) {
			return NULL_EXCEPTION;
		}
		
		return tempThis.compareTo(tempO);
	}

	@Override
	public double getValueIn(VolumeUnits units) throws InvalidInputException, SIFactorException {
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
	public Volume changeUnits(VolumeUnits newUnits) throws InvalidInputException, SIFactorException {
		double tempVal = this.getValueIn(newUnits);
		return setAt(tempVal, newUnits);
	}

}
