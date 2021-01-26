package properties.derived;

import constants.MassUnits;
import constants.SIUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;
import properties.PropertyTwo;
import properties.base.Mass;
import properties.base.Pressure;
import properties.base.Temperature;
import properties.base.Volume;

public class Density extends PropertyTwo<MassUnits, VolumeUnits>{
	
	private Pressure pressure = null;
	private Temperature temperature = null;
	
	public static final  Density DENSITY = new Density();
	
	private Density() {
		super();
	}
	
	private Density(double value, MassUnits unit1, VolumeUnits unit2) {
		super(value, unit1, unit2);
	}
	
	private Density(double value, MassUnits unit1, VolumeUnits unit2, SIUnits factor1, SIUnits factor2) {
		super(value, unit1, unit2, factor1, factor2);
	}
	
	public void atPressure(Pressure pressure) {
		this.pressure = pressure;
	}
	
	public void atTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	@Override
	public int compareTo(PropertyTwo<MassUnits, VolumeUnits> o) {
		Double thisVal = this.getValueIn(MassUnits.getISU(), VolumeUnits.getISU());
		Double oVal = o.getValueIn(MassUnits.getISU(), VolumeUnits.getISU());		
		return thisVal.compareTo(oVal);
	}

	@Override
	public Density setAt(double value, MassUnits unit1, VolumeUnits unit2) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		Density temp = new Density(value, unit1, unit2);
		
		try {
			temp.setProperties();
		} catch (SIFactorException e) {
			e.printStackTrace();
		}		
		return temp;
	}

	@Override
	public Density setAt(double value, MassUnits unit1, VolumeUnits unit2, SIUnits factor1, SIUnits factor2)
			throws InvalidInputException, NonSIException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		Density temp = new Density(value, unit1, unit2, factor1, factor2);
		temp.setProperties();
		
		return temp;
	}

	@Override
	public Density setAt(double value) throws InvalidInputException {
		Density temp = DENSITY.setAt(value, MassUnits.getISU(), VolumeUnits.getISU());
		return temp;		
	}

	@Override
	public void setProperties() throws InvalidInputException, SIFactorException {
		if (getFactor1() == null) {
			this.property1 = Mass.setAt(getValue(), getUnit1());
		}else {
			this.property1 = Mass.setAt(getValue(), getFactor1(), getUnit1());
		}
		
		if (getFactor2() == null) {
			this.property2 = Volume.setAt(1.0, getUnit2());
		}else {
			this.property2 = Volume.setAt(1.0, getFactor2(), getUnit2());
		}
	}

	@Override
	public Density calculate(PropertyOne<MassUnits> a, PropertyOne<VolumeUnits> b) {
		double value = a.getValue() / b.getValue();
		Density temp = new Density();
		temp.setValue(value);
		temp.setUnit1(a.getUnits());
		temp.setFactor1((SIUnits) a.getFactor());
		temp.setUnit2(b.getUnits());
		temp.setFactor2((SIUnits) b.getFactor());
		try {
			temp.setProperties();
		} catch (InvalidInputException | SIFactorException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public double getValueIn(MassUnits unit1, VolumeUnits unit2) {
		
		SIUnits factor1 = getFactor1();
		SIUnits factor2 = getFactor2();
		
		if (factor1 != null) {//removing factor of the first unit
			try {
				removeSIFactor(true, false);
			} catch (SIFactorException e) {
				e.printStackTrace();
			}
		}
		
		if (factor2 != null) {//removing factor of the second unit
			try {
				removeSIFactor(false, true);
			} catch (SIFactorException e) {
				e.printStackTrace();
			}
		}
		
		double value = getValue();
		
		try {// caluclating the value in the parameter units
			value = this.getUnit1().changeUnits(value, unit1);
			value = value / this.getUnit2().changeUnits(1.0, unit2);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		if (factor1 != null) {//placing again the factor in the first unit
			try {
				addSIFactor(factor1, true, false);
			} catch (SIFactorException | NonSIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (factor2 != null) {//placing again the factor in the second unit
			try {
				addSIFactor(factor2, false, true);
			} catch (SIFactorException | NonSIException e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public void addSIFactor(SIUnits factor, boolean unit1, boolean unit2) throws SIFactorException, NonSIException {
		super.addSIFactor(factor, unit1, unit2);
		
		double value = property1.getValue() / property2.getValue();
		this.setValue(value);
	}

	@Override
	public void removeSIFactor(boolean unit1, boolean unit2) throws SIFactorException {
		super.removeSIFactor(unit1, unit2);
		
		double value = property1.getValue() / property2.getValue();
		this.setValue(value);
		
		if(unit1 && this.getUnit1() == MassUnits.KILOGRAM) {
			this.setUnit1(MassUnits.GRAM);
		}
		
		try {
			this.setProperties();
		} catch (InvalidInputException | SIFactorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		if (pressure == null && temperature == null) {
			return String.format("%.3e %s/%s", getValue(), property1.printUnits(), property2.printUnits());
		}
		return String.format("%.3e %s/%s at %s & %s", getValue(), property1.printUnits(), property2.printUnits(), pressure.toString(), temperature.toString());
	}

	@Override
	public Density getIn(MassUnits unit1, VolumeUnits unit2) {
		double value = this.getValueIn(unit1, unit2);
		Density temp = null;
		try {
			temp = DENSITY.setAt(value, unit1, unit2);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

}