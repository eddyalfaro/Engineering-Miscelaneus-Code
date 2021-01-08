package properties.abstracts;

import constants.interfaces.UnitChanger;

public abstract class PropertyTwo <Unit1, Unit2 extends UnitChanger> 
												implements Comparable<PropertyTwo<Unit1, Unit2>>{
	
	public PropertyTwo() {
		
	}

}
