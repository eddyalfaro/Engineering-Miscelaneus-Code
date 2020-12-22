package constants;

import java.util.HashMap;

import constants.interfaces.Mapping;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum ForceUnits implements UnitChanger{
	NEWTON("N", LenghtUnits.METER, MassUnits.KILOGRAM , TimeUnits.SECONDS, true),
	DYNES("dyn", LenghtUnits.CENTIMETER, MassUnits.GRAM , TimeUnits.SECONDS, false),
	KILOPOND("kp", false),
	POUNDFORCE("lbf", false),
	POUNDAL("pdl", LenghtUnits.FOOT, MassUnits.POUND , TimeUnits.SECONDS, false);
	
	public static final int LENGHT_DIMENSION_EXPONENT = 1;
	public static final int MASS_DIMENSION_EXPONENT = 1;
	public static final int TIME_DIMENSION_EXPONENT = -2;
	public static final int CURRENT_DIMENSION_EXPONENT = 0;
	public static final int TEMPERATURE_DIMENSION_EXPONENT = 0;
	public static final int MOLE_DIMENSION_EXPONENT = 0;
	public static final int LUMINOUSITY_DIMENSION_EXPONENT = 0;
	
	
	private static final HashMap<ForceUnits, Double> FROM_NEWTON = 
			Mapping.generateHashMap(new Double[] {1.0, 1E5, 0.10197, 0.22481, 7.2330}, ForceUnits.values());
	private static final HashMap<ForceUnits, Double> FROM_DYNE = 
			Mapping.generateHashMap(new Double[] {1E-5, 1.0, 1.0197E-6, 2.2481E-6, 7.2330E-5}, ForceUnits.values());
	private static final HashMap<ForceUnits, Double> FROM_KILOPOND = 
			Mapping.generateHashMap(new Double[] {9.80665, 980665.0, 1.0, 2.2046, 70.932}, ForceUnits.values());
	private static final HashMap<ForceUnits, Double> FROM_POUNDFORCE = 
			Mapping.generateHashMap(new Double[] {4.448221615255, 444822.1615255, 0.45359, 1.0, 32.17404855639}, ForceUnits.values());
	private static final HashMap<ForceUnits, Double> FROM_POUNDAL = 
			Mapping.generateHashMap(new Double[] {0.138255, 13825.5, 0.014098, 0.031081, 1.0}, ForceUnits.values());

	private LenghtUnits lenght = null;
	private MassUnits mass = null;
	private TimeUnits time = null;
	
	private String symbol;
	private boolean isSI;
	
	private ForceUnits(String symbol, LenghtUnits lenght, MassUnits mass, TimeUnits time, boolean isSI) {
		this.lenght = lenght;
		this.mass = mass;
		this.time = time;
		this.symbol = symbol;
		this.isSI = isSI;
	}
	
	private ForceUnits(String symbol, boolean isSI) {
		this.symbol = symbol;
		this.isSI = isSI;
	}

	public LenghtUnits getLenghtUnits() {
		return lenght;
	}

	public MassUnits getMassUnits() {
		return mass;
	}

	public TimeUnits getTimeUnits() {
		return time;
	}

	public boolean isSI() {
		return isSI;
	}
	
	public String toString() {
		return symbol;
	}
	
	public ForceUnits getISU() {
		return NEWTON;
	}
	
	@Override
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		if (!(to instanceof ForceUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}		
		
		double newValue = value;
		ForceUnits tempTo = (ForceUnits) to;
		
		//System.out.println(newValue);
		
		switch(this) {
		case NEWTON:
			newValue *= FROM_NEWTON.get(tempTo);				
			break;
		case DYNES:
			newValue *= FROM_DYNE.get(tempTo);
			break;
		case KILOPOND:
			newValue *= FROM_KILOPOND.get(tempTo);
			break;
		case POUNDFORCE:
			newValue *= FROM_POUNDFORCE.get(tempTo);
			break;
		case POUNDAL:
			newValue *= FROM_POUNDAL.get(tempTo);
			break;
		}
		
		return newValue;
	}
}
