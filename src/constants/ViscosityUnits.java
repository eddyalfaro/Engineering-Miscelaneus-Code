package constants;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum ViscosityUnits implements UnitChanger{
	;

	@Override
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSI() {
		// TODO Auto-generated method stub
		return false;
	}

}
