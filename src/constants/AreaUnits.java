package constants;

import java.util.HashMap;

import constants.interfaces.Mapping;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum AreaUnits implements UnitChanger{
	
	SQMETER(LenghtUnits.METER, true),
	SQINCH(LenghtUnits.INCH, false),
	SQFEET(LenghtUnits.FOOT, false),
	SQYARD(LenghtUnits.YARD, false),
	SQMILE(LenghtUnits.MILE, false),
	HECTARE("hectare", false),
	ACRE("acre", false);
	
	private static final AreaUnits[] UNITS = {SQMETER, HECTARE, ACRE};
	
	public static final int LENGHT_DIMENSION_EXPONENT = 2;
	public static final int MASS_DIMENSION_EXPONENT = 0;
	public static final int TIME_DIMENSION_EXPONENT = 0;
	public static final int CURRENT_DIMENSION_EXPONENT = 0;
	public static final int TEMPERATURE_DIMENSION_EXPONENT = 0;
	public static final int MOLE_DIMENSION_EXPONENT = 0;
	public static final int LUMINOUSITY_DIMENSION_EXPONENT = 0;
	
	private static final HashMap<AreaUnits, Double> FROM_HECTARE = 
			Mapping.generateHashMap(new Double[] {1e4, 1.0, 1/0.404686}, UNITS);
	private static final HashMap<AreaUnits, Double> FROM_SQMETER = 
			Mapping.generateHashMap(new Double[] {1.0, 1E-4, 1/4046.86}, UNITS);
	private static final HashMap<AreaUnits, Double> FROM_ACRE = 
			Mapping.generateHashMap(new Double[] {4046.86, 4046.86E-4, 1.0}, UNITS);
	
	private static final HashMap<LenghtUnits, AreaUnits> ASOCIATION =
			Mapping.generateHashMap(new AreaUnits[] {SQMETER, SQINCH, SQFEET, SQYARD, SQMILE}, 
					new LenghtUnits[] {LenghtUnits.METER, LenghtUnits.INCH, LenghtUnits.FOOT, LenghtUnits.YARD, LenghtUnits.MILE});
	
	private LenghtUnits unit = null;
	private String symbol;
	private boolean isSI;
	
	private AreaUnits(LenghtUnits unit, boolean isSI) {
		this.unit = unit;
		this.isSI = isSI;
	}
	
	private AreaUnits(String symbol, boolean isSI) {
		this.symbol = symbol;
		this.isSI = isSI;
	}
	
	public static AreaUnits getAreaUnit(LenghtUnits lenght) {
		return ASOCIATION.get(lenght);
	}
	
	public LenghtUnits getLengthUnit() {
		return unit;
	}

	public boolean isSI() {
		return isSI;
	}
	
	public String toString() {
		if (unit == null) {
			return symbol;
		}
		
		return "sq" + unit.toString();
	}
	
	public AreaUnits getISU() {
		return SQMETER;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		if (!(to instanceof AreaUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}		
		
		double newValue = value;	
		AreaUnits tempTo = (AreaUnits) to;
		double convFactor = 1.0;
		
		if (unit == null) {//if source unit is either heactare or acre
			
			//if the target unit is not sqmeter and is not hectare or acre
			if (tempTo != SQMETER && tempTo.unit != null) {
				tempTo = SQMETER;
			}
			
			//convert from hectare or acre to acre, hectare or sqmeter
			switch(this) {
				case HECTARE:
					newValue *= FROM_HECTARE.get(tempTo);
					break;
				case ACRE:
					newValue *= FROM_ACRE.get(tempTo);
					break;
			}
			
			tempTo = (AreaUnits) to;
			//if the target unit is not sqmeter and is not hectare or acre
			if (tempTo != SQMETER && tempTo.unit != null) {
				convFactor *= SQMETER.unit.changeUnits(convFactor, tempTo.unit);
				convFactor = Math.pow(convFactor, LENGHT_DIMENSION_EXPONENT);
				
				newValue *= convFactor;
			}
			
		}else {//if source unit is neither hectare or acre
			
			if (tempTo.unit != null) {//if target unit is neither hectare or acre
				convFactor *= unit.changeUnits(convFactor, tempTo.unit);
				convFactor = Math.pow(convFactor, LENGHT_DIMENSION_EXPONENT);
			}else {//if target unit is either hectare or acre
				convFactor *= unit.changeUnits(convFactor, SQMETER.unit);
				convFactor = Math.pow(convFactor, LENGHT_DIMENSION_EXPONENT);
				convFactor *= FROM_SQMETER.get(tempTo);
			}
			
			newValue *= convFactor;			
		}
		
		return newValue;
	}
}
