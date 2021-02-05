package properties.derived;

import constants.LenghtUnits;
import constants.SIUnits;
import constants.TimeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;
import properties.base.Time;
import properties.base.Lenght;

public class Acceleration extends PropertyTwo<LenghtUnits, TimeUnits>{
	
	public static final Acceleration ACCELERATION = new Acceleration();
	public static final int TIME_DIMENSION_EXPONENT = -2;	
	
	public static final Acceleration EARTH_GRAVITY = new Acceleration(9.780327, LenghtUnits.METER, TimeUnits.SECONDS);
		
	private Acceleration() {
		super();
	}
	
	private Acceleration(double value, LenghtUnits unit1, TimeUnits unit2) {
		super(value, unit1, unit2);
	}
	
	@Override
	public int compareTo(PropertyTwo<LenghtUnits, TimeUnits> o) {
		Double thisVal = this.getValueIn(LenghtUnits.getISU(), TimeUnits.getISU());
		Double oVal = o.getValueIn(LenghtUnits.getISU(), TimeUnits.getISU());
		
		double res = thisVal - oVal;
		res = Math.abs(res);
		
		if (res < DELTA) {
			return 0;
		}
		
		return thisVal.compareTo(oVal);
	}

	@Override
	public Acceleration setAt(double value, LenghtUnits unit1, TimeUnits unit2) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		return new Acceleration(value, unit1, unit2);
	}

	@Override
	public Acceleration setAt(double value, LenghtUnits unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		Acceleration temp = new Acceleration(value, unit1, unit2);
		temp.setFactor1(factor1);
		temp.setFactor2(factor2);
		temp.setProperties();
		
		return temp;
	}

	@Override
	public Acceleration setAt(double value) throws InvalidInputException {
		return ACCELERATION.setAt(value, LenghtUnits.getISU(), TimeUnits.getISU());
	}

	@Deprecated
	public void setProperties() throws InvalidInputException, NonSIException {
		if (getFactor1() == null) {
			this.property1 = Lenght.setAt(getValue(), getUnit1());
		}else {
			this.property1 = Lenght.setAt(getValue(), getFactor1(), getUnit1());
		}
		
		if (getFactor2() == null) {
			this.property2 = Time.setAt(1.0, getUnit2());
		}else {
			this.property2 = Time.setAt(1.0, getFactor2(), getUnit2());
		}	
	}

	@Deprecated
	public Acceleration calculate(PropertyOne<LenghtUnits> a, PropertyOne<TimeUnits> b) {
		return null;
	}

	@Override
	public double getValueIn(LenghtUnits unit1, TimeUnits unit2) {
		double numerator = property1.getValue();
		double denominator = property2.getValue();
		
		if (unit1 != this.getUnit1()) {
			try {
				numerator = property1.getValueIn(unit1);
			} catch (InvalidInputException | SIFactorException e) {
				e.printStackTrace();
			}
		}
		
		if (unit2 != this.getUnit2()) {
			try {
				denominator = property2.getValueIn(unit2);
				denominator = Math.pow(denominator, -TIME_DIMENSION_EXPONENT);
			} catch (InvalidInputException | SIFactorException e) {
				e.printStackTrace();
			}
		}
		
		return numerator / denominator;
	}

	@Override
	public void addSIFactor(SIUnits factor, boolean unit1, boolean unit2) throws SIFactorException, NonSIException {
		if (unit1) {
			super.addSIFactor(factor, unit1, unit2);
		}
		
		if (unit2) {
			double prop2 = property2.getFactor().addSIFactor(property2.getValue(), -TIME_DIMENSION_EXPONENT);
			try {
				property2 = Time.setAt(prop2, factor, getUnit2());
				this.setFactor2(factor);
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}
		
		double value = property1.getValue() / property2.getValue();
		
		this.setValue(value);
	}

	@Override
	public void removeSIFactor(boolean unit1, boolean unit2) throws SIFactorException {
		if(unit1) {
			super.removeSIFactor(unit1, unit2);
		}
		
		if(unit2) {
			double prop2 = property2.getFactor().removeSIFactor(property2.getValue(), -TIME_DIMENSION_EXPONENT);
			try {
				property2 = Time.setAt(prop2, getUnit2());
				this.setFactor2(null);
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}
		
		double value = property1.getValue() / property2.getValue();
		
		this.setValue(value);
	}

	@Override
	public String toString() {
		return String.format("%.3e %s.%s^%s", getValue(), property1.printUnits(), property2.printUnits(), TIME_DIMENSION_EXPONENT);
	}

	@Override
	public Acceleration getIn(LenghtUnits unit1, TimeUnits unit2) {
		double value = getValueIn(unit1, unit2);
		Acceleration temp = null;
		
		try {
			temp = ACCELERATION.setAt(value, unit1, unit2);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

}
