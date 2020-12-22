package constants.interfaces;

import exceptions.NonSIFactorException;

public interface SIFactor {
	
	public <E extends Enum<E>> double addSIFactor(Double value);
	
	public <E extends Enum<E>> double addSIFactor(Double value, int powerOf);
	
	public <E extends Enum<E>> double removeSIFactor(Double value);

	public <E extends Enum<E>> double removeSIFactor(Double value, int powerOf);

	public <E extends Enum<E>> double changeTo(Double value, E to) throws NonSIFactorException;
	
	public <E extends Enum<E>> double changeTo(Double value, E to, int powerOf) throws NonSIFactorException;

}
