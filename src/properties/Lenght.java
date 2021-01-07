package properties;

import constants.LenghtUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.SIFactorException;
import properties.abstracts.SingleUnitProperty;

public class Lenght extends SingleUnitProperty<LenghtUnits> {

	private Lenght(double value, LenghtUnits unit) {
		super(value, unit);
		// TODO Auto-generated constructor stub
	}
	
	private Lenght(double value, SIUnits factor, LenghtUnits unit) {
		super(value, factor, unit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(SingleUnitProperty<LenghtUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValueIn(LenghtUnits units) throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SingleUnitProperty<LenghtUnits> changeUnits(LenghtUnits newUnits)
			throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return null;
	}

}
