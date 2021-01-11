package interfaces;

import constants.SIUnits;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;

public interface PropertySetter<T1 extends UnitChanger, T2 extends UnitChanger> 
extends DerivedProperty<T1, T2>{
	
	/**
	 * sets the instance to the given value and units.
	 * @param value
	 * @param unit1
	 * @param unit2
	 * @throws InvalidInputException
	 */
	public void setAt(double value, T1 unit1, T2 unit2) throws InvalidInputException;
	
	/**
	 * sets the instance with the given value and units, this method is only usable with standarized 
	   metric system units. IF non SI units are used the ethod will return an error.
	 * @param value
	 * @param unit1
	 * @param unit2
	 * @param factor1
	 * @param factor2
	 * @throws InvalidInputException
	 * @throws NonSIException
	 * @throws SIFactorException
	 */
	public void setAt(double value, T1 unit1, T2 unit2, SIUnits factor1, SIUnits factor2)
	throws InvalidInputException, NonSIException, SIFactorException;
	
	/**
	 * sets the unit value with the default SI units without any SI factor
	 * @param value
	 * @throws InvalidInputException
	 */
	public void set(double value) throws InvalidInputException;
	
	/**
	 * sets an instance for each property within the instance. These properties are used to change units of the 
	   property instance
	 */
	public void setProperties();

}
