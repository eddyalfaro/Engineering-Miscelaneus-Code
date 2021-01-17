package properties.derived;

import constants.SIUnits;
import constants.TimeUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;

public class FlowRate extends PropertyTwo<VolumeUnits, TimeUnits>{

	@Override
	public int compareTo(PropertyTwo<VolumeUnits, TimeUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAt(double value, VolumeUnits unit1, TimeUnits unit2) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAt(double value, VolumeUnits unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2)
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
	public PropertyTwo<VolumeUnits, TimeUnits> calculate(PropertyOne<VolumeUnits> a, PropertyOne<TimeUnits> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValueIn(VolumeUnits unit1, TimeUnits unit2) {
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
	public PropertyTwo<VolumeUnits, TimeUnits> getIn(VolumeUnits unit1, TimeUnits unit2) {
		// TODO Auto-generated method stub
		return null;
	}

}
