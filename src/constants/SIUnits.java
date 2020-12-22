package constants;

import constants.interfaces.SIFactor;
import exceptions.NonSIFactorException;

public enum SIUnits implements SIFactor{
	
	YOTTA("Y", 1e24),
	ZETTA("Z", 1e21),
	EXA("E", 1e18),
	PETA("P", 1e15),
	TERA("T", 1e12),
	GIGA("G", 1e9),
	MEGA("M", 1e6),
	KILO("k", 1e3),
	HECTO("H", 1e2),
	DEKA("D", 1e1),
	DECI("d", 1e-1),
	CENTI("c", 1e-2),
	MILI("m", 1e-3),
	MICRO("u", 1e-6),
	NANO("n", 1e-9),
	PICO("p", 1e-12),
	FEMTO("f", 1e-15),
	ATTO("a", 1e-18),
	ZEPTO("z", 1e-21),
	YOCTO("y", 1e-24);
	
	private String symbol;
	private double factor;
	
	private SIUnits(String symbol, double factor) {
		this.symbol = symbol;
		this.factor = factor;
	}
	
	public String toString() {
		return symbol;
	}

	/**
	 * Add a SI factor to the given value. The returning value would be in SI units
	 */
	@Override
	public <E extends Enum<E>> double addSIFactor(Double value) {
		return (value / this.factor);
	}

	/**
	 * Remove a SI factor to the given value. The returning value would be in its regular units
	 */
	@Override
	public <E extends Enum<E>> double removeSIFactor(Double value) {
		return (value * this.factor);
	}

	/**
	 * Changes SI factor of the given value. The returning value would be in the given SI units
	 */
	@Override
	public <E extends Enum<E>> double changeTo(Double value, E to) throws NonSIFactorException{
		value = removeSIFactor(value);
		if (!(to instanceof SIUnits)) {
			throw new NonSIFactorException("input <E> does not corresponds to an type SIUnit");
		}
		return (value / ((SIUnits) to).factor);	
	}

	/**
	 * Add a SI factor to the given value. The returning value would be in SI units to the given power
	 */
	@Override
	public <E extends Enum<E>> double addSIFactor(Double value, int powerOf) {
		return (value/Math.pow(this.factor, powerOf));
	}

	/**
	 * Remove a SI factor to the given value. The returning value would be in 
	 its regular units to the given power
	 */
	@Override
	public <E extends Enum<E>> double removeSIFactor(Double value, int powerOf) {
		return (value*Math.pow(this.factor, powerOf));
	}

	/**
	 * Changes SI factor of the given value. The returning value would be in the given 
	 SI units to the given power
	 */
	@Override
	public <E extends Enum<E>> double changeTo(Double value, E to, int powerOf) throws NonSIFactorException {
		value = removeSIFactor(value, powerOf);
		
		if (!(to instanceof SIUnits)) {
			throw new NonSIFactorException("input <E> does not corresponds to an type SIUnit");
		}
		
		return (value / Math.pow(((SIUnits) to).factor, powerOf));	
	}
}
