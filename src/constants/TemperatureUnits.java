package constants;

import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum TemperatureUnits implements UnitChanger{
	
	CELCIUS("C", false, false),
	KELVIN("K", true, true),
	FARENHEIT("F", false, false),
	RANKINE("R", false, true);
	
	public static final int LENGHT_DIMENSION_EXPONENT = 0;
	public static final int MASS_DIMENSION_EXPONENT = 0;
	public static final int TIME_DIMENSION_EXPONENT = 0;
	public static final int CURRENT_DIMENSION_EXPONENT = 0;
	public static final int TEMPERATURE_DIMENSION_EXPONENT = 1;
	public static final int MOLE_DIMENSION_EXPONENT = 0;
	public static final int LUMINOUSITY_DIMENSION_EXPONENT = 0;
	
	private String symbol;
	private boolean isSI;
	private boolean isAbsolute;
	
	private TemperatureUnits(String symbol, boolean isSI, boolean isAbsolute) {
		this.symbol = symbol;
		this.isSI = isSI;
		this.isAbsolute = isAbsolute;
	}
	
	public static TemperatureUnits getISU() {
		return KELVIN;
	}
	
	public boolean isAbsolute() {
		return isAbsolute;
	}
	
	public boolean isSI() {
		return isSI;
	}
	
	public String toString() {
		return this.symbol;
	}
	
	private static double fromCtoK(Double value) {
		return (value + 273.15);
	}
	
	private static double fromCtoF(Double value) {
		return (1.8*value + 32.0);
	}
	
	private static double fromCtoR(Double value) {
		return fromFtoR(fromCtoF(value));
	}
	
	private static double fromFtoR(Double value) {
		return (value + 459.67);
	}

	private static double fromFtoC(Double value) {
		return (value - 32.0)/1.8;
	}
	
	private static double fromFtoK(Double value) {
		return fromCtoK(fromFtoC(value));
	}
	
	private static double fromKtoC(Double value) {
		return (value - 273.15);
	}
	
	private static double fromKtoF(Double value) {
		return fromCtoF(fromKtoC(value));
	}
	
	private static double fromKtoR(Double value) {
		return fromFtoR(fromKtoF(value));
	}
	
	private static double fromRtoF(Double value) {
		return (value - 459.67);
	}
	
	private static double fromRtoC(Double value) {
		return fromFtoC(fromRtoF(value));
	}
	
	private static double fromRtoK(Double value) {
		return fromCtoK(fromRtoC(value));
	}

	@Override
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		
		if (!(to instanceof TemperatureUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}
		
		double newValue = value;
		TemperatureUnits tempTo = (TemperatureUnits) to;
		
		switch(this) {
		
		case CELCIUS:	
			switch(tempTo) {
			case CELCIUS:
				break;
			case FARENHEIT:
				newValue = fromCtoF(newValue);
				break;
			case KELVIN:
				newValue = fromCtoK(newValue);
				break;
			case RANKINE:
				newValue = fromCtoR(newValue);
				break;
			}
			break;
			
		case FARENHEIT:	
			switch(tempTo) {
			case CELCIUS:
				newValue = fromFtoC(newValue);
				break;
			case FARENHEIT:
				break;
			case KELVIN:
				newValue = fromFtoK(newValue);
				break;
			case RANKINE:
				newValue = fromFtoR(newValue);
				break;
			}
			break;
			
		case KELVIN:	
			switch(tempTo) {
			case CELCIUS:
				newValue = fromKtoC(newValue);
				break;
			case FARENHEIT:
				newValue = fromKtoF(newValue);
				break;
			case KELVIN:
				break;
			case RANKINE:
				newValue = fromKtoR(newValue);
				break;
			}
			break;
			
		case RANKINE:	
			switch(tempTo) {
			case CELCIUS:
				newValue = fromRtoC(newValue);
				break;
			case FARENHEIT:
				newValue = fromRtoF(newValue);
				break;
			case KELVIN:
				newValue = fromRtoK(newValue);
				break;
			case RANKINE:
				break;
			}
		}
		
		return newValue;
	}
	

}
