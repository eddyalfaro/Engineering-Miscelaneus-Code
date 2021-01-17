package constants;

import java.util.HashMap;

import constants.interfaces.Mapping;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum TimeUnits implements UnitChanger{
	
	SECONDS("s", true),
	MINUTES("m", false),
	HOURS("h", false),
	DAYS("d", false);
	
	private static final HashMap<TimeUnits, Double> FROM_SECONDS = 
			Mapping.generateHashMap(new Double[] {1.0, 1/60.0, 1/3600.0, 1/(3600.0*24.0)}, TimeUnits.values());
	private static final HashMap<TimeUnits, Double> FROM_MINUTES = 
			Mapping.generateHashMap(new Double[] {60.0, 1.0, 1/60.0, 1/(60.0*24.0)}, TimeUnits.values());
	private static final HashMap<TimeUnits, Double> FROM_HOURS = 
			Mapping.generateHashMap(new Double[] {3600.0, 60.0, 1.0, 1/24.0}, TimeUnits.values());
	private static final HashMap<TimeUnits, Double> FROM_DAYS = 
			Mapping.generateHashMap(new Double[] {24*3600.0, 24*60.0, 24.0, 1.0}, TimeUnits.values());
	
	public static final int LENGHT_DIMENSION_EXPONENT = 0;
	public static final int MASS_DIMENSION_EXPONENT = 0;
	public static final int TIME_DIMENSION_EXPONENT = 1;
	public static final int CURRENT_DIMENSION_EXPONENT = 0;
	public static final int TEMPERATURE_DIMENSION_EXPONENT = 0;
	public static final int MOLE_DIMENSION_EXPONENT = 0;
	public static final int LUMINOUSITY_DIMENSION_EXPONENT = 0;
	
	private String symbol;
	private boolean isSI;
	
	private TimeUnits(String symbol, boolean isSI) {
		this.symbol = symbol;
		this.isSI = isSI;
	}
	
	public static TimeUnits getISU() {
		return SECONDS;
	}
	
	public boolean isSI() {
		return isSI;
	}
	
	public String toString() {
		return symbol;
	}

	@Override
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		
		if (!(to instanceof TimeUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}		
		
		double newValue = value;
		TimeUnits tempTo = (TimeUnits) to;
		
		//System.out.println(newValue);
		
		switch(this) {
		case SECONDS:
			newValue *= FROM_SECONDS.get(tempTo);				
			break;
		case MINUTES:
			newValue *= FROM_MINUTES.get(tempTo);
			break;
		case HOURS:
			newValue *= FROM_HOURS.get(tempTo);
			break;
		case DAYS:
			newValue *= FROM_DAYS.get(tempTo);
			break;
		}
		
		return newValue;
		
	}
}
