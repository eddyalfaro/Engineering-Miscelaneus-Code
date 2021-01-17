package properties.derived;

import constants.LenghtUnits;
import constants.SIUnits;
import constants.TimeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;

public class Acceleration extends PropertyTwo<LenghtUnits, TimeUnits>{
	
	public static final Acceleration SETTER = new Acceleration();
	private static final int TIME_DIMENSION_EXPONENT = 2;	
	public static final Acceleration EARTH_GRAVITY = new Acceleration(9.780327, LenghtUnits.METER, TimeUnits.SECONDS);
		
	private Acceleration() {
		super();
	}
	
	private Acceleration(double value, LenghtUnits unit1, TimeUnits unit2) {
		super(value, unit1, unit2);
	}
	
	@Override
	public int compareTo(PropertyTwo<LenghtUnits, TimeUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Acceleration setAt(double value, LenghtUnits unit1, TimeUnits unit2) throws InvalidInputException {
		return null;
	}

	@Override
	public Acceleration setAt(double value, LenghtUnits unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		return null;
	}

	@Override
	public Acceleration setAt(double value) throws InvalidInputException {
		return null;
	}

	@Override
	public void setProperties() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PropertyTwo<LenghtUnits, TimeUnits> calculate(PropertyOne<LenghtUnits> a, PropertyOne<TimeUnits> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValueIn(LenghtUnits unit1, TimeUnits unit2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addSIFactor(SIUnits factor, boolean unit1, boolean unit2) {
		return;
	}

	@Override
	public void removeSIFactor(boolean unit1, boolean unit2) {
		return;
	}

	@Override
	public void replaceSIFactor(SIUnits factor, boolean unit1, boolean unit2) {
		return;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Acceleration getIn(LenghtUnits unit1, TimeUnits unit2) {
		// TODO Auto-generated method stub
		return null;
	}

}
