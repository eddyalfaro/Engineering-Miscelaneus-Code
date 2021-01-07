package properties;

import constants.AreaUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.abstracts.SingleUnitProperty;

public class Area extends SingleUnitProperty<AreaUnits>{

	private Area(double value, AreaUnits unit) {
		super(value, unit);
	}
	
	private Area(double value, SIUnits factor, AreaUnits unit) {
		super(value, factor, unit);
	}
	
	public static Area setAt(double value, AreaUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Area(value, unit);
	}
	
	public static Area setAt(double value, SIUnits factor, AreaUnits unit) throws InvalidInputException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (!(unit.isSI())){
			throw new SIFactorException(ERROR2);
		}
		return new Area(value, factor, unit);
	}
	
	public static Area getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, AreaUnits.getISU());
	}
	
	public static Area getInSIUnits(Area area) throws InvalidInputException, SIFactorException {
		if (area == null) {
			throw new NullPointerException();
		}
		return area.changeUnits(AreaUnits.getISU());
	}
	
	public void addSIFactor(SIUnits factor) throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		this.hasSIFactor = true;
		this.factor = factor;
		
		this.value = factor.addSIFactor(value, AreaUnits.LENGHT_DIMENSION_EXPONENT);
	}
	
	public void removeSIFactor() throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR3);
		}
		hasSIFactor = false;
		value = factor.removeSIFactor(value, AreaUnits.LENGHT_DIMENSION_EXPONENT);
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
	public double getValueIn(AreaUnits units) throws InvalidInputException, SIFactorException {
		SIUnits temp = null;
		if (hasSIFactor()) {
			temp = (SIUnits) factor;
			removeSIFactor();
		}
		
		double result = super.unit.changeUnits(getValue(), units);
		
		if (temp != null) {
			addSIFactor(temp);	
		}
		
		return result;
	}

	@Override
	public Area changeUnits(AreaUnits newUnits)	throws InvalidInputException, SIFactorException {
		double tempVal = this.getValueIn(newUnits);
		return setAt(tempVal, newUnits);
	}

	@Override
	public int compareTo(SingleUnitProperty<AreaUnits> o) {
		if (!(o instanceof Area)) {
			return INPUT_EXCEPTION;
		}
		Double tempThis = null;
		Double tempO = null;
		
		try {
			tempThis = this.getValueIn(AreaUnits.getISU());
			tempO = o.getValueIn(AreaUnits.getISU());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} 
		
		if (tempO == null || tempThis == null) {
			return NULL_EXCEPTION;
		}
		
		return tempThis.compareTo(tempO);
	}

}
