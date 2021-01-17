package properties.base;

import constants.AreaUnits;
import constants.LenghtUnits;
import constants.SIUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;

public class Area extends PropertyOne<AreaUnits>{

	private Area(double value, AreaUnits unit) {
		super(value, unit);
	}
	
	private Area(double value, SIUnits factor, AreaUnits unit) {
		super(value, factor, unit);
	}
	
	public static Area setAt(double value, AreaUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Area(value, unit);
	}
	
	public static Area setAt(double value, SIUnits factor, AreaUnits unit) throws InvalidInputException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (!(unit.isSI())){
			throw new SIFactorException(ERROR2);
		}
		return new Area(value, factor, unit);
	}
	
	public static Area getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, AreaUnits.getISU());
	}
	
	public static Area getInSIUnits(Area area) throws InvalidInputException, SIFactorException {
		if (area == null) {
			throw new NullPointerException();
		}
		return area.changeUnits(AreaUnits.getISU());
	}
	
	public void addSIFactor(SIUnits factor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new NonSIException(ERROR2);
		}
		this.hasSIFactor = true;
		this.factor = factor;
		
		this.value = factor.addSIFactor(value, AreaUnits.LENGHT_DIMENSION_EXPONENT);
	}
	
	public void removeSIFactor() throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR3);
		}
		hasSIFactor = false;
		value = factor.removeSIFactor(value, AreaUnits.LENGHT_DIMENSION_EXPONENT);
		factor = null;
	}
	
	public void replaceSIFactor(SIUnits newFactor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR4);
		}
		removeSIFactor();
		addSIFactor(newFactor);
	}

	@Override
	public double getValueIn(AreaUnits units) throws InvalidInputException, SIFactorException {
		SIUnits temp = null;
		if (hasSIFactor()) {
			temp = (SIUnits) factor;
			removeSIFactor();
		}
		
		double result = super.unit.changeUnits(getValue(), units);
		
		if (temp != null) {
			try {
				addSIFactor(temp);
			} catch (NonSIException e) {
				e.printStackTrace();
			}	
		}
		
		return result;
	}

	@Override
	public Area changeUnits(AreaUnits newUnits)	throws InvalidInputException, SIFactorException {
		double tempVal = this.getValueIn(newUnits);
		return setAt(tempVal, newUnits);
	}

	@Override
	public int compareTo(PropertyOne<AreaUnits> o) {
		if (!(o instanceof Area)) {
			return INPUT_EXCEPTION;
		}
		Double tempThis = null;
		Double tempO = null;
		
		try {
			tempThis = this.getValueIn(AreaUnits.getISU());
			tempO = o.getValueIn(AreaUnits.getISU());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} 
		
		if (tempO == null || tempThis == null) {
			return NULL_EXCEPTION;
		}
		
		return tempThis.compareTo(tempO);
	}

	public String toString() {
		if (factor == null) {
			return super.toString();
		}
		return String.format("%.3e %s%s^%d", value, factor.toString(), unit.getLengthUnit().toString(), AreaUnits.LENGHT_DIMENSION_EXPONENT);
	}
	
	public static Area square(double sideA, LenghtUnits a, double sideB, LenghtUnits b) {
		if (sideA < 0 || sideB < 0) {
			return null;
		}
		
		Area area = null;
		
		try {
			sideB = b.changeUnits(sideB, a);
			double value = sideA * sideB;
			area = setAt(value, AreaUnits.getAreaUnit(a));
		} catch (InvalidInputException e) {
			return null;
		} 
		
		return area;
	}
	
	public static Area square(Lenght sideA, Lenght sideB) {
		return square(sideA.getValue(), sideA.getUnits(), sideB.getValue(), sideB.getUnits());
	}
	
	public static Area circle(double radious, LenghtUnits a) {
		if (radious < 0) {
			return null;
		}
		Area area = null;
		double value = Math.PI * Math.pow(radious, AreaUnits.LENGHT_DIMENSION_EXPONENT);
		
		try {
			area = setAt(value, AreaUnits.getAreaUnit(a));
		} catch (InvalidInputException e) {
			return null;
		}
		
		return area;
	}
	
	public static Area circle(Lenght radious) {
		return circle(radious.getValue(), radious.getUnits());
	}
	
	public static Area triangle(double height, LenghtUnits h, double base, LenghtUnits b) {
		if (height < 0 || base < 0) {
			return null;
		}
		
		Area area = null;
		
		try {
			base = b.changeUnits(base, h);
			double value = 0.5 * height * base;
			area = setAt(value, AreaUnits.getAreaUnit(h));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return area;
	}
	
	public static Area triangle(Lenght height, Lenght base) {
		return triangle(height.getValue(), height.getUnits(), base.getValue(), base.getUnits());
	}
	
	public static Area cilinder(double radious, LenghtUnits a, double height, LenghtUnits h) {
		Area circle = circle(radious, a);
		double sideB = 2 * Math.PI * radious;
		Area square = square(height, h, sideB, h);
		
		if (circle == null || square == null) {
			return null;
		}
		
		Area area = null;
		
		try {
			double value = (2 * circle.getValue()) + square.getValueIn(circle.getUnits());
			area = setAt(value, circle.getUnits());
		} catch (InvalidInputException | SIFactorException e) {
			e.printStackTrace();
		}
		
		return area;
	}
	
	public static Area cilinder(Lenght radious, Lenght height) {
		return cilinder(radious.getValue(), radious.getUnits(), height.getValue(), height.getUnits());
	}
}
