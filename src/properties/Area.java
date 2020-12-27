package properties;

import constants.AreaUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.SIFactorException;
import properties.abstracts.SingleUnitProperty;

public class Area extends SingleUnitProperty<AreaUnits>{

	private Area(double value, AreaUnits unit) {
		super(value, unit);
	}
	
	private Area(double value, SIUnits factor, AreaUnits unit) {
		super(value, factor, unit);
	}

	@Override
	public double getValueIn(AreaUnits units) throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SingleUnitProperty<AreaUnits> changeUnits(AreaUnits newUnits)
			throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(SingleUnitProperty<AreaUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
