package iwt2.transformations;

import iwt2.metamodel.gherkin.Feature;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;

public class FunctionalRequirementToFeature {

	public Feature transform(FunctionalRequirement fr) {
		Feature feature = new Feature();
		feature.setFunctionalRequirement(fr);
		feature.setName(fr.getName());
		
		return feature;
	}
	
}
