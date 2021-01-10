package constants;

import java.util.HashMap;

import constants.interfaces.Mapping;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum VolumeUnits implements UnitChanger{
	CUBICMETER(LenghtUnits.METER, true),
	CUBICFEET(LenghtUnits.FOOT, false),
	CUBICINCHES(LenghtUnits.INCH, false),
	CUBICYARD(LenghtUnits.YARD, false),
	CUBICMILE(LenghtUnits.MILE, false),
	LITER("L", 0.035314667, false),
	US_GALLON("Gal", 0.13368056, false),
	OIL_BARREL("Bbl", 5.6145833, false),
	IMPERIAL_GALLON("Imp. gal", 0.16054365, false),
	;
	
	public static final int LENGHT_DIMENSION_EXPONENT = 3;
	public static final int MASS_DIMENSION_EXPONENT = 0;
	public static final int TIME_DIMENSION_EXPONENT = 0;
	public static final int CURRENT_DIMENSION_EXPONENT = 0;
	public static final int TEMPERATURE_DIMENSION_EXPONENT = 0;
	public static final int MOLE_DIMENSION_EXPONENT = 0;
	public static final int LUMINOUSITY_DIMENSION_EXPONENT = 0;
	
	private static final String PREFIX = "c.";
	
	private String symbol;
	private LenghtUnits lenght = null;
	private boolean isSI;
	private Double toCubicFoot = null;
	
	private static final HashMap<LenghtUnits, VolumeUnits> ASOCIATION =
			Mapping.generateHashMap(new VolumeUnits[] {CUBICMETER, CUBICINCHES, CUBICFEET, CUBICYARD, CUBICMILE}, 
					new LenghtUnits[] {LenghtUnits.METER, LenghtUnits.INCH, LenghtUnits.FOOT, LenghtUnits.YARD, LenghtUnits.MILE});
	
	private VolumeUnits(LenghtUnits lenght, boolean isSI) {
		this.lenght = lenght;
		this.isSI = isSI;
	}
	
	private VolumeUnits(String symbol, double conversion, boolean isSI) {
		this.symbol = symbol;
		this.isSI = isSI;
		this.toCubicFoot = conversion;
	}
	
	public static VolumeUnits getVolumeUnit(LenghtUnits lenght) {
		return ASOCIATION.get(lenght);
	}
	
	public LenghtUnits getLengthUnit() {
		return lenght;
	}

	public boolean isSI() {
		return isSI;
	}
	
	public String toString() {
		if (lenght == null) {
			return symbol;
		}
		
		return PREFIX + lenght.toString();
	}
	
	public static VolumeUnits getISU() {
		return CUBICMETER;
	}
	
	private double toCubicFeet(double value, VolumeUnits units) throws InvalidInputException {
		if (units.lenght == null) {
			return value * units.toCubicFoot;
		}else {
			return value * Math.pow(units.lenght.changeUnits(1.0, LenghtUnits.FOOT), LENGHT_DIMENSION_EXPONENT);
		}
	}

	@Override
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		if (!(to instanceof VolumeUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}		
		
		double newValue = value;	
		VolumeUnits tempTo = (VolumeUnits) to;
		double convFactor = 1.0;
		
		if (this.lenght == null) {
			
			newValue = toCubicFeet(newValue, this);//converting to cubic foot
			
			if (tempTo.lenght == null) {// original = non-conventional, destination = non-conventional
				newValue /= tempTo.toCubicFoot; //converting to non-conventional unit
			}else {// original = non-conventional, destination = conventional
				convFactor = LenghtUnits.FOOT.changeUnits(convFactor, tempTo.lenght);
				convFactor = Math.pow(convFactor, LENGHT_DIMENSION_EXPONENT);
				newValue *= convFactor;
			}
			
		}else {
			
			if (tempTo.lenght != null) {//original = conventional, destination = conventional
				convFactor = lenght.changeUnits(convFactor, tempTo.lenght);
				convFactor = Math.pow(convFactor, LENGHT_DIMENSION_EXPONENT);
				newValue *= convFactor;
			}else {// original = conventional, destination = non-conventional
				newValue = toCubicFeet(newValue, this);//converting to cubic feet
				newValue /= tempTo.toCubicFoot;//converting to no-conventional unit
			}
		}
		return newValue;
	}

}
