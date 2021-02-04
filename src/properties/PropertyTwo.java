package properties;

import constants.SIUnits;
import constants.interfaces.UnitChanger;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.interfaces.PropertySetter;

public abstract class PropertyTwo<T1 extends UnitChanger, T2 extends UnitChanger> 
implements Comparable<PropertyTwo<T1 , T2 >>, PropertySetter<T1, T2>{

	private T1 unit1 = null;
	private SIUnits factor1 = null;
	protected PropertyOne<T1> property1 = null;
	
	private T2 unit2 = null;
	private SIUnits factor2 = null;
	protected PropertyOne<T2> property2 = null;
	
	private double value;
	
	protected static final double DELTA = 1e-4;
	
	protected static final String ERROR1 = "Negative value";
	protected static final String ERROR2 = "Non-SIUnit";
	protected static final String ERROR3 = "There is no SI Factor to remove";
	protected static final String ERROR4 = "There is no SI Factor to replace";
	protected static final String ERROR5 = "Units already contains a SI factor";
	
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
	
	protected void setUnit1(T1 unit1) {
		this.unit1 = unit1;
	}

	protected void setFactor1(SIUnits factor1) {
		this.factor1 = factor1;
	}

	protected void setUnit2(T2 unit2) {
		this.unit2 = unit2;
	}

	protected void setFactor2(SIUnits factor2) {
		this.factor2 = factor2;
	}

	protected void setValue(double value) {
		this.value = value;
	}

	public abstract double getValueIn(T1 unit1, T2 unit2);
	
	public void addSIFactor(SIUnits factor, boolean unit1, boolean unit2) throws SIFactorException, NonSIException {
		if (unit1) {
			property1.addSIFactor(factor);
			factor1 = factor;
		}
		
		if (unit2) {
			property2.addSIFactor(factor);
			factor2 = factor;
		}
	}
	
	public void removeSIFactor(boolean unit1, boolean unit2) throws SIFactorException {
		if (unit1) {
			property1.removeSIFactor();
			factor1 = null;
		}
		
		if (unit2) {
			property2.removeSIFactor();
			factor2 = null;
		}
	}
	
	public void replaceSIFactor(SIUnits factor, boolean unit1, boolean unit2) throws SIFactorException, NonSIException {
		removeSIFactor(unit1, unit2);
		addSIFactor(factor, unit1, unit2);
	}
	
	public abstract String toString();
	
	public abstract PropertyTwo<T1, T2> getIn(T1 unit1, T2 unit2);
}
