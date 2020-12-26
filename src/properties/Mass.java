package properties;

import constants.MassUnits;
import constants.SIUnits;

import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;

import properties.abstracts.SingleUnitProperty;

public class Mass extends SingleUnitProperty<MassUnits>{
	
	private static final String ERROR1 = "Use alternative method, Kilogram cannot have SI Factor";
	private static final String ERROR2 = "Negative mass";
	
	private Mass(double value, MassUnits unit) {
		super(value, unit);
	}
	
	private Mass(double value, SIUnits factor, MassUnits unit) {
		super(value, factor, unit);
	}
	
	public static Mass getInSIUnits(double value) throws InvalidInputException{
		return setAt(value, MassUnits.getISU());
	}
	
	public static Mass setAt(double value, MassUnits isu) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR2);
		}
		Mass temp = new Mass(value, isu);
		if (isu == MassUnits.KILOGRAM) {
			temp.hasSIFactor = true;
		}		
		return temp;
	}
	
	public static Mass setAt(double value, SIUnits factor, MassUnits isu) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR2);
		}
		if (isu != MassUnits.GRAM) {
			throw new InvalidInputException("NonISU");
		}
		if (isu == MassUnits.KILOGRAM) {
			throw new InvalidInputException(ERROR1);
		}
		return new Mass(value, factor, isu);
	}

	//TODO test
	public void addSIFactor(SIUnits factor) throws SIFactorException {
		if (!(unit == MassUnits.KILOGRAM || unit == MassUnits.GRAM)) {
			throw new SIFactorException("Factor cannot be added to non IS Unit " + unit);
		}
		if (unit == MassUnits.GRAM && factor == SIUnits.KILO) {
			try {
				this.value = unit.changeUnits(value, MassUnits.KILOGRAM);
				unit = MassUnits.KILOGRAM;
				factor = null;
				hasSIFactor = true;
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}			
		}else {
			super.addSIFactor(factor);
		}
	}
	
	//TODO test
	public void removeSIFactor() throws SIFactorException {
		if (!(unit == MassUnits.KILOGRAM || unit == MassUnits.GRAM)) {
			throw new SIFactorException("NonSIUnit");
		} 
		
		if (unit == MassUnits.KILOGRAM) {
			try {
				this.value = unit.changeUnits(value, MassUnits.GRAM);
				this.factor = null;
				//System.out.println("changing hasSIFactor");
				this.hasSIFactor = false;
				this.unit = MassUnits.GRAM;
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}			
		}else if (unit == MassUnits.GRAM) {
			super.removeSIFactor();
		}
	}
	
	//TODO test
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorException, NonSIException {
		if (!(unit == MassUnits.KILOGRAM || unit == MassUnits.GRAM)) {
			throw new SIFactorException("Factor cannot be added to non IS Unit " + unit);
		}
		
		removeSIFactor();
		addSIFactor(newFactor);
	}

	@Override
	public double getValueIn(MassUnits units) throws InvalidInputException, SIFactorException {
		if (unit == MassUnits.KILOGRAM) {
			return unit.changeUnits(value, units);
		}
		
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
	public Mass changeUnits(MassUnits newUnits) throws InvalidInputException, 
														SIFactorException {
		double newValue = this.getValueIn(newUnits);
		return setAt(newValue, newUnits);
	}

	@Override
	public int compareTo(SingleUnitProperty<MassUnits> o) {
		Mass temp1 = null;
		Mass temp2 = null;
		Double value = null;
		int result;
		
		if (!(o instanceof Mass)) {
			return INPUT_EXCEPTION;
		}
		
		try {
			temp1 = getInSIUnits(this);			
			temp2 = getInSIUnits((Mass) o);
			value = temp1.getValue();
			result = value.compareTo(temp2.getValue());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} catch (NullPointerException e) {
			return NULL_EXCEPTION;
		}
		
		return result;
	}

	public static Mass getInSIUnits(Mass mass) throws InvalidInputException, SIFactorException{
		if (mass == null) {
			throw new NullPointerException();
		}
		return mass.changeUnits(MassUnits.getISU());
	}

}
