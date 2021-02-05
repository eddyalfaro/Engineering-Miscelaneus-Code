package constants;

import java.util.HashMap;

import constants.interfaces.Mapping;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum MassUnits implements UnitChanger{
	
	GRAM ("g", true),
	KILOGRAM(GRAM, SIUnits.KILO, true),
	POUND ("lb", false),
	SLUG ("sl", false),
	METRIC_TON ("metric ton", false),
	ENGLISH_TON ("english ton", false);
	
	public static final int LENGHT_DIMENSION_EXPONENT = 0;
	public static final int MASS_DIMENSION_EXPONENT = 1;
	public static final int TIME_DIMENSION_EXPONENT = 0;
	public static final int CURRENT_DIMENSION_EXPONENT = 0;
	public static final int TEMPERATURE_DIMENSION_EXPONENT = 0;
	public static final int MOLE_DIMENSION_EXPONENT = 0;
	public static final int LUMINOUSITY_DIMENSION_EXPONENT = 0;
	
	private String symbol;
	private boolean isSI;
	private SIUnits factor = null;
	private MassUnits unit;
	private static MassUnits[] units = {GRAM, POUND, SLUG, METRIC_TON, ENGLISH_TON};
			
	private static HashMap<MassUnits, Double> fromGram = 
			Mapping.generateHashMap(new Double[] {1.0 , 0.0022046226, 0.00006852177, 0.000001, 0.000001102311}, units);
	private static HashMap<MassUnits, Double> fromPound = 
			Mapping.generateHashMap(new Double[] {453.5924, 1.0, 0.0310809531, 0.0004535924, 0.0005}, units);
	private static HashMap<MassUnits, Double> fromSlug = 
			Mapping.generateHashMap(new Double[] {14593.9, 32.17405, 1.0, 0.0145939, 0.01608702}, units);
	private static HashMap<MassUnits, Double> fromMetricTonne = 
			Mapping.generateHashMap(new Double[] {1000000.0, 2204.623, 68.52177, 1.0, 1.102311}, units);
	private static HashMap<MassUnits, Double> fromEnglishTonne = 
			Mapping.generateHashMap(new Double[] {907184.7, 2000.0, 62.1619, 0.9071847, 1.0}, units);

	private MassUnits(String unit, boolean isSI) {
		symbol = unit;
		this.isSI = isSI;
	}
	
	private MassUnits(MassUnits unit, SIUnits factor, boolean isSI) {
		this.unit = unit;
		this.factor = factor;
		this.isSI = isSI;
	}
	
	public static MassUnits getISU() {
		return KILOGRAM;
	}
	
	public boolean isSI() {
		return isSI;
	}
	
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		
		if (!(to instanceof MassUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}
		
		double newValue = value;
		MassUnits tempTo = (MassUnits) to;
		//System.out.println("converting " + value + from + " to " + to);
		
		if (factor != null) {
			newValue = this.factor.removeSIFactor(value);
		}
		
		if (tempTo == KILOGRAM) {
			tempTo = GRAM;
		}
		
		switch(this) {
			case GRAM:
				//System.out.println("Factor conversion of " + fromGram.get(to));
				newValue *= fromGram.get(tempTo);
				break;
			case KILOGRAM:
				//System.out.println("Factor conversion of " + fromGram.get(to));
				newValue *= fromGram.get(tempTo);
				break;
			case POUND:
				//System.out.println("Factor conversion of " + fromPound.get(to));
				newValue *= fromPound.get(tempTo);
				break;
			case SLUG:
				//System.out.println("Factor conversion of " + fromSlug.get(to));
				newValue *= fromSlug.get(tempTo);
				break;
			case METRIC_TON:
				//System.out.println("Factor conversion of " + fromMetricTonne.get(to));
				newValue *= fromMetricTonne.get(tempTo);
				break;
			case ENGLISH_TON:
				//System.out.println("Factor conversion of " + fromEnglishTonne.get(to));
				newValue *= fromEnglishTonne.get(tempTo);
				break;
		}

		tempTo = (MassUnits) to;
		
		if (tempTo == KILOGRAM) {
			newValue = tempTo.factor.addSIFactor(newValue);
		}
		//System.out.println("" + value + from + " is equivalent to " + newValue + to + '\n');
		
		return newValue;
	}
	
	public String toString() {
		if (factor == null) {
			return symbol;
		}
		
		return "" + factor + unit;
	}

}
