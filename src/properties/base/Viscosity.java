package properties.base;

import constants.SIUnits;
import constants.ViscosityUnits;
import exceptions.InvalidInputException;
import exceptions.SIFactorException;
import properties.PropertyOne;

public class Viscosity extends PropertyOne<ViscosityUnits>{

	private Viscosity(double value, ViscosityUnits unit) {
		super(value, unit);
	}
	
	private Viscosity(double value, SIUnits factor, ViscosityUnits unit) {
		super(value, factor, unit);
	}

	@Override
	public int compareTo(PropertyOne<ViscosityUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValueIn(ViscosityUnits units) throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PropertyOne<ViscosityUnits> changeUnits(ViscosityUnits newUnits)
			throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return null;
	}

}
