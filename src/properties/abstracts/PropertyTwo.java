package properties.abstracts;

import constants.SIUnits;
import constants.interfaces.UnitChanger;
import interfaces.DerivedProperty;
import interfaces.PropertySetter;

public abstract class PropertyTwo<T1 extends UnitChanger, T2 extends UnitChanger> 
implements Comparable<PropertyTwo<T1 , T2 >>, PropertySetter<T1, T2>{

	private T1 unit1 = null;
	private SIUnits factor1 = null;
	private PropertyOne<T1> property1 = null;
	
	private T2 unit2 = null;
	private SIUnits factor2 = null;
	private PropertyOne<T2> property2 = null;
	
	private double value;
	
	public PropertyTwo() {
		
	}
	
	public PropertyTwo(double value, T1 unit1, T2 unit2) {
		this.unit1 = unit1;
		this.unit2 = unit2;
		this.value = value;
	}
	
	public PropertyTwo(double value, T1 unit1, T2 unit2, SIUnits factor1, SIUnits factor2) {
		this(value, unit1, unit2);
		this.factor1 = factor1;
		this.factor2 = factor2;
	}

	public T1 getUnit1() {
		return unit1;
	}

	public SIUnits getFactor1() {
		return factor1;
	}

	public T2 getUnit2() {
		return unit2;
	}

	public SIUnits getFactor2() {
		return factor2;
	}

	public double getValue() {
		return value;
	}
	
	
	
}
