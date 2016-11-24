package iwt2.output;

import java.util.ArrayList;
import java.util.List;

import iwt2.metamodel.gherkin.Feature;
import iwt2.concretesyntax.eap.EAPScenariosDAO;
import mdetest.concretesyntax.eap.EAPConnectionFacade;
import mdetest.concretesyntax.eap.EAPFunctionalRequirementDAO;
import mdetest.concretesyntax.eap.EAPPackageDAO;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;
import mdetest.metamodels.functionalrequirement.Subsystem;

public class SaveToText {

	
	public void saveToText(String eapFile, String packageName) {
		EAPConnectionFacade.Connect(eapFile);
		EAPFunctionalRequirementDAO frDAO = EAPConnectionFacade.getEAPFunctionalRequirementDAO();
		EAPScenariosDAO scenariosDAO = new EAPScenariosDAO(EAPConnectionFacade.Connection());
		
		
		String pbId = this.idFor(packageName);
		List<FunctionalRequirement> frs =  frDAO.getAllFunctionalRequirementsIn(null, pbId);
		
		
		
		List<Feature> features = new ArrayList<>();
		Feature feature;
		
		for (FunctionalRequirement fr: frs) {
			feature = new Feature();
			feature.setFunctionalRequirement(fr);
			features.add(feature);
			
			System.out.println(fr.getName() + ": " + fr.getInternalId());
			feature.setScenarios(scenariosDAO.getScenariosFor(fr));

		}

		// Volcarlos a una plantilla.
		
	}
	
	//-----------------------------------------------
	
	private String idFor(String packageName) {
		EAPPackageDAO packages = EAPConnectionFacade.getEAPPackageDAO();
		Subsystem sub = packages.getPackage(packageName);	
		return packages.getIdOfSubsystem(sub);
	}
	
	
	//------------------------------------------
	
	public static void main(String[] args) {
		SaveToText stot = new SaveToText();
		stot.saveToText("./test/resources/ForTesting 01.EAP", "UC - Tournament system");
	}
}
