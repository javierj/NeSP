package mdetest.metamodels.base;

/**
 * @author Rincew
 * @version 1.0
 * @created 13-abr-2010 12:11:28
 */
public class NamedElement extends Element {

	private String name;
	private String description;
	private Package inPackage;

	public NamedElement(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Package getInPackage() {
		return inPackage;
	}

	public void setInPackage(Package inPackage) {
		this.inPackage = inPackage;
	}

}//end NamedElement