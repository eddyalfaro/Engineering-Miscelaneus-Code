package properties;

import constants.MassUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.abstracts.SingleUnitProperty;

public class Mass extends SingleUnitProperty<MassUnits>{

	private Mass(double value, MassUnits unit) {
		super(value, unit);
	}
	
	private Mass(double value, SIUnits factor, MassUnits unit) {
		super(value, factor, unit);
	}
	
	public void addSIFactor(SIUnits factor) throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException("Factor cannot be added to non IS Unit " + unit);
		}//TODO for mass add a statement that would add or remove si factor for kg
		super.addSIFactor(factor);
	}
	
	public void removeSIFactor() throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException("Factor cannot be added to non IS Unit " + unit);
		}
		super.removeSIFactor();
	}
	
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new SIFactorException("Factor cannot be added to non IS Unit " + unit);
		}
		super.replaceSIFactor(newFactor);
	}

	@Override
	public double getValueIn(MassUnits units) throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Mass changeUnits(MassUnits newUnits) throws InvalidInputException, 
														SIFactorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(SingleUnitProperty<MassUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
