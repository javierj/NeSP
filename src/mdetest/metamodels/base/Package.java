package mdetest.metamodels.base;

import java.util.List;

/**
 * @author Rincew
 * @version 1.0
 * @created 13-abr-2010 12:11:29
 */
public class Package extends NamedElement {

	protected List<Element> elements;

	public Package(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((elements == null) ? 0 : elements.hashCode());
		result *= super.getName().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Package other = (Package) obj;
		/*if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;*/
		return this.getName().equals(other.getName());
	}

	@Override
	public String toString() {
		return "Package ["
				//+ (elements != null ? "elements=" + elements + ", " : "")
				+ (getName() != null ? "getName()=" + getName() : "") + "]";
	}

}//end Package