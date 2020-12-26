package constants.interfaces;

import exceptions.NonSIException;

public interface SIFactor {
	
	public <E extends Enum<E>> double addSIFactor(Double value);
	
	public <E extends Enum<E>> double addSIFactor(Double value, int powerOf);
	
	public <E extends Enum<E>> double removeSIFactor(Double value);

	public <E extends Enum<E>> double removeSIFactor(Double value, int powerOf);

	public <E extends Enum<E>> double changeTo(Double value, E to) throws NonSIException;
	
	public <E extends Enum<E>> double changeTo(Double value, E to, int powerOf) throws NonSIException;

}
