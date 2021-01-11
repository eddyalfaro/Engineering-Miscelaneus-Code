package interfaces;

import constants.interfaces.UnitChanger;
import properties.abstracts.PropertyOne;
import properties.abstracts.PropertyTwo;

public interface DerivedProperty <T1 extends UnitChanger, T2 extends UnitChanger> {
		
	/**
	 * Sets value and units for the instance of the given property by using the original properties
	   as an input. This method is implemented as a bypass of the lenghty constructors. It can also be
	   used as a single instance for the setting of multiple properties with the same unit by using an instance
	   of the given property as the interface.
	 * @param a
	 * @param b
	 * @return
	 */
	public PropertyTwo<T1,T2> calculate(PropertyOne<T1> a, PropertyOne<T2> b);
	
}
