package properties.derived;

import constants.MassUnits;
import constants.SIUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;
import properties.base.Pressure;
import properties.base.Temperature;

public class Density extends PropertyTwo<MassUnits, VolumeUnits>{
	
	private Pressure pressure = null;
	private Temperature temperature = null;
	
	

	@Override
	public int compareTo(PropertyTwo<MassUnits, VolumeUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Density setAt(double value, MassUnits unit1, VolumeUnits unit2) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Density setAt(double value, MassUnits unit1, VolumeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		// TODO Auto-generated method stub		
		return null;
	}

	@Override
	public Density setAt(double value) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
		
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