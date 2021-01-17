package properties.derived;

import constants.LenghtUnits;
import constants.SIUnits;
import constants.TimeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;

public class Velocity extends PropertyTwo<LenghtUnits, TimeUnits>{

	@Override
	public int compareTo(PropertyTwo<LenghtUnits, TimeUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAt(double value, LenghtUnits unit1, TimeUnits unit2) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAt(double value, LenghtUnits unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAt(double value) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperties() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Velocity calculate(PropertyOne<LenghtUnits> a, PropertyOne<TimeUnits> b) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSIFactor(boolean unit1, boolean unit2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceSIFactor(SIUnits factor, boolean unit1, boolean unit2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Velocity getIn(LenghtUnits unit1, TimeUnits unit2) {
		// TODO Auto-generated method stub
		return null;
	}

}
