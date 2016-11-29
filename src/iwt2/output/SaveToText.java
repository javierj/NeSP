package iwt2.output;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import iwt2.metamodel.gherkin.Feature;
import iwt2.metamodel.gherkin.Given;
import iwt2.metamodel.gherkin.Scenario;
import iwt2.concretesyntax.eap.EAPScenariosDAO;
import iwt2.concretesyntax.freemaker.FMTemplate;
import mdetest.concretesyntax.eap.EAPConnectionFacade;
import mdetest.concretesyntax.eap.EAPFunctionalRequirementDAO;
import mdetest.concretesyntax.eap.EAPPackageDAO;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;
import mdetest.metamodels.functionalrequirement.Subsystem;

public class SaveToText {

	List<FunctionalRequirement> readScenarios(String packageName) {
		EAPFunctionalRequirementDAO frDAO = EAPConnectionFacade.getEAPFunctionalRequirementDAO();
		String pbId = EAPConnectionFacade.getEAPPackageDAO().idFor(packageName);
		List<FunctionalRequirement> frs =  frDAO.getAllFunctionalRequirementsIn(null, pbId);
		
		return frs;
	}
	
	
	List<Feature> readFeaturesWithScenarios(List<FunctionalRequirement> frs) {
		EAPScenariosDAO scenariosDAO = new EAPScenariosDAO(EAPConnectionFacade.Connection());
		
		List<Feature> features = new ArrayList<>();
		Feature feature;
		
		for (FunctionalRequirement fr: frs) {
			feature = new Feature();
			feature.setFunctionalRequirement(fr);
			features.add(feature);
			
			//System.out.println(fr.getName() + ": " + fr.getInternalId());
			feature.setScenarios(scenariosDAO.getScenariosFor(fr));
		}
		
		return features;
	}
	
	public void saveToText(String eapFile, String packageName) {
		EAPConnectionFacade.Connect(eapFile);
		
		List<FunctionalRequirement> frs = this.readScenarios(packageName);
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
		SaveToText stot = new SaveToText();
		stot.saveToText("./test/resources/ForTesting 01.EAP", "UC - Tournament system");
	}
}
