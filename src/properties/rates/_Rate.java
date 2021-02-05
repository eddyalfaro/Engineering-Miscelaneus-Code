package properties.rates;

import constants.SIUnits;
import constants.TimeUnits;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyTwo;
import properties.base.Time;

public abstract class _Rate<T1 extends UnitChanger> extends PropertyTwo<T1, TimeUnits>{
	
	protected _Rate() {
		super();
	}
	
	protected _Rate(double value, T1 unit1, TimeUnits unit2) {
		super(value, unit1, unit2);
	}
	
	protected _Rate(double value, T1 unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2) {
		super(value, unit1, unit2, factor1, factor2);
	}

	@Override
	public int compareTo(PropertyTwo<T1, TimeUnits> o) {
		Double oVal = o.getValueIn(this.getUnit1(), this.getUnit2());
		Double val = this.getValue();
		
		double res = val - oVal;
		res = Math.abs(res);
		
		if (res < DELTA) {
			return 0;
		}
		
		return val.compareTo(oVal);
	}


	@Override
	public void setProperties() throws InvalidInputException, NonSIException {		
		if (getFactor2() == null) {
			this.property2 = Time.setAt(1.0, getUnit2());
		}else {
			this.property2 = Time.setAt(1.0, getFactor2(), getUnit2());
		}
		
		completeSetProperties();
	}
	
	protected abstract void completeSetProperties() throws InvalidInputException, NonSIException;

	@Override
	public double getValueIn(T1 unit1, TimeUnits unit2) {
		double num = property1.getValue();
		double den = property2.getValue();
		
		if (unit1 != this.getUnit1()) {
			try {
				num = property1.getValueIn(unit1);
			} catch (InvalidInputException | SIFactorException e) {
				e.printStackTrace();
			}
		}
		
		if (unit2 != this.getUnit2()) {
			try {
				den = property2.getValueIn(unit2);
			} catch (InvalidInputException | SIFactorException e) {
				e.printStackTrace();
			}
		}
		
		return num / den;
	}

	@Override
	public void addSIFactor(SIUnits factor, boolean unit1, boolean unit2) throws SIFactorException, NonSIException {
		super.addSIFactor(factor, unit1, unit2);
		
		double value = property1.getValue() / property2.getValue();
		this.setValue(value);
		try {
			this.setProperties();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeSIFactor(boolean unit1, boolean unit2) throws SIFactorException {
		super.removeSIFactor(unit1, unit2);
		
		double value = property1.getValue() / property2.getValue();
		this.setValue(value);
		
		try {
			this.setProperties();
		} catch (NonSIException | InvalidInputException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.format("%.3e %s/%s", this.getValue(), property1.printUnits(), property2.printUnits());
	}
}
