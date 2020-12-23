package properties;

import constants.PressureUnits;
import constants.SIUnits;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;
import properties.abstracts.SingleUnitProperty;

public class Pressure extends SingleUnitProperty<PressureUnits>{
		
	public Pressure(double value, PressureUnits unit) {
		super(value, unit);
	}
	
	public Pressure(double value, SIUnits factor, PressureUnits unit) {
		super(value, factor, unit);
	}
	
	@Override
	public Pressure changeUnits(PressureUnits newUnit) throws InvalidInputException {
		return null;
	}

	@Override
	public double getValueIn(PressureUnits units) throws InvalidInputException{
		return 0;
	}

	@Override
	public int compareTo(SingleUnitProperty<PressureUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
