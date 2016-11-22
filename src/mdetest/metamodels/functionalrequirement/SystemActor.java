package mdetest.metamodels.functionalrequirement;

import java.util.List;
import mdetest.metamodels.base.NamedElement;

/**
 * @version 1.0
 * @created 13-abr-2010 12:11:32
 */
public class SystemActor extends NamedElement {

	@Override
	public String toString() {
		return (getName() != null ? getName() : "")
				+ (getDescription() != null ? " : "
						+ getDescription() + ", " : "")
				;
	}

	private List<Step> iteraction;
	//public FunctionalRequirement colabora;

	public SystemActor(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}//end SystemActor