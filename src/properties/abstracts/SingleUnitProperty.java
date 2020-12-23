package properties.abstracts;

import constants.SIUnits;
import constants.interfaces.SIFactor;
import constants.interfaces.UnitChanger;

import exceptions.InvalidInputException;
import exceptions.NonSIFactorException;
import exceptions.SIFactorExeception;

public abstract class SingleUnitProperty<Unit extends UnitChanger> implements Comparable<SingleUnitProperty<Unit>>{
	
	protected Unit unit;
	private double value;
	
	private SIFactor factor;
	private boolean hasSIFactor = false;
	
	public SingleUnitProperty(double value, Unit unit) {
		this.unit = unit;
		this.value = value;
	}
	
	public SingleUnitProperty(double value, SIUnits factor, Unit unit) {
		this(value, unit);
		this.factor = factor;
		hasSIFactor = true;
	}
	
	public String toString() {
		if (hasSIFactor) {
			return "" + value + ((SIUnits) factor) + unit;
		}
		return "" + value + unit;
	}
	
	public boolean hasSIFactor() {
		return hasSIFactor;
	}
	
	public double getValue() {
		return value;
	}
		
	public String printUnits() {
		if (!hasSIFactor) {
			return unit.toString();
		}
		return ((SIUnits) factor) + unit.toString();
	}
	
	public abstract double getValueIn(Unit units) throws InvalidInputException;
	
	public abstract SingleUnitProperty<Unit> changeUnits(Unit newUnits) throws InvalidInputException;
	
	public void addSIFactor(SIUnits factor) throws SIFactorExeception{
		if (hasSIFactor) {
			throw new SIFactorExeception("Units already contains a SI factor: " + this.toString());
		}
		
		this.factor = factor;		
		this.value = factor.addSIFactor(value);
		hasSIFactor = true;
	}
	
	public void removeSIFactor() throws SIFactorExeception{
		if (!hasSIFactor) {
			throw new SIFactorExeception("There is no SI Factor to remove");
		}
		
		this.value = factor.removeSIFactor(value);
		this.factor = null;
		hasSIFactor = false;
	}
	
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorExeception, NonSIFactorException {
		//TODO Check if the values within the reference change without updating the reference
		if(!hasSIFactor) {
			throw new SIFactorExeception("There is no SI Factor to replace");
		}
		
		removeSIFactor();
		addSIFactor(newFactor);
	}
	
	public abstract int compareTo(SingleUnitProperty<Unit> o);
}
