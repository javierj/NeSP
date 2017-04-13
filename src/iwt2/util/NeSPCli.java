package iwt2.util;

import iwt2.cucumber.Launcher;
import iwt2.transformations.GherkinScenariosToText;

public class NeSPCli {

	public static void main(String[] args) throws Throwable {
		NeSPConfiguration config = new NeSPConfiguration();
		
		config.setFeaturesPath("./test/features_genetared");
		
		GherkinScenariosToText stot = new GherkinScenariosToText(config);
		stot.transform("./test/resources/Scenarios 03.EAP", "UC - Tournament system");
		
		System.out.println("-------------------------");
		
		new Launcher().launch(config);
		
		System.out.println("Ok");
	}
	
}
