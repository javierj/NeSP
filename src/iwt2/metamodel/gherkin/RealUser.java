package iwt2.metamodel.gherkin;

/**
 * Este nombre no me convence mucho, a ver is lo cambio.
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class RealUser {

	String name;
	Feature m_Feature;
	//public SystemActor exampleOf;

	public RealUser(){

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Feature getFeature() {
		return m_Feature;
	}

	public void setFeature(Feature m_Feature) {
		this.m_Feature = m_Feature;
	}
	
	

}//end RealUser