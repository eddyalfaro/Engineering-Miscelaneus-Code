package constants;

import java.util.HashMap;

import constants.interfaces.Mapping;
import constants.interfaces.UnitChanger;
import exceptions.InvalidInputException;

public enum PressureUnits implements UnitChanger{
	
	PASCAL("Pa", true),
	BAR("bar", false),
	ATMOSPHERE("atm", false),
	TORR("torr", false),
	PSI("psi", ForceUnits.POUNDFORCE , AreaUnits.SQINCH),
	BARYE("dyne/sqcm", PASCAL, SIUnits.DECI, false);
		
	public static final int LENGHT_DIMENSION_EXPONENT = -1;
	public static final int MASS_DIMENSION_EXPONENT = 1;
	public static final int TIME_DIMENSION_EXPONENT = -2;
	public static final int CURRENT_DIMENSION_EXPONENT = 0;
	public static final int TEMPERATURE_DIMENSION_EXPONENT = 0;
	public static final int MOLE_DIMENSION_EXPONENT = 0;
	public static final int LUMINOUSITY_DIMENSION_EXPONENT = 0;
	
	private static final HashMap<PressureUnits, Double> FROM_PASCAL = 
			Mapping.generateHashMap(new Double[] {1.0, 1e-5, 9.8692e-6, 7.5006e-3, 145.04e-6, 10.0}, PressureUnits.values());
	private static final HashMap<PressureUnits, Double> FROM_BAR = 
			Mapping.generateHashMap(new Double[] {1e5, 1.0, 0.98692, 750.06, 14.504, 1e6}, PressureUnits.values());
	private static final HashMap<PressureUnits, Double> FROM_ATM = 
			Mapping.generateHashMap(new Double[] {101325.0, 1.01325, 1.0, 760.0, 14.696, 1013250.0}, PressureUnits.values());
	private static final HashMap<PressureUnits, Double> FROM_TORR = 
			Mapping.generateHashMap(new Double[] {133.322, 1.3332e-3, 1.3158e-3, 1.0, 19.337e-3, 1333.22}, PressureUnits.values());
	private static final HashMap<PressureUnits, Double> FROM_PSI = 
			Mapping.generateHashMap(new Double[] {6894.76, 68.948e-3, 68.046e-3, 51.715, 1.0, 68947.6}, PressureUnits.values());
	private static final HashMap<PressureUnits, Double> FROM_BARYE = 
			Mapping.generateHashMap(new Double[] {1e-1, 1e-6, 1/101325.0, 1/1333.22, 1/68947.6, 1.0}, PressureUnits.values());	
	
	private String symbol;
	private ForceUnits force;
	private AreaUnits area;
	private PressureUnits pressure;
	private SIUnits factor = null;
	private boolean isSI;
	
	private PressureUnits(String symbol, boolean isSI) {
		this.symbol = symbol;
		this.isSI = isSI;
	}
	
	private PressureUnits(String symbol, PressureUnits unit, SIUnits factor, boolean isSI) {
		this(symbol, isSI);
		this.pressure = unit;
		this.factor = factor;
	}
	
	private PressureUnits(String symbol, ForceUnits force, AreaUnits area) {
		this(symbol, false);
		this.force = force;
		this.area = area;
	}
	
	public PressureUnits getISU() {
		return PASCAL;
	}
	
	public ForceUnits getForceUnits() {
		return force;
	}

	public AreaUnits getAreaUnits() {
		return area;
	}

	public PressureUnits getPressureUnits() {
		return pressure;
	}

	public SIUnits getSIFactor() {
		return factor;
	}

	public boolean isSI() {
		return isSI;
	}
	
	@Override
	public <E extends Enum<E>> double changeUnits(Double value, E to) throws InvalidInputException {
		if (!(to instanceof PressureUnits)){
			throw new InvalidInputException(InvalidInputException.ERROR1 + this.getDeclaringClass()); 
		}		
		
		double newValue = value;	
		PressureUnits tempTo = (PressureUnits) to;
		
		switch(this) {
		case PASCAL:
			newValue *= FROM_PASCAL.get(tempTo);				
			break;
		case BAR:
			newValue *= FROM_BAR.get(tempTo);
			break;
		case ATMOSPHERE:
			newValue *= FROM_ATM.get(tempTo);
			break;
		case TORR:
			newValue *= FROM_TORR.get(tempTo);
			break;
		case PSI:
			newValue *= FROM_PSI.get(tempTo);
			break;
		case BARYE:
			newValue *= FROM_BARYE.get(tempTo);
			break;
		}
		
		return newValue;
	}

	public String toString() {
		return symbol;
	}
}
