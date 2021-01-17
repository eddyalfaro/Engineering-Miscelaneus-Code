package properties.base;

import constants.AreaUnits;
import constants.LenghtUnits;
import constants.SIUnits;
import constants.VolumeUnits;
import exceptions.InvalidInputException;
import exceptions.NonSIException;
import exceptions.SIFactorException;
import properties.PropertyOne;

public class Volume extends PropertyOne<VolumeUnits>{
	
	private Volume(double value, VolumeUnits unit) {
		super(value, unit);
	}

	private Volume(double value, SIUnits factor, VolumeUnits unit) {
		super(value, factor, unit);
	}
	
	public static Volume setAt(double value, VolumeUnits unit) throws InvalidInputException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		return new Volume(value, unit);
	}
	
	public static Volume setAt(double value, SIUnits factor, VolumeUnits unit) throws InvalidInputException, SIFactorException {
		if (value < 0) {
			throw new InvalidInputException(ERROR1);
		}
		if (!(unit.isSI())){
			throw new SIFactorException(ERROR2);
		}
		return new Volume(value, factor, unit);
	}
	
	public static Volume getInSIUnits(double value) throws InvalidInputException {
		return setAt(value, VolumeUnits.getISU());
	}
	
	public static Volume getInSIUnits(Volume vol) throws InvalidInputException, SIFactorException {
		if (vol == null) {
			throw new NullPointerException();
		}
		return vol.changeUnits(VolumeUnits.getISU());
	}
	
	public void addSIFactor(SIUnits factor) throws SIFactorException, NonSIException {
		if (!unit.isSI()) {
			throw new NonSIException(ERROR2);
		}
		this.hasSIFactor = true;
		this.factor = factor;
		
		this.value = factor.addSIFactor(value, VolumeUnits.LENGHT_DIMENSION_EXPONENT);
	}
	
	public void removeSIFactor() throws SIFactorException {
		if (!unit.isSI()) {
			throw new SIFactorException(ERROR2);
		}
		if (!hasSIFactor) {
			throw new SIFactorException(ERROR3);
		}
		hasSIFactor = false;
		value = factor.removeSIFactor(value, VolumeUnits.LENGHT_DIMENSION_EXPONENT);
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
	public int compareTo(PropertyOne<VolumeUnits> o) {
		if (!(o instanceof Volume)) {
			return INPUT_EXCEPTION;
		}
		Double tempThis = null;
		Double tempO = null;
		
		try {
			tempThis = this.getValueIn(VolumeUnits.getISU());
			tempO = o.getValueIn(VolumeUnits.getISU());
		} catch (InvalidInputException | SIFactorException e) {
			return INPUT_EXCEPTION;
		} 
		
		if (tempO == null || tempThis == null) {
			return NULL_EXCEPTION;
		}
		
		return tempThis.compareTo(tempO);
	}

	@Override
	public double getValueIn(VolumeUnits units) throws InvalidInputException, SIFactorException {
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
	public Volume changeUnits(VolumeUnits newUnits) throws InvalidInputException, SIFactorException {
		double tempVal = this.getValueIn(newUnits);
		return setAt(tempVal, newUnits);
	}

	public String toString() {
		if (factor == null) {
			return super.toString();
		}
		return String.format("%.3e %s%s^%d", value, factor.toString(), unit.getLengthUnit().toString(), VolumeUnits.LENGHT_DIMENSION_EXPONENT);
	}
	
	public static Volume cilinder(Lenght radious, Lenght height) {
		return cilinder(radious.getValue(), height.getValue(), radious.getUnits(), height.getUnits());
	}
	
	public static Volume cilinder(double radious, double height, LenghtUnits units, LenghtUnits units2) {
		double volume = 0;
		Volume temp = null;
		try {
			height = units2.changeUnits(height, LenghtUnits.getISU());
			radious = units.changeUnits(radious, LenghtUnits.getISU());
			Area area = Area.circle(radious, LenghtUnits.getISU());
			volume = area.getValue() * height;
			temp = Volume.setAt(volume, VolumeUnits.getVolumeUnit(LenghtUnits.getISU()));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static Volume parallelepiped(Area base, Lenght height) {
		return parallelepiped(base.getValue(), height.getValue(), base.getUnits(), height.getUnits());
	}
	
	public static Volume parallelepiped(double base, double height, AreaUnits units, LenghtUnits units2) {
		double volume = 0;
		Volume temp = null;
		
		try {
			base = units.changeUnits(base, AreaUnits.getAreaUnit(LenghtUnits.getISU()));
			height = units2.changeUnits(height, LenghtUnits.getISU());
			volume = base * height;
			temp = Volume.setAt(volume, VolumeUnits.getVolumeUnit(LenghtUnits.getISU()));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}		
		
		return temp;
	}

	public static Volume cube(Lenght side) {
		return cube(side.getValue(), side.getUnits());
	}
	
	public static Volume cube(double side, LenghtUnits units) {
		double volume = 0;
		Volume temp = null;
		
		try {
			side = units.changeUnits(side, LenghtUnits.getISU());
			volume = Math.pow(side, VolumeUnits.LENGHT_DIMENSION_EXPONENT);
			temp = Volume.setAt(volume, VolumeUnits.getVolumeUnit(LenghtUnits.getISU()));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	public static Volume cube(Lenght sideA, Lenght sideB, Lenght sideC) {
		return cube(sideA.getValue(), sideB.getValue(), sideC.getValue(), sideA.getUnits(), sideB.getUnits(), sideC.getUnits());
	}
	
	public static Volume cube(double a, double b, double c, LenghtUnits unitsA, LenghtUnits unitsB,
			LenghtUnits unitsC) {
		double volume = 0;
		Volume temp = null;
		
		try {
			a = unitsA.changeUnits(a, LenghtUnits.getISU());
			b = unitsB.changeUnits(b, LenghtUnits.getISU());
			c = unitsC.changeUnits(c, LenghtUnits.getISU());
			volume = a * b * c;
			temp = Volume.setAt(volume, VolumeUnits.getVolumeUnit(LenghtUnits.getISU()));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	public static Volume pipe(Lenght od, Lenght id, Lenght height) {
		return pipe(od.getValue(), id.getValue(), height.getValue(), od.getUnits(), id.getUnits(), height.getUnits());
	}
	
	public static Volume pipe(double od, double id, double height, LenghtUnits units, LenghtUnits units2,
			LenghtUnits units3) {
		double volume = 0;
		Volume temp = null;
		
		try {
			od = units.changeUnits(od, LenghtUnits.getISU());
			id = units2.changeUnits(id, LenghtUnits.getISU());
			height = units3.changeUnits(height, LenghtUnits.getISU());
			
			od = od / 2;
			id = id / 2;
			
			Area odCircle = Area.circle(od, LenghtUnits.getISU());
			Area idCircle = Area.circle(id, LenghtUnits.getISU());
			volume = (odCircle.getValue() - idCircle.getValue()) * height;
			temp = Volume.setAt(volume, VolumeUnits.getVolumeUnit(LenghtUnits.getISU()));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	public static Volume cylinderDifferential(double arc, Lenght ri, Lenght ro, Lenght height) {
		return cylinderDifferential(arc, ro.getValue(), ri.getValue(), height.getValue(), ro.getUnits(), ri.getUnits(), height.getUnits());
	}
	
	public static Volume cylinderDifferential(double arc, double ri, double ro, double height,
			LenghtUnits units, LenghtUnits units2, LenghtUnits units3) {
		Volume temp = Volume.pipe(2 * ro, 2 * ri, height, units, units2, units3);
		
		try {
			double volume = temp.getValue() * (arc / (2 * Math.PI));
			temp = Volume.setAt(volume, temp.getUnits());
		} catch (InvalidInputException|NullPointerException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	public static Volume sphere(Lenght radious) {
		return sphere(radious.getValue(), radious.getUnits());
	}

	public static Volume sphere(double radious, LenghtUnits units) {
		double volume = (4/3) * Math.PI * Math.pow(radious, VolumeUnits.LENGHT_DIMENSION_EXPONENT);
		Volume temp = null;
		
		try {
			temp = Volume.setAt(volume, VolumeUnits.getVolumeUnit(units));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
}
