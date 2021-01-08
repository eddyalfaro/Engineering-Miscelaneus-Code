package properties;

import constants.LenghtUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.SIFactorException;
import properties.abstracts.PropertyOne;

public class Lenght extends PropertyOne<LenghtUnits> {
	
	private static final String ERROR_01 = "Use alternative method, centimeter cannot have SI Factor";

	private Lenght(double value, LenghtUnits unit) {
		super(value, unit);
	}
	
	private Lenght(double value, SIUnits factor, LenghtUnits unit) {
		super(value, factor, unit);
	}
	
	public static Lenght setAt(double value, LenghtUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		
		Lenght temp = new Lenght(value, unit);
		
		if (unit == LenghtUnits.CENTIMETER) {
			temp.hasSIFactor = true;
		}	
		
		return temp;
	}
	
	public static Lenght setAt(double value, SIUnits factor, LenghtUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (unit == LenghtUnits.CENTIMETER) {
			throw new InvalidInputException(ERROR_01);
		}
		if (!(unit.isSI())) {
			throw new InvalidInputException(ERROR2);
		}
		return new Lenght(value, factor, unit);
	}
	
	public static Lenght getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, LenghtUnits.getISU());
	}
	
	public static Lenght getInSIUnits(Lenght o) throws InvalidInputException, SIFactorException {
		if (o == null) {
			throw new NullPointerException();
		}
		return o.changeUnits(LenghtUnits.getISU());
	}
	
	public void addSIFactor(SIUnits factor) {
		
	}
	
	public void removeSIFactor() {
		
	}
	
	public void replaceSIFactor(SIUnits newFactor) {
		
	}

	@Override
	public int compareTo(PropertyOne<LenghtUnits> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValueIn(LenghtUnits units) throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Lenght changeUnits(LenghtUnits newUnits)
			throws InvalidInputException, SIFactorException {
		// TODO Auto-generated method stub
		return null;
	}

}
