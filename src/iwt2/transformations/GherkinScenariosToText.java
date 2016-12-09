package iwt2.transformations;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import iwt2.metamodel.gherkin.Feature;
import iwt2.concretesyntax.eap.EAPScenariosDAO;
import iwt2.concretesyntax.freemaker.FMTemplate;
import mdetest.concretesyntax.eap.EAPConnectionFacade;
import mdetest.concretesyntax.eap.EAPFunctionalRequirementDAO;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;


/**
 * Extrat use cases and the attached gherkin scenarios and store them into a file
 * @author Javier
 *
 */
public class GherkinScenariosToText {

	List<FunctionalRequirement> readFunctionalRequirements(String packageName) {
		EAPFunctionalRequirementDAO frDAO = EAPConnectionFacade.getEAPFunctionalRequirementDAO();
		String pbId = EAPConnectionFacade.getEAPPackageDAO().idFor(packageName);
		List<FunctionalRequirement> frs =  frDAO.getAllFunctionalRequirementsIn(null, pbId);
		
		return frs;
	}
	
	
	List<Feature> readFeaturesWithScenarios(List<FunctionalRequirement> frs) {
		EAPScenariosDAO scenariosDAO = new EAPScenariosDAO(EAPConnectionFacade.Connection());
		
		List<Feature> features = new ArrayList<>();
		Feature feature;
		FunctionalRequirementToFeature FR_2_F = new FunctionalRequirementToFeature();
		
		for (FunctionalRequirement fr: frs) {
			feature = FR_2_F.transform(fr);
			features.add(feature);
			
			// Añadir los unos a los otros y leer el Background por separado
			feature.setScenarios(scenariosDAO.getScenariosLinkedTo(fr));
			feature.setScenarios(scenariosDAO.getScenariosSonsOf(fr));
		}
		
		return features;
	}
	
	
	
	public void saveToText(String eapFile, String packageName) {
		EAPConnectionFacade.Connect(eapFile);
		
		List<FunctionalRequirement> frs = this.readFunctionalRequirements(packageName);
		List<Feature> features = this.readFeaturesWithScenarios(frs);
		
		
		FMTemplate template = new FMTemplate();
		template.setTemplateFile("scenario_notes.freemaker");
        for(Feature feat: features) {
        	Map<String, Object> root = new HashMap<>();
	        root.put("scenarios", feat.scenarios());
	        
        	template.processToConsole(root);
        }
	}
	
	
	//------------------------------------------
	
	public static void main(String[] args) {
		GherkinScenariosToText stot = new GherkinScenariosToText();
		stot.saveToText("./test/resources/Scenarios 03.EAP", "UC - Tournament system");
	}
}
