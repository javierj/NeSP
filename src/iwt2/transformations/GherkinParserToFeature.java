package iwt2.transformations;

import java.util.ArrayList;
import java.util.List;

import gherkin.ast.ScenarioDefinition;
import iwt2.metamodel.gherkin.Feature;
import iwt2.metamodel.gherkin.Scenario;

/**
 * This transformation creates a model from the gherkin metamodel from the AST if a cherking
 * Fwarure.
 * The gherkin feautre is readed from a text file using the gherkin parser.
 * 
 * Limitations:
 * 
 * @author Javier
 *
 */
public class GherkinParserToFeature {

	public Feature transform(gherkin.ast.Feature gherkin_feature) {
		Feature feature = gherkinFeature_to_Feature(gherkin_feature);
		feature.setScenarios(gherkinScenarios_to_Scenarios(gherkin_feature));
		return feature;
	}

	private List<Scenario> gherkinScenarios_to_Scenarios(gherkin.ast.Feature gherkin_feature) {
		List<Scenario> scens = new ArrayList<>();
		//;
		for (gherkin.ast.ScenarioDefinition scenario: gherkin_feature.getChildren()) {
			scens.add(gherkinScenario_to_Scenario(scenario));
		}
		
		return scens;
	}

	private Scenario gherkinScenario_to_Scenario(ScenarioDefinition scenario) {
		Scenario scen = new Scenario();
		return scen;
	}

	private Feature gherkinFeature_to_Feature(gherkin.ast.Feature gherkin_feature) {
		Feature feature = new Feature();
		feature.setName(gherkin_feature.getName());
		feature.setIWant(gherkin_feature.getDescription().split("\n")[1]);
		feature.setInOrderTo(gherkin_feature.getDescription().split("\n")[2]);
		feature.setDescription(gherkin_feature.getDescription());
		return feature;
	}
	
}
