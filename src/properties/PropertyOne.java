package properties;

import constants.SIUnits;
import constants.interfaces.SIFactor;
import constants.interfaces.UnitChanger;

import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;

public abstract class PropertyOne<Unit extends UnitChanger> implements Comparable<PropertyOne<Unit>>{
	
	protected Unit unit;
	protected double value;
	
	protected SIFactor factor = null;
	protected boolean hasSIFactor = false;	

	protected static final int INPUT_EXCEPTION = -111;
	protected static final int NULL_EXCEPTION = -222;
	
	protected static final String ERROR1 = "Negative value";
	protected static final String ERROR2 = "Non-SIUnit";
	protected static final String ERROR3 = "There is no SI Factor to remove";
	protected static final String ERROR4 = "There is no SI Factor to replace";
	protected static final String ERROR5 = "Units already contains a SI factor";
	
	public PropertyOne(double value, Unit unit) {
		this.unit = unit;
		this.value = value;
	}
	
	public PropertyOne(double value, SIUnits factor, Unit unit) {
		this(value, unit);
		this.factor = factor;
		
		if (factor != null) {
			hasSIFactor = true;	
		}
	}
	
	public Unit getUnits() {
		return unit;
	}
	
	public SIFactor getFactor() {
		return factor;
	}
	
	public String toString() {
		if (factor != null) {
			return String.format("%.3e %s%s", value, ((SIUnits) factor).toString(), unit.toString());
		}
		return String.format("%.3e %s", value, unit.toString());
	}
	
	public boolean hasSIFactor() {
		return hasSIFactor;
	}
	
	public double getValue() {
		return value;
	}
		
	public String printUnits() {
		if (factor == null) {
			return unit.toString();
		}
		return ((SIUnits) factor) + unit.toString();
	}
	
	/**
	 * Returns the value of this instace into the given units. The units the method is requesting have
	 to correspond to valid units of the given property.
	 * @param units unit value of the instance to which the method will be converting
	 * @return the value of the property in the units used as an input
	 * @throws InvalidInputException
	 * @throws SIFactorException
	 */
	public abstract double getValueIn(Unit units) throws InvalidInputException, SIFactorException;
	
	/**
	 * Creates a new instance of the property with the given specified unit as its base. The numerical value
	   the original instance and the new instance are equivalent albeit in different units. The new unit has 
	   have a compatible unit used by the property
	 * @param newUnits unit the new instance will have
	 * @return a new instance of the property with different units to the one it was called from.
	 * @throws InvalidInputException
	 * @throws SIFactorException
	 */
	public abstract PropertyOne<Unit> changeUnits(Unit newUnits) throws InvalidInputException, SIFactorException;
	
	/**
	 * Adds SI factor only to units valid within the international metric system. If the instance is not
	   an international standard unit the method will return an Exception and fail to add a SI factor
	 * @param factor Enum to add to the instance
	 * @throws SIFactorException
	 * @throws NonSIException 
	 */
	public void addSIFactor(SIUnits factor) throws SIFactorException, NonSIException{
		if (!unit.isSI()) {
			throw new NonSIException(ERROR2);
		}
		if (hasSIFactor) {
			throw new SIFactorException("Units already contains a SI factor: " + this.toString());
		}
		
		this.factor = factor;		
		this.value = factor.addSIFactor(value);
		hasSIFactor = true;
	}
	
	/**
	 * Similar to addSIFactor, this removes an SIFactor from the instance changing its value to its base
	   value. The method will throw an error if the instance does not have a SI factor
	 * @throws SIFactorException
	 */
	public void removeSIFactor() throws SIFactorException{
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR3);
		}
		
		this.value = factor.removeSIFactor(value);
		this.factor = null;
		hasSIFactor = false;
	}
	
	/**
	 * Replaces the SI factor that the instance currently has. The numerical value will change to the
	   equivalent value with the new Factor. There wil be two exceptions thrown in this method. One if the
	   give factor is not of SIUnit instance and the other if the instance does not contain a factor to 
	   replace
	 * 
	 * @param newFactor factor that will replace the factor within the instance
	 * @throws SIFactorException
	 * @throws NonSIException
	 */
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorException, NonSIException {
		//TODO Check if the values within the reference change without updating the reference
		if(!hasSIFactor) {
			throw new SIFactorException(ERROR4);
		}
		
		removeSIFactor();
		addSIFactor(newFactor);
	}
}
