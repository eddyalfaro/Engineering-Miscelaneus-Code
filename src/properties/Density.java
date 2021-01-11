package properties;

import constants.MassUnits;
import constants.SIUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.abstracts.PropertyOne;
import properties.abstracts.PropertyTwo;

public class Density extends PropertyTwo<MassUnits, VolumeUnits>{

	@Override
	public int compareTo(PropertyTwo<MassUnits, VolumeUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAt(double value, MassUnits unit1, VolumeUnits unit2) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAt(double value, MassUnits unit1, VolumeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		// TODO Auto-generated method stub		
	}

	@Override
	public void set(double value) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperties() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PropertyTwo<MassUnits, VolumeUnits> calculate(PropertyOne<MassUnits> a, PropertyOne<VolumeUnits> b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValueIn(MassUnits unit1, VolumeUnits unit2) {
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
	public PropertyTwo<MassUnits, VolumeUnits> getIn(MassUnits unit1, VolumeUnits unit2) {
		// TODO Auto-generated method stub
		return null;
	}

}