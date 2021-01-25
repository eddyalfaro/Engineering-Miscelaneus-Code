package properties.rates;

import constants.SIUnits;
import constants.TimeUnits;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;

public class _Rate<T1 extends UnitChanger> extends PropertyTwo<T1, TimeUnits>{

	@Override
	public int compareTo(PropertyTwo<T1, TimeUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PropertyTwo<T1, TimeUnits> setAt(double value, T1 unit1, TimeUnits unit2) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyTwo<T1, TimeUnits> setAt(double value, T1 unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyTwo<T1, TimeUnits> setAt(double value) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PropertyTwo<T1, TimeUnits> calculate(PropertyOne<T1> a, PropertyOne<TimeUnits> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValueIn(T1 unit1, TimeUnits unit2) {
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
	public PropertyTwo<T1, TimeUnits> getIn(T1 unit1, TimeUnits unit2) {
		// TODO Auto-generated method stub
		return null;
	}
}
