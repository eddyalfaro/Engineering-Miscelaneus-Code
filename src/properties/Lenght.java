package properties;

import constants.LenghtUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.abstracts.PropertyOne;

public class Lenght extends PropertyOne<LenghtUnits> {
	
	private static final String ERROR_01 = "Use alternative method, centimeter cannot have SI Factor";

	private Lenght(double value, LenghtUnits unit) {
		super(value, unit);
	}
	
	private Lenght(double value, SIUnits factor, LenghtUnits unit) {
		super(value, factor, unit);
	}
	
	public static Lenght setAt(double value, LenghtUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		Lenght temp = new Lenght(value, unit);
		
		if (unit == LenghtUnits.CENTIMETER) {
			temp.hasSIFactor = true;
		}	
		
		return temp;
	}
	
	public static Lenght setAt(double value, SIUnits factor, LenghtUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (unit == LenghtUnits.CENTIMETER) {
			throw new InvalidInputException(ERROR_01);
		}
		if (!(unit.isSI())) {
			throw new InvalidInputException(ERROR2);
		}
		return new Lenght(value, factor, unit);
	}
	
	public static Lenght getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, LenghtUnits.getISU());
	}
	
	public static Lenght getInSIUnits(Lenght o) throws InvalidInputException, SIFactorException {
		if (o == null) {
			throw new NullPointerException();
		}
		return o.changeUnits(LenghtUnits.getISU());
	}
	
	public void addSIFactor(SIUnits factor) throws NonSIException, SIFactorException {
		if (!(unit == LenghtUnits.CENTIMETER || unit == LenghtUnits.METER)) {
			throw new NonSIException(ERROR2);
		}
		super.addSIFactor(factor);
	}
	
	public void removeSIFactor() throws SIFactorException {
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR3);
		}
		
		if (unit == LenghtUnits.CENTIMETER) {
			try {
				value = unit.changeUnits(value, LenghtUnits.METER);
				unit = LenghtUnits.METER;
				hasSIFactor = false;
				return;
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}
		
		super.removeSIFactor();
	}
	
	public void replaceSIFactor(SIUnits newFactor) throws NonSIException, SIFactorException {
		if (!(unit == LenghtUnits.CENTIMETER || unit == LenghtUnits.METER)) {
			throw new NonSIException(ERROR2);
		}
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR4);
		}
		
		removeSIFactor();
		addSIFactor(newFactor);
	}

	@Override
	public int compareTo(PropertyOne<LenghtUnits> o) {
		if (!(o instanceof Lenght)) {
			return INPUT_EXCEPTION;
		}
		Double tempThis = null;
		Double tempO = null;
		
		try {
			tempThis = this.getValueIn(LenghtUnits.getISU());
			tempO = o.getValueIn(LenghtUnits.getISU());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} 
		
		if (tempO == null || tempThis == null) {
			return NULL_EXCEPTION;
		}
		
		return tempThis.compareTo(tempO);
	}

	@Override
	public double getValueIn(LenghtUnits units) throws InvalidInputException, SIFactorException {
		//if (unit != LenghtUnits.getISU()) {
			//return unit.changeUnits(value, units);
		//}
		
		SIUnits temp = null;
		if (factor != null) {
			temp = (SIUnits) factor;
			removeSIFactor();
		}
		
		double val = unit.changeUnits(value, units);
		
		if (temp != null) {
			try {
				addSIFactor(temp);
			} catch (NonSIException e) {
				e.printStackTrace();
			}
		}
		
		return val;
	}

	@Override
	public Lenght changeUnits(LenghtUnits newUnits)
			throws InvalidInputException, SIFactorException {
		double newValue = this.getValueIn(newUnits);
		Lenght temp = Lenght.setAt(newValue, newUnits);		
		return temp;
	}

}
