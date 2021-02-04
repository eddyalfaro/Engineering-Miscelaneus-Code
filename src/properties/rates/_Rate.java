package properties.rates;

import constants.SIUnits;
import constants.TimeUnits;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;
import properties.base.Time;

public class _Rate<T1 extends UnitChanger> extends PropertyTwo<T1, TimeUnits>{
	
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
	public _Rate<T1> setAt(double value, T1 unit1, TimeUnits unit2) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		_Rate<T1> temp = new _Rate<T1>(value, unit1, unit2);
		
		try {
			temp.setProperties();
		} catch (SIFactorException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public _Rate<T1> setAt(double value, T1 unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		_Rate<T1> temp = new _Rate<T1>(value, unit1, unit2, factor1, factor2);
		temp.setProperties();
		
		return temp;
	}

	@Override
	public void setProperties() throws InvalidInputException, SIFactorException {		
		if (getFactor2() == null) {
			this.property2 = Time.setAt(1.0, getUnit2());
		}else {
			this.property2 = Time.setAt(1.0, getFactor2(), getUnit2());
		}
	}

	@Override
	public _Rate<T1> calculate(PropertyOne<T1> a, PropertyOne<TimeUnits> b) {
		double value = a.getValue() / b.getValue();
		
		_Rate<T1> temp = new _Rate<T1>();
		temp.setValue(value);
		temp.setUnit1(a.getUnits());
		temp.setFactor1((SIUnits) a.getFactor());
		temp.setUnit2(b.getUnits());
		temp.setFactor2((SIUnits) b.getFactor());
		
		try {
			temp.setProperties();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (SIFactorException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

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
		} catch (InvalidInputException | SIFactorException e) {
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
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.format("%.3e %s/%s", this.getValue(), property1.printUnits(), property2.printUnits());
	}

	@Override
	public _Rate<T1> getIn(T1 unit1, TimeUnits unit2) {
		double value = this.getValueIn(unit1, unit2);
		_Rate<T1> temp = new _Rate<T1>(value, unit1, unit2);
		return temp;
	}

	@Deprecated
	public _Rate<T1> setAt(double value) throws InvalidInputException {
		return null;
	}
}
