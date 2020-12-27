package properties.abstracts;

import constants.SIUnits;
import constants.interfaces.SIFactor;
import constants.interfaces.UnitChanger;

import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;

public abstract class SingleUnitProperty<Unit extends UnitChanger> implements Comparable<SingleUnitProperty<Unit>>{
	
	protected Unit unit;
	protected double value;
	
	protected SIFactor factor = null;
	protected boolean hasSIFactor = false;	

	protected static final int INPUT_EXCEPTION = -111;
	protected static final int NULL_EXCEPTION = -222;
	
	public SingleUnitProperty(double value, Unit unit) {
		this.unit = unit;
		this.value = value;
	}
	
	public SingleUnitProperty(double value, SIUnits factor, Unit unit) {
		this(value, unit);
		this.factor = factor;
		
		if (factor != null) {
			hasSIFactor = true;	
		}
	}
	
	public String toString() {
		if (factor != null) {
			return String.format("%.3f%s%s", value, ((SIUnits) factor).toString(), unit.toString());
		}
		return String.format("%.3f%s", value, unit.toString());
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
	
	public abstract double getValueIn(Unit units) throws InvalidInputException, SIFactorException;
	
	public abstract SingleUnitProperty<Unit> changeUnits(Unit newUnits) throws InvalidInputException, SIFactorException;
	
	/**
	 * 
	 * @param factor
	 * @throws SIFactorException
	 */
	public void addSIFactor(SIUnits factor) throws SIFactorException{
		if (hasSIFactor) {
			throw new SIFactorException("Units already contains a SI factor: " + this.toString());
		}
		
		this.factor = factor;		
		this.value = factor.addSIFactor(value);
		hasSIFactor = true;
	}
	
	/**
	 * 
	 * @throws SIFactorException
	 */
	public void removeSIFactor() throws SIFactorException{
		if (!hasSIFactor) {
			throw new SIFactorException("There is no SI Factor to remove");
		}
		
		this.value = factor.removeSIFactor(value);
		this.factor = null;
		hasSIFactor = false;
	}
	
	/**
	 * 
	 * 
	 * @param newFactor
	 * @throws SIFactorException
	 * @throws NonSIException
	 */
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorException, NonSIException {
		//TODO Check if the values within the reference change without updating the reference
		if(!hasSIFactor) {
			throw new SIFactorException("There is no SI Factor to replace");
		}
		
		removeSIFactor();
		addSIFactor(newFactor);
	}
}
