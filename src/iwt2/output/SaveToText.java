package iwt2.output;

import java.util.List;

import mdetest.concretesyntax.eap.EAPConnectionFacade;
import mdetest.concretesyntax.eap.EAPFunctionalRequirementDAO;
import mdetest.concretesyntax.eap.EAPPackageDAO;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;
import mdetest.metamodels.functionalrequirement.Subsystem;

public class SaveToText {

	
	public void saveToText(String eapFile, String packageName) {
		
		EAPConnectionFacade.Connect(eapFile);
		EAPFunctionalRequirementDAO frDAO =EAPConnectionFacade.getEAPFunctionalRequirementDAO();
		
		
		String pbId = this.idFor(packageName);
		List<FunctionalRequirement> frs =  frDAO.getAllFunctionalRequirementsIn(null, pbId);
	}
	
	//-----------------------------------------------
	
	private String idFor(String packageName) {
		EAPPackageDAO packages = EAPConnectionFacade.getEAPPackageDAO();
		Subsystem sub = packages.getPackage(packageName);	
		return packages.getIdOfSubsystem(sub);
	}
}
