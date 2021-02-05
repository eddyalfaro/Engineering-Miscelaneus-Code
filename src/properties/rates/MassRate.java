package properties.rates;

import constants.MassUnits;
import constants.SIUnits;
import constants.TimeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.base.Mass;

public class MassRate extends _Rate<MassUnits>{
	
	public static final MassRate MASS_RATE = new MassRate();
	
	private MassRate() {
		super();
	}
	
	private MassRate(double value, MassUnits unit1, TimeUnits unit2) {
		super(value, unit1, unit2);
	}
	
	private MassRate(double value, MassUnits unit1, TimeUnits unit2, SIUnits factor1, SIUnits factor2) {
		super(value, unit1, unit2, factor1, factor2);
	}
	
	@Override
	protected void completeSetProperties() throws InvalidInputException, NonSIException {		
		if (getFactor1() == null) {
			this.property1 = Mass.setAt(getValue(), getUnit1());
		}else {
			this.property1 = Mass.setAt(getValue(), getFactor1(), getUnit1());
		}
	}
	
	public void removeSIFactor(boolean unit1, boolean unit2) throws SIFactorException {
		//System.out.println("Original value of mass rate: " + this.toString());
		if (unit1) {
			//System.out.println("Removing factor: " + property1);
			property1.removeSIFactor();
			//System.out.println("Factor Removed: " + property1);
			
			this.setFactor1(null);
			
			if (this.getUnit1() == MassUnits.KILOGRAM) {
				this.setUnit1(MassUnits.GRAM);
			}
		}
		
		if (unit2) {
			property2.removeSIFactor();
			this.setFactor2(null);
		}
		
		double value = property1.getValue() / property2.getValue();
		this.setValue(value);
		//System.out.println("New value of mass rate: " + this.toString());
		
		try {
			this.setProperties();
		} catch (NonSIException | InvalidInputException e) {
			e.printStackTrace();
		}
		//System.out.println();
	}

	@Override
	public MassRate setAt(double value, MassUnits unit1, TimeUnits unit2)
			throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		MassRate temp = new MassRate(value, unit1, unit2);
		
		try {
			temp.setProperties();
		} catch (NonSIException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public MassRate setAt(double value, MassUnits unit1, TimeUnits unit2, SIUnits factor1,
			SIUnits factor2) throws InvalidInputException, NonSIException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		MassRate temp = new MassRate(value, unit1, unit2, factor1, factor2);
		temp.setProperties();
		
		return temp;
	}

	@Override
	public MassRate setAt(double value) throws InvalidInputException {
		return setAt(value, MassUnits.getISU(), TimeUnits.getISU());
	}

	@Override
	public MassRate calculate(PropertyOne<MassUnits> a, PropertyOne<TimeUnits> b) {
		double value = a.getValue() / b.getValue();
		
		MassRate temp = new MassRate();
		temp.setValue(value);
		temp.setUnit1(a.getUnits());
		temp.setFactor1((SIUnits) a.getFactor());
		temp.setUnit2(b.getUnits());
		temp.setFactor2((SIUnits) b.getFactor());
		
		try {
			temp.setProperties();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (NonSIException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public MassRate getIn(MassUnits unit1, TimeUnits unit2) {
		double value = this.getValueIn(unit1, unit2);
		MassRate temp = new MassRate(value, unit1, unit2);
		return temp;
	}

}
