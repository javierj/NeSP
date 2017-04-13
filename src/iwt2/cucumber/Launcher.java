package iwt2.cucumber;

import iwt2.util.NeSPConfiguration;

import java.util.ArrayList;
import java.util.*;

import cucumber.api.cli.Main;

/**
 * Launchs Cucumber after generating the text fils with the scearios.
 * @author Javier
 *
 */
public class Launcher {

	/**
	 * Test main.
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		NeSPConfiguration config = new NeSPConfiguration();
		
		config.setFeaturesPath("./");
		new Launcher().launch(config);
	}
	
	public void launch(NeSPConfiguration config) throws Throwable {
		String[] args = createArgs(config);
		
		//System.out.println(args);
		Main.main(args);
	}

	private String[] createArgs(NeSPConfiguration config) {
		List<String> args = new ArrayList<>();
		
		//args.add(getGlueArg(config));
		//args.add("--plugin junit");
		//args.add("--plugin pretty");
		
		args.add(config.featuresPath());
		
		
		return args.toArray(new String[1]);
	}

	/*
	private String getGlueArg(NeSPConfiguration config) {
		return "--glue "+"";
	}*/
	
	
	
}
