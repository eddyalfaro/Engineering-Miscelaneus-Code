package constants.interfaces;

import exceptions.InvalidInputException;

public interface UnitChanger{

	/**
	 * Calculates the numerical value attached to the units of the original called instatiated enum to the parameter enum.
	 Thus, if the method is called Unit1.changeUnits(value, Unit2) the method will return a numerical value that 
	 corresponds to an equivalence as value_Unit1 = return_Unit2. 
	 The parameter enum input must have the same class as the one it was called from to avoid exceptions being thrown
	 
	 * @param <E> calling enum unit which is currently attached to the value
	 * @param value numerical value of <E>
	 * @param to unit to which the origianl numerical value is being converted to
	 * @return the equivalent numerical value of <E> in to units
	 * @throws InvalidInputException
	 */
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException;
	
}
