package constants;

import java.util.HashMap;

import constants.interfaces.Mapping;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum LenghtUnits implements UnitChanger{
	
	METER("m", true),
	CENTIMETER(METER, SIUnits.CENTI, false),
	FOOT("ft", false),
	INCH("in", false),
	YARD("yd", false),
	MILE("mile", false);
	
	private String symbol;
	private boolean isSI;
	private static final LenghtUnits[] UNITS = {METER, FOOT, INCH, YARD, MILE};
	
	private LenghtUnits unit = null;
	private SIUnits factor = null;
	
	private static HashMap<LenghtUnits, Double> fromMeter = 
			Mapping.generateHashMap(new Double[] {1.0 , 1/0.3048, 1/0.0254, 1/0.9144, 1/1609.0}, UNITS);
	private static HashMap<LenghtUnits, Double> fromFoot = 
			Mapping.generateHashMap(new Double[] {0.3048, 1.0, 12.0, 1/3.0, 1/5280.0}, UNITS);
	private static HashMap<LenghtUnits, Double> fromInch = 
			Mapping.generateHashMap(new Double[] {0.0254, 1/12.0, 1.0, 1/36.0, 1/63360.0}, UNITS);
	private static HashMap<LenghtUnits, Double> fromYard = 
			Mapping.generateHashMap(new Double[] {0.9144, 3.0, 36.0, 1.0, 1/1760.0}, UNITS);
	private static HashMap<LenghtUnits, Double> fromMile = 
			Mapping.generateHashMap(new Double[] {1609.0, 5280.0, 63360.0, 1760.0, 1.0}, UNITS);
	
	private LenghtUnits(String symbol, boolean isSI) {
		this.symbol = symbol;
		this.isSI = isSI;
	}
	
	private LenghtUnits(LenghtUnits unit, SIUnits factor, boolean isSI) {
		this.unit = unit;
		this.factor = factor;
		this.isSI = isSI;
	}
	
	public boolean isSI() {
		return isSI;
	}
	
	public LenghtUnits getISU() {
		return METER;
	}
	
	public String toString() {
		if (factor == null) {
			return symbol;
		}
		
		return "" + factor + unit;
	}
	
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		
		if (!(to instanceof LenghtUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}		
		
		double newValue = value;	
		LenghtUnits tempTo = (LenghtUnits) to;
		
		if (factor != null) {
			newValue = this.factor.removeSIFactor(value);
		}
		
		if (tempTo == CENTIMETER) {
			tempTo = METER;
		}
		
		switch(this) {
			case METER:
				newValue *= fromMeter.get(tempTo);
				break;
			case CENTIMETER:
				newValue *= fromMeter.get(tempTo);
				break;
			case FOOT:
				newValue *= fromFoot.get(tempTo);
				break;
			case INCH:
				newValue *= fromInch.get(tempTo);
				break;
			case YARD:
				newValue *= fromYard.get(tempTo);
				break;
			case MILE:
				newValue *= fromMile.get(tempTo);
				break;
		}
		
		tempTo = (LenghtUnits) to;
		
		if (tempTo == CENTIMETER) {
			newValue = tempTo.factor.addSIFactor(newValue);
		}
		
		return newValue;
	}
}