package properties.rates;

import constants.MassUnits;
import constants.TimeUnits;
import exceptions.InvalidInputException;
import exceptions.SIFactorException;
import properties.base.Mass;

public class MassRate extends _Rate<MassUnits>{
	
	public static final MassRate MASS_RATE = new MassRate();
	
	@Override
	public void setProperties() throws InvalidInputException {		
		try {
			super.setProperties();
		} catch (SIFactorException e) {
			e.printStackTrace();
		}
		
		if (getFactor1() == null) {
			this.property1 = Mass.setAt(getValue(), getUnit1());
		}else {
			this.property1 = Mass.setAt(getValue(), getFactor1(), getUnit1());
		}
	}
	
	public void removeSIFactor(boolean unit1, boolean unit2) throws SIFactorException {
		super.removeSIFactor(unit1, unit2);
		
		if(unit1 && this.getUnit1() == MassUnits.KILOGRAM) {
			this.setUnit1(MassUnits.GRAM);
		}
		
		try {
			this.setProperties();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	public MassRate setAt(double value) throws InvalidInputException {
		return (MassRate) MASS_RATE.setAt(value, MassUnits.getISU(), TimeUnits.getISU());
	}

}
